package com.example.nombi.warframebuild.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nombi.warframebuild.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nombi on 5/30/2017.
 */

public class WarframeLoadoutDB {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "WarframeLoadout.db";

    private LoadoutDBHelper mLoadoutDBHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private static final String LOADOUT_TABLE = "UserLoadout";

    /**
     *
     * @param email
     * @param name
     * @return
     */
    public boolean insertLoadout(String name) {
        ContentValues contentValues = new ContentValues();
        //contentValues.put("email", email);
        contentValues.put("loadout_name", name);


        long rowId = mSQLiteDatabase.insert("UserLoadout", null, contentValues);
        return rowId != -1;
    }
    public WarframeLoadoutDB(Context context) {
        mLoadoutDBHelper = new LoadoutDBHelper(
                context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mLoadoutDBHelper.getWritableDatabase();
    }

    /**
     * Returns the list of courses from the local Course table.
     * @return list
     */
    public List<String> getLoadouts() {

        String[] columns = {
                "loadout_name", "email"
        };

        Cursor c = mSQLiteDatabase.query(
                LOADOUT_TABLE,  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<String> list = new ArrayList<String>();
        for (int i=0; i<c.getCount(); i++) {
            String id = c.getString(0);
            String shortDesc = c.getString(1);
            list.add(id);
            c.moveToNext();
        }

        return list;
    }




    public void closeDB() {
        mSQLiteDatabase.close();
    }

    /**
     * Delete all the data from the COURSE_TABLE
     */
    public void deleteCourses() {
        mSQLiteDatabase.delete(LOADOUT_TABLE, null, null);
    }


    /**
     *
     */
    class LoadoutDBHelper extends SQLiteOpenHelper {

        private final String CREATE_LOADOUT_SQL;

        private final String DROP_LOADOUT_SQL;

        public LoadoutDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            CREATE_LOADOUT_SQL = context.getString(R.string.CREATE_LOADOUT_SQL);
            DROP_LOADOUT_SQL = context.getString(R.string.DROP_LOADOUT_SQL);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_LOADOUT_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_LOADOUT_SQL);
            onCreate(sqLiteDatabase);
        }

    }

}
