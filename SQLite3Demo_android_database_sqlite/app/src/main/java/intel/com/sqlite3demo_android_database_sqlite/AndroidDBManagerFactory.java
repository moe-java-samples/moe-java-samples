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

package intel.com.sqlite3demo_android_database_sqlite;


import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.intel.inde.moe.sqlite.AbstractDBManagerFactory;
import com.intel.inde.moe.sqlite.DBManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AndroidDBManagerFactory extends AbstractDBManagerFactory {

    public static  final String TAG = "AndroidDBManagerFactory";

    private Context context;

    public AndroidDBManagerFactory(Context c) {
        this.context = c;
    }

    @Override
    public String applicationDocumentsDirectory() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    public File copyDatabaseIntoDocumentsDirectory() {

        File dbFile = new File(applicationDocumentsDirectory(), DBManager.DB_NAME);

        if (!dbFile.exists()) {

            try {
                InputStream dbSource = context.getResources().getAssets().open(DBManager.DB_NAME);

                if (dbSource != null) {
                    OutputStream os = null;
                    try {
                        try {

                            os = new FileOutputStream(dbFile);

                            if (os == null) {
                                throw new RuntimeException("DB file not created: " + dbFile.getPath());
                            }

                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = dbSource.read(buffer)) > 0) {
                                os.write(buffer, 0, length);
                            }
                        } finally {
                            dbSource.close();
                            if (os != null) {
                                os.close();
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Unable copy base db file", e);
                    }
                } else {
                    Log.e(TAG, "Unable find resource");
                }
            } catch (IOException ioe) {
                Log.e(TAG, "Unable find base db file", ioe);
            }
        }

        return dbFile;
    }
}
