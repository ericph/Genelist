package io.github.genelist.util;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import io.github.genelist.listitems.MusicArtist;
import io.github.genelist.lists.GeneList;
import io.github.genelist.R;

public class User {
    private User(){}
    private static class SingletonHelper { private static final User INSTANCE = new User(); }
    public static User getInstance() { return SingletonHelper.INSTANCE; }

    public String username = "";
    public GeneList<MusicArtist> musicArtistList = new GeneList<>();

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
                musicArtistList.add(MusicArtist.getById(Long.parseLong(nextLine)));
                nextLine = br.readLine();
            }
            br.close();
            isr.close();
            fis.close();
        } catch (Exception e) {
            Toast.makeText(context, R.string.read_save_data_error, Toast.LENGTH_SHORT).show();
            username = "";
            musicArtistList = new GeneList<>();
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
                Toast.makeText(context, R.string.io_close_error, Toast.LENGTH_SHORT).show();
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
            for (MusicArtist item : musicArtistList) {
                osw.write(item.getId() + "\n");
            }
            osw.flush(); osw.close();
            fos.flush(); fos.close();
        } catch (IOException e) {
            Toast.makeText(context, R.string.write_save_data_error, Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (osw != null) {
                    osw.flush(); osw.close();
                }
                if (fos != null) {
                    fos.flush(); fos.close();
                }
            } catch (IOException e) {
                Toast.makeText(context, R.string.io_close_error, Toast.LENGTH_SHORT).show();
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
