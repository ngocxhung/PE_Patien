package com.example.pe_patien.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Patien")
public class Patien {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "PatienCode")
    private String code;
    @ColumnInfo(name = "PatienName")
    private String name;
    @ColumnInfo(name = "PatienDOB")
    private String dob;
    @ColumnInfo(name = "PatienAge")
    private int age;

    public Patien(String code, String name, String dob, int age) {
        this.code = code;
        this.name = name;
        this.dob = dob;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
