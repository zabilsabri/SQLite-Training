package com.example.sqlitepractice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button btn_submit;
    EditText et_name, et_nim;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn_submit = findViewById(R.id.btn_submit);
        et_name = findViewById(R.id.et_name);
        et_nim = findViewById(R.id.et_nim);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(et_name.getText());
                String nim = String.valueOf(et_nim.getText());

                dbHelper = new DatabaseHelper(MainActivity2.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("nim", nim);
                long newRowId = db.insert("users", null, values);
                db.close();
                if (newRowId != -1) {
                    Toast.makeText(MainActivity2.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity2.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                }

                Intent resultIntent = new Intent();
                setResult(101, resultIntent);
                finish();
            }

        });
    }
}