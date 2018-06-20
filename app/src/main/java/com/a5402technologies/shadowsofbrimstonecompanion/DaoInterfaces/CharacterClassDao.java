package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;

import java.util.List;

@Dao
public interface CharacterClassDao {

    @Insert
    void insert(CharacterClass characterClass);

    @Query("DELETE FROM character_class_table")
    void deleteAllCharacterClasses();

    @Query("SELECT class_name FROM character_class_table ORDER BY class_name ASC")
    LiveData<List<String>> getAllCharacterClassNames();

    @Query("SELECT * FROM character_class_table ORDER BY class_name ASC")
    LiveData<List<CharacterClass>> getAllCharacterClasses();

}
