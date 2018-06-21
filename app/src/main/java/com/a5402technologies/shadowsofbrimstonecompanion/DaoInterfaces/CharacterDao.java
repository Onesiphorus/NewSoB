package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

import java.util.List;

@Dao
public interface CharacterDao {

    @Insert
    void insert(SobCharacter character);

    @Query("DELETE FROM character_table")
    void deleteAllSobCharacters();

    @Query("DELETE FROM character_table WHERE character_name = :name")
    void deleteCharacterByName(String name);

    @Query("SELECT character_name from character_table ORDER BY character_name ASC")
    LiveData<List<String>> getAllCharacterNames();

    @Query("SELECT * FROM character_table WHERE character_name = :name")
    LiveData<SobCharacter> getCharacterByName(String name);

    @Query("SELECT * from character_table")
    LiveData<List<SobCharacter>> getAllSobCharacters();
}
