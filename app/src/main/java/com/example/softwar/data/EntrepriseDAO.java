package com.example.softwar.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.softwar.data.Entreprise;


import java.util.List;

@Dao
public interface EntrepriseDAO {

    @Query("SELECT * FROM Entreprise")
    List<Entreprise> getAll();

    @Insert
    void insert(Entreprise ent);

    @Insert
    long[] insertAll(Entreprise... ent);

    @Delete
    void delete(Entreprise ent);

    @Update
    void update(Entreprise ent);

}

