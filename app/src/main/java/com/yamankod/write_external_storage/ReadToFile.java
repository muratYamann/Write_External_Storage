package com.yamankod.write_external_storage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class ReadToFile {

    private static final String TAG = "_Read";
    private String error = "NoError";
    String line = "";
    public ArrayList<String> liste;
    Context context;

    public  void ReadToFile(String fileName, Context context){
        this.context = context;
        Log.d(TAG, "ReadToFile method");
        liste=new ArrayList<String>();

        try {

            File root = Environment.getExternalStorageDirectory();
            File myFile = new File(root, fileName);


            if (myFile.exists()){//Dosya Var ise

                Log.i(TAG, "file exist");

                FileInputStream fIn = new FileInputStream(myFile);

                InputStreamReader inputStreamReader = new InputStreamReader(fIn);

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                while ((line = bufferedReader.readLine()) != null) {
                    Log.d(TAG, "ReadToFile: :"+line);
                    liste.add(line);
                }

                fIn.close();

            }else error = "FileNotFound";

        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "Dosya yok: " + e.toString());

        } catch (IOException e) {
            Log.e(TAG, "Dosya okunamiyor: " + e.toString());
        }


    }

    public String getError(){
        return this.error;
    }

}



