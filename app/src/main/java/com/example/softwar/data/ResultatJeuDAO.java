package com.example.softwar.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ResultatJeuDAO {

    @Query("SELECT * FROM ResultatJeu")
    List<ResultatJeu> getAll();

    @Insert
    void insert(ResultatJeu res);

    @Insert
    long[] insertAll(ResultatJeu... res);

    @Delete
    void delete(ResultatJeu res);

    @Update
    void update(ResultatJeu res);

}