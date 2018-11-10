package io.github.genelist.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import io.github.genelist.listitems.GeneListItem;
import io.github.genelist.listitems.Musician;
import io.github.genelist.base.GeneList;
import io.github.genelist.R;

public class User {
    private User(){}
    private static class SingletonHelper { private static final User INSTANCE = new User(); }
    public static User getInstance() { return SingletonHelper.INSTANCE; }

    private static final Logger LOG = Logger.getLogger("io.github.genelist.util.User");

    public String username = "";
    public GeneList<GeneListItem> masterList = new GeneList<>();
    public GeneList<Musician> musicianList = new GeneList<>();

    public boolean readSaveData(Context context) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            fis = context.openFileInput("genelistUser");
            if (fis == null)
                return false;
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String nextLine = br.readLine();
            username = nextLine == null ? "" : nextLine;
            br.close();
            isr.close();
            fis.close();

            fis = context.openFileInput("genelistList1");
            if (fis == null)
                return false;
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            nextLine = br.readLine();
            while (nextLine != null && !nextLine.equals("")) {
                musicianList.add(Musician.getById(nextLine));
                nextLine = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            Util.warn(LOG, R.string.err_read_data);
            username = "";
            masterList = new GeneList<>();
            musicianList = new GeneList<>();
            return false;
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
        return true;
    }

    public void writeSaveData(Context context) {
        File uFile = context.getFileStreamPath("genelistUser");
        File l1File = context.getFileStreamPath("genelistList1");
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            uFile.createNewFile(); // only creates new file if no file with filename exists
            fos = context.openFileOutput("genelistUser", Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            osw.write(username);
            osw.flush(); osw.close();
            fos.flush(); fos.close();

            l1File.createNewFile(); // only creates new file if no file with filename exists
            fos = context.openFileOutput("genelistList1", Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            for (Musician item : musicianList) {
                osw.write(item.getId() + "\n");
            }
            osw.flush(); osw.close();
            fos.flush(); fos.close();
        } catch (IOException e) {
            Util.warn(LOG, R.string.err_write_data);
        } finally {
            try {
                if (osw != null) {
                    osw.flush(); osw.close();
                }
                if (fos != null) {
                    fos.flush(); fos.close();
                }
            } catch (IOException e) {
                Util.warn(LOG, R.string.err_io_close);
            }
        }
    }

    public void deleteSaveData(Context context) {
        File uFile = context.getFileStreamPath("genelistUser");
        if (uFile.exists())
            uFile.delete();
        File l1File = context.getFileStreamPath("genelistList1");
        if (l1File.exists())
            l1File.delete();
    }
}
