package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserData extends SQLiteOpenHelper {
    public UserData(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE user ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "tuoi INTEGER NOT NULL)";
        db.execSQL(sql);
    }
    public List<User> getAll(){
        List<User> list = new ArrayList<>();
        String sql = "select * from user";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setTuoi(cursor.getInt(2));
                list.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return list;
    }
    public int removeUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        return db.delete("User", "ID =?",
                new String[] {String.valueOf(id)});
    }
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("tuoi", user.getTuoi());

        db.insert("user", null, values);

    }
    public void editUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("tuoi", user.getTuoi());
        db.update("user",values,"id = ?", new String[]{String.valueOf(user.getId())});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
