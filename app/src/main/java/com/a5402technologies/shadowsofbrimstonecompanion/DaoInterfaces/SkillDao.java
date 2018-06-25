package com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;

import java.util.List;

@Dao
public interface SkillDao {
    @Insert
    void insert(Skill skill);

    @Query("DELETE FROM skill_table")
    void deleteAllSkill();

    @Query("SELECT skill_name FROM skill_table ORDER BY skill_name ASC")
    LiveData<List<String>> getAllSkillNames();

    @Query("SELECT * FROM skill_table ORDER BY skill_name ASC")
    LiveData<List<Skill>> getAllSkill();
}
