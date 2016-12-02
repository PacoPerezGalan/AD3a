package com.pacoperezgalan.ad3a;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    FileOutputStream fos;
    DataOutputStream dos;
    FileInputStream fis;
    DataInputStream dis;

    EditText text;
    Button afegir;
    Button mostrar;
    TextView tv_tot;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(EditText) findViewById(R.id.et_text);
        tv_tot=(TextView) findViewById(R.id.tv_text);
        afegir=(Button) findViewById(R.id.btn_afegir);
        mostrar=(Button) findViewById(R.id.btn_mostrar);

        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fos= openFileOutput("Fitxer.txt", Context.MODE_APPEND);
                    dos=new DataOutputStream(fos);
                    dos.writeBytes(text.getText().toString());
                    fos.close();
                    dos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fis= openFileInput("Fitxer.txt");
                    dis= new DataInputStream(fis);
                    byte[] buff=new byte[5000];
                    dis.read(buff);

                    tv_tot.setText(new String(buff));

                    fis.close();
                    dis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
