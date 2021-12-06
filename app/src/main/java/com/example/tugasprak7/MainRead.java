package com.example.tugasprak7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private Mydatabase db;
    private List<Fashion> ListFashion = new ArrayList<Fashion>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new Mydatabase(this);
        adapter_off = new CustomListAdapter(this, ListFashion
        );
        mListView = (ListView) findViewById(R.id.list_fashion);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListFashion.clear();
        List<Fashion> fashion = db.ReadFashion();
        for (Fashion mhs : fashion) {
            Fashion daftar = new Fashion();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_harga(mhs.get_harga());
            ListFashion.add(daftar);
            if ((ListFashion.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Fashion detailFsn = (Fashion) o;
        String Sid = detailFsn.get_id();
        String Snama = detailFsn.get_nama();
        String Sharga = detailFsn.get_harga();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharag", Sharga);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListFashion.clear();
        mListView.setAdapter(adapter_off);
        List<Fashion> fashion = db.ReadFashion();
        for (Fashion fsn : fashion) {
            Fashion daftar = new Fashion();
            daftar.set_id(fsn.get_id());
            daftar.set_nama(fsn.get_nama());
            daftar.set_harga(fsn.get_harga());
            ListFashion.add(daftar);
            if ((ListFashion.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
