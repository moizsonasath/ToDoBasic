package com.msonasath.todobasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.sonasath on 11/20/2015.
 */
public class DbActions {
    private SQLiteDatabase db;
    private MySQLiteHelper dbHelper;

    public DbActions(final Context context) {
        dbHelper = new MySQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Close the db
    public void close() {
        db.close();
    }

    /**
     * Create new TODO item object
     * @param itemText
     */
    public int createItem(String itemText) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.ITEM, itemText);
        // Insert into DB
        db.insert(MySQLiteHelper.TABLE, null, contentValues);
        return getLastItemId();
    }

    public int getLastItemId () {
        String[] tableColumns = new String[] {MySQLiteHelper.ID};
        Cursor cursor = db.query(MySQLiteHelper.TABLE, tableColumns, null, null, null, null, null);

        if (cursor.moveToLast()) {
            return Integer.parseInt(cursor.getString(0));
        }

        return -1;
    }

    /**
     * Delete TODO item object
     * @param itemId
     */
    public void deleteItem(int itemId) {
        // Delete from DB where id match
        db.delete(MySQLiteHelper.TABLE, "_id = " + itemId, null);
    }

    /**
     * Edit existing TODO item object
     * @param itemText
     */
    public void editItem(String itemText, int itemId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteHelper.ITEM, itemText);
        // Insert into DB
        db.update(MySQLiteHelper.TABLE, contentValues, "_id=" + itemId, null);
    }

    /**
     * Get all TODOs.
     * @return
     */
    public void getAllItems(ArrayList<Integer> ids, ArrayList<String> items) {
        // Name of the columns we want to select
        String[] tableColumns = new String[] {MySQLiteHelper.ID,MySQLiteHelper.ITEM};

        // Query the database
        Cursor cursor = db.query(MySQLiteHelper.TABLE, tableColumns, null, null, null, null, null);
        cursor.moveToFirst();

        // Iterate the results
        while (!cursor.isAfterLast()) {
            DbObject todo = new DbObject();
            // Take values from the DB
            ids.add(cursor.getInt(0));
            items.add(cursor.getString(1));

            // Add to the DB
            //todoList.add(todo);

            // Move to the next result
            cursor.moveToNext();
        }
    }
}
