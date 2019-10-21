package com.example.softwar.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AleaDAO {

    @Query("SELECT * FROM Alea")
    List<Alea> getAll();

    @Insert
    void insert(Alea al);

    @Insert
    long[] insertAll(Alea... al);

    @Delete
    void delete(Alea al);

    @Update
    void update(Alea al);

}