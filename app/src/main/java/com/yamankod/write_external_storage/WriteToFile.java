package com.yamankod.write_external_storage;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WriteToFile {

    ///sdcard/mysdfile.txt"

    private static final String TAG = "_WriteToFile";

    public void WriteFile(String fileName, String[] mesaj) {

         try {

            File root = Environment.getExternalStorageDirectory();
            // File myFile = new File("/sdcard/mysdfile.txt");//root, fileName);
             File myFile = new File(root,fileName);

            if (myFile.exists()) {

                Log.i(TAG, "file exist");
             //   myFile.delete(); //bu satırın silinmedigi zaman silip üzerine yazıyor
            }

            myFile.createNewFile();

            FileOutputStream fOut = new FileOutputStream(myFile, true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);

            for (int i = 0; i < mesaj.length; i++) {
                myOutWriter.write(mesaj[i] + "\n");
            }

            myOutWriter.flush();
            myOutWriter.close();

            fOut.close();

            Log.d(TAG, "Done writing SD");

        } catch (IOException e) {

            Log.w(TAG, "" + e.toString());

        }

    }
}
