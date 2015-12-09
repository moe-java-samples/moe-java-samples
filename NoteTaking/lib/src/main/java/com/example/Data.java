// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.example;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class Data {

    private HashMap<String,String> allNotes;
    private String key;

    private String baseDir;
    String fileName = "note_taking.txt";
    String filePath;


    public Data(String dir){
        baseDir = dir;
        filePath = baseDir + File.separator + fileName;
    }

    public HashMap<String, String> getAllNotes(){

        File f = new File(filePath);
            try {
                if(!f.exists()) {
                    f.createNewFile();
                }
                if(allNotes==null) {
                    try(InputStream fis = new FileInputStream(f);ObjectInputStream ois = new ObjectInputStream(fis)){
                        allNotes = (HashMap<String, String>) ois.readObject();

                        }catch (EOFException e){
                        allNotes = new HashMap<String, String>();
                    }
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
            }
        return allNotes;
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

    public String getNoteForCurrentKey(){return allNotes.get(key);}

    public void setNoteForKey(String k,String note){
        allNotes.put(k,note);
    }

    public String getNoteForKey(String k){return allNotes.get(k);}

    public void removeNoteForKey(String k){
        allNotes.remove(k);
    }

    public void saveFile() {
        try {
            File f = new File(filePath );
            if(!f.exists()) {
                f.createNewFile();
            }try(FileOutputStream fos = new FileOutputStream(f);ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(allNotes);

            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getAllKeys(){
        ArrayList<String> keys = new ArrayList<String>();
        keys.addAll(getAllNotes().keySet());
        Collections.sort(keys,Collections.reverseOrder());
        return keys;
    }

}
