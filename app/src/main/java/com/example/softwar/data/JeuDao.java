package com.example.softwar.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface JeuDao {


    @Query("SELECT * FROM Jeu")
    List<Jeu> getAll();

    @Insert
    void insert(Jeu jeu);

    @Insert
    long[] insertAll(Jeu... jeu);

    @Delete
    void delete(Jeu jeu);

    @Update
    void update(Jeu jeu);

}
