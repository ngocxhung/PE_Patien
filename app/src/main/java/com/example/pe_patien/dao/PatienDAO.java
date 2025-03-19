package com.example.pe_patien.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pe_patien.entity.Patien;

import java.util.List;

@Dao
public interface PatienDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Patien patien);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Iterable<Patien> patienList);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Patien patien);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAll(Iterable<Patien> patienList);
    @Delete
    void delete(Patien patien);
    @Query("SELECT * FROM Patien")
    List<Patien> getAll();
    @Query("SELECT * FROM Patien WHERE PatienCode = :code")
    Patien getByCode(String code);


}
