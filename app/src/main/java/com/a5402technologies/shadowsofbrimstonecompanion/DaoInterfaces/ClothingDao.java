package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

import java.util.List;

@Dao
public interface ClothingDao {
    @Insert
    void insert(Clothing clothing);

    @Query("DELETE FROM clothing_table")
    void deleteAllClothing();

    @Query("SELECT clothing_name FROM clothing_table ORDER BY clothing_name ASC")
    LiveData<List<String>> getAllClothingNames();

    @Query("SELECT * FROM clothing_table ORDER BY clothing_name ASC")
    LiveData<List<Clothing>> getAllClothing();

}
