package com.example.parkapp.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by J on 2019/10/29.
 */

public class SQLiteAdapter {
    //声明数据库的基本信息
    private static final int DB_VERSION = 1;            //数据库版本
    private static final String SQLITE_NAME = "SqliteBookImage";    //数据库名称
    //声明操作Sqlite数据库的实例
    private SQLiteDatabase sqliteDb;
    private DBOpenHelper sqliteHelper;

    //构造方法
    public SQLiteAdapter(Context context) {
        sqliteHelper = new DBOpenHelper(context, SQLITE_NAME, null, DB_VERSION);
        sqliteDb = sqliteHelper.getWritableDatabase();    //获得可写的数据库,此时有执行OnCreate方法
    }

    //自定义的帮助类
    private static class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, dbname, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table quality_checks( integer primary key autoincrement,qc_id text,qc_name text not null,qc_date text,app_id text,qc_result text);");
            db.execSQL("insert into quality_checks values(null,'Q1001','张三','2024-01-12','A1001','合格');");
            db.execSQL("insert into quality_checks values(null,'Q1002','张三','2024-01-22','A1002','合格');");
            db.execSQL("insert into quality_checks values(null,'Q1003','李四','2024-01-24','A1003','不良');");
            db.execSQL("insert into quality_checks values(null,'Q1004','赵二','2024-01-21','A1005','合格');");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists quality_checks");
            onCreate(db);
        }
    }

    //获取SQLite数据库实例
    public SQLiteDatabase getSqliteDb() {
        return sqliteDb;
    }
}
