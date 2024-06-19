package com.tranminhtruc.onthi1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.tranminhtruc.Adapter.adapter;
import com.tranminhtruc.Model.Database;
import com.tranminhtruc.Model.Dienthoai;
import com.tranminhtruc.onthi1.databinding.ActivityMain2Binding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

  ActivityMain2Binding binding;
    public Database db;
    adapter adapter;
    List<Dienthoai> dienThoais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prepareDb();
        loadData();
        addEvent();
    }

    private void addEvent() {
        binding.btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void prepareDb() {
        db = new Database(this);
        db.createSampleData();
    }

    public void loadData() {
        dienThoais = getDaFromDb();
        adapter = new adapter(MainActivity2.this, R.layout.list_item, dienThoais);
        binding.lvDienThoai.setAdapter(adapter);
    }

    private List<Dienthoai> getDaFromDb() {
        dienThoais = new ArrayList<>();
        Cursor cursor = db.QueryData("SELECT * FROM " + db.TBL_NAME);
        while (cursor.moveToNext()){
            dienThoais.add(new Dienthoai(cursor.getString(0),cursor.getString(1),cursor.getDouble(2)));
        }
        cursor.close();
        return dienThoais;
    }
}