package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;

import java.util.List;

@Dao
public interface RangedWeaponDao {
    @Insert
    void insert(RangedWeapon rangedWeapon);

    @Query("DELETE FROM ranged_weapon_table")
    void deleteAllRangedWeapons();

    @Query("SELECT ranged_name FROM ranged_weapon_table ORDER BY ranged_name ASC")
    LiveData<List<String>> getAllRangedWeaponNames();

    @Query("SELECT * FROM ranged_weapon_table ORDER BY ranged_name ASC")
    LiveData<List<RangedWeapon>> getAllRangedWeapons();
}
