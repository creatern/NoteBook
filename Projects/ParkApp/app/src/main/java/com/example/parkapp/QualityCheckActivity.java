package com.example.parkapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class QualityCheckActivity extends AppCompatActivity {
    private ListView myListView;
    private Button myButtonSearch;
    private EditText myQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_check);
//        findViews();
//        myQuery = (EditText) findViewById(R.id.edtQuery);
//        myButtonSearch = (Button) findViewById(R.id.btnSearch);
//        myButtonSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                QualityCheck qualityCheck = new QualityCheck();
//                Cursor cursor = qualityCheck.queryName(QualityCheckActivity.this, myQuery.getText().toString());
//                if (cursor != null && cursor.getCount() >= 0) {
//                    SimpleCursorAdapter adapter = new SimpleCursorAdapter(QualityCheckActivity.this,
//                            R.layout.listitem_quality_check, //前面设计好的布局格式文件名listitem.xml
//                            cursor, //数据库的Cursors对象
//                            new String[]{"qc_id", "qc_name", "qc_date", "app_id", "qc_result"}, //各字段
//                            new int[]{R.id.txt_qc_id, R.id.txt_qc_name, R.id.txt_qc_date, R.id.txt_app_id, R.id.txt_qc_result}, 0);
//                    myListView.setAdapter(adapter); //将adapter增加到listview01中
//                }
//            }
//        });
//    }
//
//    private void findViews() {
//        myListView = (ListView) findViewById(R.id.lstQualityCheck);
//        QualityCheck qualityCheck = new QualityCheck();
//        Cursor cursor = qualityCheck.queryAll(this);
//        if (cursor != null && cursor.getCount() >= 0) {
//            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                    R.layout.listitem_quality_check, //前面设计好的布局格式文件名listitem.xml
//                    cursor, //数据库的Cursors对象
//                    new String[]{"qc_id", "qc_name", "qc_date", "app_id", "qc_result"}, //各字段
//                    new int[]{R.id.txt_qc_id, R.id.txt_qc_name, R.id.txt_qc_date, R.id.txt_app_id, R.id.txt_qc_result}, 0);
//            myListView.setAdapter(adapter); //将adapter增加到listview01中
//        }
//
//    }
    }
}