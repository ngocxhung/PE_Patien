package com.example.pe_patien;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pe_patien.entity.Patien;
import com.example.pe_patien.repository.PatienRepository;
import com.example.pe_patien.utils.Validation;

public class AddPatienActivity extends AppCompatActivity {
    private PatienRepository patienRepository;
    private EditText etPatienCode;
    private EditText etPatienName;
    private EditText etPatienDOB;
    private EditText etPatienAge;
    private Button btSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_patien);
        // Initialize the repository
        patienRepository = new PatienRepository(this);
        // Initialize the views
        etPatienCode = findViewById(R.id.etPatienCode);
        etPatienName = findViewById(R.id.etPatienName);
        etPatienDOB = findViewById(R.id.etPatienDOB);
        etPatienAge = findViewById(R.id.etPatienAge);
        btSave = findViewById(R.id.btSave);
        // Set the click listener for the save button
        btSave.setOnClickListener(v -> {
                    // Get the values from the EditText fields
                    String code = etPatienCode.getText().toString();
                    String name = etPatienName.getText().toString();
                    String dob = etPatienDOB.getText().toString();
                    int age = Integer.parseInt(etPatienAge.getText().toString());
                    if (code.isEmpty() || name.isEmpty() || dob.isEmpty() || age == 0) {
                        Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!Validation.validatePatientCode(code)){

                        Toast.makeText(this, "Invalid patient code. Code must start with P and don't have more than 10 character", Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(patienRepository.getByCode(code) != null){
                        Toast.makeText(this, "Patient code already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(!Validation.validateDOB(dob)){
                        Toast.makeText(this, "Invalid DOB. DOB must be in the format DD/MM/YYYY and year must be after 1920", Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(!Validation.validateAge(String.valueOf(age))){
                        Toast.makeText(this, "Invalid age. Age must be between 20 and 100", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    try {
                        Patien patien = new Patien(code, name, dob, age);
                        patienRepository.insert(patien);
                        Toast.makeText(this, "Patien saved successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(this, "Error saving patien", Toast.LENGTH_SHORT).show();
                    }



                });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}