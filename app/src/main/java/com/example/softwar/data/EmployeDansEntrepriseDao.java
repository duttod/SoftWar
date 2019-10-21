package com.example.softwar.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeDansEntrepriseDao {

    @Query("SELECT * FROM EmployeDansEntreprise")
    List<EmployeDansEntreprise> getAll();

    @Query("SELECT * FROM EmployeDansEntreprise WHERE nomEntreprise = :nom")
    List<EmployeDansEntreprise> getEmployeDuneEntreprise(String nom);

    @Insert
    void insert(EmployeDansEntreprise ent);

    @Insert
    long[] insertAll(EmployeDansEntreprise... ent);

    @Delete
    void delete(EmployeDansEntreprise ent);

    @Update
    void update(EmployeDansEntreprise ent);


}
