package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;

import java.util.List;

@Dao
public interface GearBaseDao {

    @Insert
    void insert(GearBase gearBase);

    @Query("DELETE FROM gear_base_table")
    void deleteAllGearBase();

    @Query("SELECT gear_name FROM gear_base_table ORDER BY gear_name ASC")
    LiveData<List<String>> getAllGearNames();

    @Query("SELECT * from gear_base_table ORDER BY gear_name ASC")
    LiveData<List<GearBase>> getAllGear();

    @Query("SELECT * FROM gear_base_table WHERE gear_name = :name")
    LiveData<GearBase> getGearByName(String name);
}
