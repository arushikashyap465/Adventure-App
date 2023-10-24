package com.example.adventureapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int  databaseVersion =1;
    private static final String databaseName ="db_adventure";
    private static final String tableAdventure="tbl_adventure";

    private static final String adventureId ="id";
    private static final String adventureName ="name";
    private static final String adventureDesc ="description";
    private static final String adventureCost ="cost";
    public DatabaseHandler(@Nullable Context context) {
        super(context,databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ADVANTURE_TABLE = "CREATE TABLE " + tableAdventure + "("
                + adventureId + " INTEGER PRIMARY KEY," + adventureName + " TEXT, "
                + adventureDesc + " TEXT,"
                + adventureCost + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_ADVANTURE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tableAdventure);
    }

    public void addAdvantureItem(Adventure adventure)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(adventureName,adventure.getName());
        values.put(adventureDesc, adventure.getDescription());
        values.put(adventureCost,adventure.getCost());

        db.insert(tableAdventure, null, values);
        db.close();

    }

    public ArrayList<Adventure> getAllAdvantureData()
    {
        ArrayList<Adventure> adventureArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String  selectQuery ="SELECT * FROM "+ tableAdventure;
        Cursor cursor =db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst())
        {
            do {
                Adventure adventure =new Adventure();
                adventure.setId(cursor.getInt(0));
                adventure.setName(cursor.getString(1));
                adventure.setDescription(cursor.getString(2));
                adventure.setCost(cursor.getString(3));
                adventureArrayList.add(adventure);
            }
            while (cursor.moveToNext());
        }

        return  adventureArrayList;
    }

    public Adventure getAdvantureItemById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String  selectQuery ="SELECT * FROM "+ tableAdventure+" WHERE "+adventureId+"="+id;
        Cursor cursor =db.rawQuery(selectQuery,null);
        Adventure adventure =new Adventure();
        if(cursor.moveToFirst())
        {

                adventure.setId(cursor.getInt(0));
                adventure.setName(cursor.getString(1));
                adventure.setDescription(cursor.getString(2));
                adventure.setCost(cursor.getString(3));
        }

        return  adventure;
    }
}
