package com.example.pe_patien.repository;

import android.content.Context;

import com.example.pe_patien.dao.PatienDAO;
import com.example.pe_patien.dao.PatienRoomDatabase;
import com.example.pe_patien.entity.Patien;

import java.util.ArrayList;
import java.util.List;

public class PatienRepository {
    private PatienDAO patienDAO;

    public PatienRepository(Context context) {
        PatienRoomDatabase db = PatienRoomDatabase.getINSTANCE(context);
        this.patienDAO = db.patienDAO();
    }

    public void insert(Patien patien) {
        patienDAO.insert(patien);
    }
    public void insertAll(List<Patien> patienList) {
        patienDAO.insertAll(patienList);
    }

    public void delete(Patien patien) {
        patienDAO.delete(patien);
    }
    public void update(Patien patien) {
        patienDAO.update(patien);
    }
    public List<Patien> getAll() {
       return patienDAO.getAll();
    }
    public Patien getByCode(String code) {
        return patienDAO.getByCode(code);
    }
    public void insertSample() {
        Patien patient1 = new Patien("P001", "John Doe", "1990-01-01", 35);
        Patien patient2 = new Patien("P002", "Jane Smith", "1985-05-15", 40);
        Patien patient3 = new Patien("P003", "Bob Johnson", "2000-12-31", 24);
        Patien patient4 = new Patien("P004", "Alice Brown", "1995-07-22", 29);
        Patien patient5 = new Patien("P005", "Mike Wilson", "1980-03-10", 45);
        insert(patient1);
        insert(patient2);
        insert(patient3);
        insert(patient4);
        insert(patient5);



    }
}