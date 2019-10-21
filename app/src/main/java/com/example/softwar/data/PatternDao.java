package com.example.softwar.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface PatternDao {
    @Query("SELECT * FROM Pattern")
    List<Pattern> getAll();

    @Insert
    void insert(Pattern pat);

    @Insert
    long[] insertAll(Pattern... pat);

    @Delete
    void delete(Pattern pat);

    @Update
    void update(Pattern pat);
}
