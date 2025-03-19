package com.example.pe_patien.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pe_patien.entity.Patien;

@Database(entities = {Patien.class}, version = 1, exportSchema = false)
public abstract class PatienRoomDatabase extends RoomDatabase {

    public abstract PatienDAO patienDAO();

    private static volatile PatienRoomDatabase INSTANCE;

    public static PatienRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (PatienRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PatienRoomDatabase.class, "patien_database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();

            }
        }
        return INSTANCE;
    }
}

