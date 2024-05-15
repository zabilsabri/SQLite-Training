package com.example.sqlitepractice2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_name, tv_nim, tv_id;
    Button btn_add, btn_delete, btn_edit;
    DatabaseHelper dbHelper;
    final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == 101){
                    getData();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_id = findViewById(R.id.tv_id);
        tv_name = findViewById(R.id.tv_name);
        tv_nim = findViewById(R.id.tv_nim);
        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);

        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        getData();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                resultLauncher.launch(intent);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                resultLauncher.launch(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("users", null, null);
                tv_id.setText("-");
                tv_name.setText("-");
                tv_nim.setText("-");
            }
        });

    }

    public void getData(){
        dbHelper = new DatabaseHelper(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("users", null, null, null, null, null, null);
        StringBuilder id = new StringBuilder();
        StringBuilder name = new StringBuilder();
        StringBuilder nim = new StringBuilder();
        if(cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String id_db = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name_db = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String nim_db = cursor.getString(cursor.getColumnIndex("nim"));
                id.append(id_db).append("\n");
                name.append(name_db).append("\n");
                nim.append(nim_db).append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        tv_id.setText(id.toString());
        tv_name.setText(name.toString());
        tv_nim.setText(nim.toString());
    }
}