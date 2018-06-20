package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;

import java.util.List;

@Dao
public interface MeleeWeaponDao {
    @Insert
    void insert(MeleeWeapon meleeWeapon);

    @Query("DELETE FROM melee_weapon_table")
    void deleteAllMeleeWeapons();

    @Query("SELECT melee_name FROM melee_weapon_table ORDER BY melee_name ASC")
    LiveData<List<String>> getAllMeleeWeaponNames();

    @Query("SELECT * FROM melee_weapon_table ORDER BY melee_name ASC")
    LiveData<List<MeleeWeapon>> getAllMeleeWeapons();
}
