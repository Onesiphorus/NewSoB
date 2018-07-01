package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.PermanentCondition;

import java.util.List;

@Dao
public interface PermanentConditionDao {
    @Insert
    void insert(PermanentCondition permanentCondition);

    @Query("DELETE FROM permanent_condition_table")
    void deleteAllPermanentCondition();

    @Query("SELECT condition_name FROM permanent_condition_table ORDER BY condition_name ASC")
    LiveData<List<String>> getAllPermanentConditionNames();

    @Query("SELECT * FROM permanent_condition_table ORDER BY condition_name ASC")
    LiveData<List<PermanentCondition>> getAllPermanentCondition();

    @Query("SELECT * FROM permanent_condition_table WHERE condition_name = :name")
    LiveData<PermanentCondition> getPermanentConditionByName(String name);

}
