package com.example.tugasprak7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainCreate extends AppCompatActivity {
    private Mydatabase db;
    private EditText Tnama, Tharga;
    private String Snama, Sharaga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_create);
        db = new Mydatabase(this);
        Tnama = (EditText) findViewById(R.id.create_nama);
        Tharga = (EditText) findViewById(R.id.create_harga);
        Button btnCreate = (Button)
                findViewById(R.id.create_btn);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snama= String.valueOf(Tnama.getText());
                Sharaga = String.valueOf(Tharga.getText());
                if (Snama.equals("")){
                    Tnama.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi nama barang",
                            Toast.LENGTH_SHORT).show();
                }
                else if (Sharaga.equals("")) {
                    Tharga.requestFocus();
                    Toast.makeText(MainCreate.this, "Silahkan isi harga",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Tnama.setText("");
                    Tharga.setText("");
                    Toast.makeText(MainCreate.this, "Data telah ditambah",
                            Toast.LENGTH_SHORT).show();
                    db.CreateFashion(new Fashion(null, Snama,
                            Sharaga));
                    Intent a = new Intent(MainCreate.this,
                            MainActivity.class);
                    startActivity(a);
                }
            }
        });
    }
}
