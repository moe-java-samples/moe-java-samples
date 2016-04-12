package com.intel.moe.samples.notetaking.common;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Data {
    private HashMap<String,String> allNotes;
    private String key;

    public  static final String FILENAME = "notes.txt";
    private String baseDir;

    public Data(String dir){
        baseDir = dir;
    }

    public void setCurrentKey(String key){
        this.key = key;
    }

    public String getCurrentKey(){
        return key;
    }

    public void setNoteForCurrentKey(String note){
        allNotes.put(key,note);
    }

    public void removeCurrentNote() { allNotes.remove(key); }

    public String getCurrentNote() { return allNotes.get(key); }

    public HashMap<String, String> getAllNotes(){
        if(allNotes==null) {
            InputStream fis = null;
            ObjectInputStream ois = null;
            try {
                String filePath = baseDir + File.separator + FILENAME;
                File f = new File(filePath);

                if(!f.exists()) {
                    f.createNewFile();
                }
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);

                allNotes = (HashMap<String,String>) ois.readObject();

                ois.close();
                fis.close();
            } catch (EOFException e) {
                allNotes = new HashMap<String, String>();
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();

            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();

            }
        }
        return allNotes;
    }

    public void saveFile() {
        try {
            String filePath = baseDir + File.separator + FILENAME;
            File f = new File(filePath);
            if(!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allNotes);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
