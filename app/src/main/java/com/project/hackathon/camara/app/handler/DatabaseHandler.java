package com.project.hackathon.camara.app.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.hackathon.camara.app.model.Suspected;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "suspectedManager";
    private static final String TABLE_SUSPECTED = "suspected";

    private static final String KEY_ID = "id";
    private static final String KEY_VOTE = "vote";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SUSPECTED + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_VOTE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUSPECTED);
        onCreate(db);
    }

    public void addSuspected(Suspected suspected) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VOTE, suspected.getVote());

        db.insert(TABLE_SUSPECTED, null, values);
        db.close();
    }

    public Suspected getSuspected(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUSPECTED, new String[]{KEY_ID,
                        KEY_VOTE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        Suspected suspected = null;

        if (cursor != null && cursor.getCount() != 0) {
            cursor.moveToFirst();
            suspected = new Suspected(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)));
        }


        return suspected;
    }

    public List<Suspected> getAllSuspecteds() {
        List<Suspected> suspectedList = new ArrayList<Suspected>();

        String selectQuery = "SELECT  * FROM " + TABLE_SUSPECTED;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Suspected suspected = new Suspected();
                suspected.setId(Integer.parseInt(cursor.getString(0)));
                suspected.setVote(Integer.parseInt(cursor.getString(1)));
                suspectedList.add(suspected);
            } while (cursor.moveToNext());
        }

        return suspectedList;
    }

    public int updateSuspected(Suspected suspected) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(KEY_VOTE, "" + suspected.getVote());

        return db.update(TABLE_SUSPECTED, values, KEY_ID + " = ?",
                new String[] { String.valueOf(suspected.getId()) });
    }

    public void deleteSuspected(Suspected suspected) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUSPECTED, KEY_ID + " = ?",
                new String[]{String.valueOf(suspected.getId())});
        db.close();
    }

    public int getSuspectedsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SUSPECTED;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

}
