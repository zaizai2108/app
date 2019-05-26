package com.example.administrator.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.myclass.MyClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_PART = "TABLE_CLASS";
    private static final String TABLE_USER = "TABLE_USER";
    private static DBHelper instance;
    private static int version = 1;
    DBConfig dbConfig=new DBConfig();

    public DBHelper(Context context) {
        super(context, DBConfig.dbPath, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db, prepareTableInfo());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private ArrayList<Table> prepareTableInfo() {
        ArrayList<Table> tables = new ArrayList<Table>();
        Table t;

        t = new Table(TABLE_PART); //
        t.addFeild(new Feild("day", "text")); //
        t.addFeild(new Feild("name", "text")); //
        t.addFeild(new Feild("time", "text")); //
        t.addFeild(new Feild("address", "text")); //
        tables.add(t);

        return tables;
    }

    private void createTables(SQLiteDatabase db, ArrayList<Table> tables) {
        db.beginTransaction();
        for (Table table : tables) {
            db.execSQL(table.createSQL());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    /**

     */
    public long addClass(MyClass part) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("insert into TABLE_CLASS values('qq','www','eee','rrr')");

        ContentValues cv = new ContentValues();
        cv.put("day", part.day);
        cv.put("name", part.name);
        cv.put("time", part.time);
        cv.put("address", part.address);

        long insert = db.insert(DBHelper.TABLE_PART, null, cv);
        db.close();
        return insert;
    }

    public int deleteClass(MyClass part) {
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete(TABLE_PART, "name = ?", new String[]{part.name});
        db.close();
        return i;
    }


    public ArrayList<MyClass> getAllClass() {
        ArrayList<MyClass> rlts = new ArrayList<MyClass>();
        SQLiteDatabase db = this.getReadableDatabase();
        String whereClause = null;
        String[] columns = {"day", "name", "time", "address"};
        String orderby = "day";

        Cursor cursor = db.query(TABLE_PART, columns, whereClause, null, null,
                null, orderby, null);
        MyClass part = null;
        while (cursor.moveToNext()) {
            part = new MyClass( cursor.getString(cursor.getColumnIndex("day")), cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("address")));

            rlts.add(part);
        }
        cursor.close();
        db.close();
        return rlts;
    }

    public ArrayList<MyClass> getOneDayClass() {
        String whereClause = null;  //数据库查询条件
        //或去当前是周几
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            whereClause ="day like '%天' or day like '%七' or day like '%末'";
        }else if("2".equals(mWay)){
            whereClause ="day like '%一'";
        }else if("3".equals(mWay)){
            whereClause ="day like '%二'";
        }else if("4".equals(mWay)){
            whereClause ="day like '%三'";
        }else if("5".equals(mWay)){
            whereClause ="day like '%四'";
        }else if("6".equals(mWay)){
            whereClause ="day like '%五'";
        }else if("7".equals(mWay)){
            whereClause ="day like '%六'";
        }
        ArrayList<MyClass> rlts = new ArrayList<MyClass>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"day", "name", "time", "address"};
        String orderby = "day";
        Cursor cursor = db.query(TABLE_PART, columns, whereClause, null, null,
                null, orderby, null);
        MyClass part = null;
        while (cursor.moveToNext()) {
            part = new MyClass( cursor.getString(cursor.getColumnIndex("day")), cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("time")),cursor.getString(cursor.getColumnIndex("address")));

            rlts.add(part);
        }
        cursor.close();
        db.close();
        return rlts;
    }

    // 对搜索到的课程进行排序 TODO
    public ArrayList<MyClass> ordbyData(ArrayList<MyClass> rlts){
        for(MyClass temp:rlts){

        }
        return rlts;
    }

}
