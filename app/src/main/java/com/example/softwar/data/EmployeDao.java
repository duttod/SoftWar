package com.example.softwar.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

public interface EmployeDao {

    @Query("SELECT * FROM Employe")
    List<Employe> getAll();

    @Insert
    void insert(Employe employe);

    @Insert
    long[] insertAll(Employe... employes);

    @Delete
    void delete(Employe employe);

    @Update
    void update(Employe employe);

}
