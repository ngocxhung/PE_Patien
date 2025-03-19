package com.example.pe_patien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pe_patien.adapter.PatienAdapter;
import com.example.pe_patien.entity.Patien;
import com.example.pe_patien.repository.PatienRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PatienRepository patienRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        patienRepository = new PatienRepository(this);
        List<Patien> patienList = patienRepository.getAll();

        RecyclerView recyclerViewPatien = findViewById(R.id.recyclerViewPatien);
        recyclerViewPatien.setLayoutManager(new LinearLayoutManager(this));
        PatienAdapter patienAdapter = new PatienAdapter(patienList, this);
        recyclerViewPatien.setAdapter(patienAdapter);
        Button btnAddPatien = findViewById(R.id.btnAddPatien);
        btnAddPatien.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPatienActivity.class);
            startActivity(intent);
        });



    }
}