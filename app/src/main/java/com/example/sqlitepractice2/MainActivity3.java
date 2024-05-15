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

public class MainActivity3 extends AppCompatActivity {

    Button btn_edit;
    EditText et_id, et_name, et_nim;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn_edit = findViewById(R.id.btn_edit);
        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_nim = findViewById(R.id.et_nim);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(et_id.getText());
                String name = String.valueOf(et_name.getText());
                String nim = String.valueOf(et_nim.getText());

                dbHelper = new DatabaseHelper(MainActivity3.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("nim", nim);
                long updateId = db.update("users", values, "id = ?", new String[]{id});
                db.close();
                if (updateId != -1) {
                    Toast.makeText(MainActivity3.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Failed to update data", Toast.LENGTH_SHORT).show();
                }

                Intent resultIntent = new Intent();
                setResult(101, resultIntent);
                finish();

            }
        });

    }
}