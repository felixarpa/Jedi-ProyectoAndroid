package com.example.felixarpa.proyectojedi.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class IntentsOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DataBase";
    private static final String TABLE_USR = "Usuarios";
    private static final String USR_CREATE = "CREATE TABLE " + TABLE_USR + " (username TEXT PRIMARY KEY," +
            "name TEXT, password TEXT, address TEXT, mejor4x4 INTEGER, mejor6x6 INTEGER, mejor8x8 INTEGER, teImatge INTEGER);";

    public IntentsOpenHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USR_CREATE);
    }

    public void deleteUser(String usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_USR+" WHERE username='"+usr+"';");
    }

    public boolean correctPassword(String usr, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"username"};
        String[] where = {usr, password};
        Cursor cursor = db.query(
                TABLE_USR,
                columns,
                "username=? AND password=?",
                where,
                null,
                null,
                null
        );
        return cursor.moveToFirst();
    }


    public boolean putUsr(ContentValues contentValues) { // true: user insert. false: user already exists
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getUsr(contentValues.get("username").toString());
        if (c.moveToFirst()) return false;
        else {
            db.insert(TABLE_USR, null, contentValues);
            return true;
        }
    }

    public void clearRanking4x4() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET mejor4x4="+-1);
    }

    public void clearRanking6x6() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET mejor6x6="+-1);
    }

    public void clearRanking8x8() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET mejor8x8="+-1);
    }

    public Cursor getRanking4x4() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username", "name", "mejor4x4"};
        return db.query(
                TABLE_USR,
                columns,
                "mejor4x4!=-1",
                null,
                null,
                null,
                "mejor4x4 ASC"
        );
    }

    public Cursor getRanking6x6() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username", "name", "mejor6x6"};
        return db.query(
                TABLE_USR,
                columns,
                "mejor6x6!=-1",
                null,
                null,
                null,
                "mejor6x6 ASC"
        );
    }

    public Cursor getRanking8x8() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username", "name", "mejor8x8"};
        return db.query(
                TABLE_USR,
                columns,
                "mejor8x8!=-1",
                null,
                null,
                null,
                "mejor8x8 ASC"
        );
    }

    public void updateImageSi(String usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET teImatge=1 WHERE username='"+usr+"';");
    }

    public void updateImageNo(String usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET teImatge=0 WHERE username='"+usr+"';");
    }

    public void updateName(String usr, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET name='"+s+"' WHERE username='"+usr+"';");
    }

    public void updateAddress(String usr, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET address='"+s+"' WHERE username='"+usr+"';");
    }

    public void updatePassword(String usr, String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET password='"+s+"' WHERE username='"+usr+"';");
    }

    public void updateScore4x4(String usr, int p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET mejor4x4="+p+" WHERE username='"+usr+"';");
    }

    public void updateScore6x6(String usr, int p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET mejor6x6="+p+" WHERE username='"+usr+"';");
    }

    public void updateScore8x8(String usr, int p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_USR+" SET mejor8x8="+p+" WHERE username='"+usr+"';");
    }

    public int puntosActuales4x4(String usr) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"mejor4x4"};
        String[] where = {usr};
        Cursor cursor = db.query(
                TABLE_USR,
                columns,
                "username=?",
                where,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) return cursor.getInt(cursor.getColumnIndex("mejor4x4"));
        else return -1;
    }

    public int puntosActuales6x6(String usr) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"mejor6x6"};
        String[] where = {usr};
        Cursor cursor = db.query(
                TABLE_USR,
                columns,
                "username=?",
                where,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) return cursor.getInt(cursor.getColumnIndex("mejor6x6"));
        else return -1;
    }

    public int puntosActuales8x8(String usr) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"mejor8x8"};
        String[] where = {usr};
        Cursor cursor = db.query(
                TABLE_USR,
                columns,
                "username=?",
                where,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) return cursor.getInt(cursor.getColumnIndex("mejor8x8"));
        else return -1;
    }

    public Cursor getUsr(String usr) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"username", "name", "address", "mejor4x4", "mejor6x6", "mejor8x8", "teImatge"};
        String[] where = {usr};
        return db.query(
                TABLE_USR,
                columns,
                "username=?",
                where,
                null,
                null,
                null
        );
    }

    public void clearTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USR);
        db.execSQL(USR_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USR);
        db.execSQL(USR_CREATE);
    }
}
