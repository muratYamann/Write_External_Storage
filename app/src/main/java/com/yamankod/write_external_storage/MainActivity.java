package com.yamankod.write_external_storage;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by murat on 13.12.2016.
 */

public class MainActivity extends Activity {

    final static int PERMISSIONS_REQUEST_CODE = 1;



    Button btnRead,btnWrite;
    EditText etYourData;
    TextView tvResponseData;

    ArrayList<String> liste;

    WriteToFile write;
    ReadToFile read;

    String[] sendData ;

    String fileName ="NotDefterim.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissionWriteExternalStorage();
        getPermissionReadExternalStorage();

        btnRead = (Button)findViewById(R.id.btnRead);
        btnWrite = (Button)findViewById(R.id.btnWrite);
        etYourData = (EditText)findViewById(R.id.etWrite);
        tvResponseData =(TextView)findViewById(R.id.tvread);



        sendData =new String[1];
        liste = new ArrayList();
        write = new WriteToFile();
        read =  new ReadToFile();


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendData[0]=etYourData.getText().toString();
                write.WriteFile(fileName,sendData);

                Toast.makeText(MainActivity.this, "Veri Dosyaya yazıldı...", Toast.LENGTH_SHORT).show();

                etYourData.setText("");
            }
        });


        //Read From File
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                read.ReadToFile(fileName,getApplicationContext());
                liste =read.liste;

                String datam ="";
                for ( int i = 1; i<liste.size();i++){
                    datam +=liste.get(i)+"\n";
                }
                tvResponseData.setText(datam);

            }
        });

    }


    public void getPermissionWriteExternalStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            }
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_CODE);
        }
    }



    public void getPermissionReadExternalStorage() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    PERMISSIONS_REQUEST_CODE);
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "izin verildi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "izin verilmedi ", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }



}
