package com.example.parkapp.entity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.parkapp.utils.SQLiteAdapter;

import java.io.Serializable;


public class QualityCheck implements Serializable {

    private int _id;
    private String qc_id;//保存用户的ID(如果计划使用ContentProvider来共享表，就必须具有唯一的ID字段)
    private String qc_name;
    private String qc_date;
    private String app_id;
    private String qc_result;
    //声明一个SQLiteAdapter对象作为访问SQLite数据库的中介
    private SQLiteAdapter sqLiteAdapter = null;
    private SQLiteDatabase sqLiteDb = null;                    //数据库实例

    private static final String TABLE_NAME = "quality_checks";            //记录用户信息的表名

    public QualityCheck() {
    }

    public QualityCheck(String qc_id, String qc_name, String qc_date, String app_id, String qc_result) {
        this.qc_id = qc_id;
        this.qc_name = qc_name;
        this.qc_date = qc_date;
        this.app_id = app_id;
        this.qc_result = qc_result;
    }

    @Override
    public String toString() {
        return "添加成功";
    }
//    public Cursor queryCate(Context context,String str) //按类别查询
//    {
//        sqLiteAdapter = new SQLiteAdapter(context);
//        //创建SQLiteAdapter对象
//        sqLiteDb = sqLiteAdapter.getSqliteDb();          //得到SQLite实例
//        Cursor cursor = sqLiteDb.query(TABLE_NAME,null, "category= "+str, null, null, null, null);                 //获取注册的所有用户数据
//        return cursor;
//    }
//    public void saveQualityCheck(Context context) {
//        sqLiteAdapter = new SQLiteAdapter(context);
//        //创建SQLiteAdapter对象
//        sqLiteDb = sqLiteAdapter.getSqliteDb();			//得到SQLite实例
//        ContentValues values = new ContentValues();		//构造ContentValues实例
//        //保存数据
//        values.put("qualityCheckname", qualityCheckname);
//        values.put("qualityCheckISBN",qualityCheckISBN);
//        values.put("price", price);
//        values.put("description", description);
//        values.put("category", category);
//        values.put("photoID",photoID);
//        sqLiteDb.insert(TABLE_NAME, null, values);		//添加数据
//
//    }

    public void getQualityCheckData(Context context) {
        sqLiteAdapter = new SQLiteAdapter(context);
        //创建SQLiteAdapter对象
        sqLiteDb = sqLiteAdapter.getSqliteDb();            //得到SQLite实例
        Cursor cursor = sqLiteDb.query(TABLE_NAME, new String[]{"qc_id", "qc_name", "qc_date", "app_id", "qc_result"}, null, null, null, null, null);                        //获取注册的用户数据
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            qc_id = cursor.getString(0);
            qc_name = cursor.getString(1);
            qc_date = cursor.getString(2);
            app_id = cursor.getString(3);
            qc_result = cursor.getString(4);
        }
        cursor.close();
    }

    public Cursor queryAll(Context context) {
        sqLiteAdapter = new SQLiteAdapter(context);
        //创建SQLiteAdapter对象
        sqLiteDb = sqLiteAdapter.getSqliteDb();          //得到SQLite实例
        Cursor cursor = sqLiteDb.query(TABLE_NAME, null, null, null, null, null, null);                    //获取注册的所有用户数据
        return cursor;
    }

//    public boolean updateAll(Context context, int id, String qualityCheckname, String qualityCheckISBN, double price, String description, int category, int photoID) {
//        sqLiteAdapter = new SQLiteAdapter(context);
//        //创建SQLiteAdapter对象
//        sqLiteDb = sqLiteAdapter.getSqliteDb();          //得到SQLite实例
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("qualityCheckname", qualityCheckname);
//        contentValues.put("qualityCheckISBN", qualityCheckISBN);
//        contentValues.put("price", price);
//        contentValues.put("description", description);
//        contentValues.put("category", category);
//        contentValues.put("photoID", photoID);
//        sqLiteDb.update(TABLE_NAME, contentValues, "qc_id=" + id, null);
//        return true;
//    }

//    public boolean deleteID(Context context, int id) {
//        sqLiteAdapter = new SQLiteAdapter(context);
//        //创建SQLiteAdapter对象
//        sqLiteDb = sqLiteAdapter.getSqliteDb();          //得到SQLite实例
//        sqLiteDb.delete(TABLE_NAME, "_id=" + id, null);
//        return true;
//    }

//    public Cursor queryIDN(Context context, String str) {
//        sqLiteAdapter = new SQLiteAdapter(context);
//        //创建SQLiteAdapter对象
//        sqLiteDb = sqLiteAdapter.getSqliteDb();          //得到SQLite实例
//        Cursor cursor = sqLiteDb.query(TABLE_NAME, null, "_id= " + str, null, null, null, null);                 //获取注册的所有用户数据
//        return cursor;
//    }

    public Cursor queryName(Context context, String str) {
        sqLiteAdapter = new SQLiteAdapter(context);
        //创建SQLiteAdapter对象
        sqLiteDb = sqLiteAdapter.getSqliteDb();          //得到SQLite实例
        Cursor cursor = sqLiteDb.query(TABLE_NAME, null, "qc_name like '%" + str + "%'", null, null, null, null);                 //获取注册的所有用户数据
        return cursor;
    }
}