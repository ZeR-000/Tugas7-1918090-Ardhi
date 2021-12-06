package com.example.tugasprak7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Mydatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "db_penjualan";
    private static final String tb_fashion = "tb_fashion";
    private static final String tb_fashion_id = "id";
    private static final String tb_fashion_nama = "nama";
    private static final String tb_fashion_harga = "harga";
    private static final String CREATE_TABLE_FASHION = "CREATE TABLE " +
            tb_fashion +"("
            + tb_fashion_id + " INTEGER PRIMARY KEY ,"
            + tb_fashion_nama + " TEXT ,"
            + tb_fashion_harga + " TEXT " + ")";

    public Mydatabase (Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FASHION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void CreateFashion(Fashion data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_fashion_id, data.get_id());
        values.put(tb_fashion_nama, data.get_nama());
        values.put(tb_fashion_harga, data.get_harga());
        db.insert(tb_fashion, null, values);
        db.close();
    }
    public List<Fashion> ReadFashion() {
        List<Fashion> listFsn = new ArrayList<Fashion>();
        String selectQuery = "SELECT * FROM " + tb_fashion;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Fashion data = new Fashion();
                data.set_id(cursor.getString(0));
                data.set_nama(cursor.getString(1));
                data.set_harga(cursor.getString(2));
                listFsn.add(data);
            } while (cursor.moveToNext());
        }
        db.close();
        return listFsn;
    }
    public int UpdateFashion (Fashion data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_fashion_nama, data.get_nama());
        values.put(tb_fashion_harga, data.get_harga());
        return db.update(tb_fashion, values, tb_fashion_id +
                        " = ?",
                new String[]{String.valueOf((data.get_id()))});
    }
    public void DeleteFashion(Fashion data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_fashion,tb_fashion_id+ " = ?",
                new String[]{String.valueOf(data.get_id())});
        db.close();
    }
}
