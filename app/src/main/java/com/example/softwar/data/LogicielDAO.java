package com.example.softwar.data;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.example.softwar.data.Entreprise;


import java.util.List;

@Dao
public interface LogicielDAO {
    @Query("SELECT * FROM Logiciel")
    List<Logiciel> getAll();

    @Query("SELECT * FROM Logiciel where nomLogiciel= :nl ")
    Logiciel getByEntreprise(String nl);

    @Insert
    void insert(Logiciel log);

    @Insert
    long[] insertAll(Logiciel... log);

    @Delete
    void delete(Logiciel log);

    @Update
    void update(Logiciel log);
}
