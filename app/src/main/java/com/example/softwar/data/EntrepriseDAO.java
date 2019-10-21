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

    @Query("SELECT * FROM EntreprisePerso")
    List<EntreprisePerso> getAll();

    @Insert
    void insert(EntreprisePerso ent);

    @Insert
    long[] insertAll(EntreprisePerso... ent);

    @Delete
    void delete(EntreprisePerso ent);

    @Update
    void update(EntreprisePerso ent);

}

