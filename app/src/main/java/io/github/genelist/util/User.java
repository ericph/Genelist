package io.github.genelist.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.github.genelist.base.ListItem;
import io.github.genelist.listitems.*;
import io.github.genelist.base.GeneList;
import io.github.genelist.R;

public class User {
    private User(){}
    private static class SingletonHelper { private static final User INSTANCE = new User(); }
    public static User getInstance() { return SingletonHelper.INSTANCE; }

    private static final Logger LOG = Logger.getLogger("io.github.genelist.util.User");

    public String username = "";
    public GeneList<GeneListItem> masterList = new GeneList<>();
    public GeneList<Album> albumList = new GeneList<>();
    public GeneList<Game> gameList = new GeneList<>();
    public GeneList<Musician> musicianList = new GeneList<>();

    public boolean readSaveData(Context context) {
        List<String> data = readFromFile("genelistUser", context);
        if (data == null)
            return false;
        username = data.get(0);

        List<String> listIds = readFromFile("genelistList0", context);
        if (listIds == null)
            return false;
        masterList = new GeneList<>();

        for (String listId : listIds) {
            data = readFromFile("genelistList" + listId, context);
            if (data == null)
                return false;

            // TODO: read list data into various GeneList objects
        }
        return true;
    }

    private List<String> readFromFile(String filename, Context context) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        List<String> data = new ArrayList<>(5);
        try {
            fis = context.openFileInput(filename);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String nextLine = br.readLine();
            while (nextLine != null && !nextLine.equals("")) {
                data.add(nextLine);
                nextLine = br.readLine();
            }
        } catch (Exception e) {
            Util.warn(LOG, R.string.err_read_data);
            return null;
        } finally {
            try {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                Util.warn(LOG, R.string.err_io_close);
            }
        }
        return data;
    }

    public void writeSaveData(Context context) {
        writeToFile("genelistUser", username, context);

        StringBuffer listsSB = new StringBuffer();
        StringBuffer dataSB;
        for (GeneListItem gli : masterList) {
            listsSB.append(gli.getId());
            listsSB.append("\n");

            dataSB = new StringBuffer();
            for (ListItem item: gli.getList()) {
                dataSB.append(item.getId());
                dataSB.append("\n");
            }
            writeToFile("genelistList" + gli.getId(), dataSB.toString(), context);
        }
        writeToFile("genelistList0", listsSB.toString(), context);
    }

    private void writeToFile(String filename, String data, Context context) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            // only creates new file if no file with filename exists
            context.getFileStreamPath(filename).createNewFile();
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            osw.write(data);
        } catch (IOException e) {
            Util.warn(LOG, R.string.err_write_data);
        } finally {
            try {
                if (osw != null) {
                    osw.flush();
                    osw.close();
                }
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                Util.warn(LOG, R.string.err_io_close);
            }
        }
    }

    public void deleteSaveData(Context context) {
        List<String> filenames = new ArrayList<>();
        filenames.add("genelistUser");
        filenames.add("genelistList0");
        filenames.add("genelistList1");
        filenames.add("genelistList2");
        filenames.add("genelistList3");
        filenames.add("genelistList4");
        filenames.add("genelistList5");
        for (String filename : filenames) {
            File file = context.getFileStreamPath(filename);
            if (file.exists())
                file.delete();
        }
    }
}
