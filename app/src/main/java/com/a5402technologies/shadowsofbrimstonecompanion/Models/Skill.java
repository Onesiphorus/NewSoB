package com.a5402technologies.shadowsofbrimstonecompanion.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "skill_table")
public class Skill {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "skill_name")
    private String name;
    @NonNull
    @ColumnInfo(name = "type")
    private String type;
    @NonNull
    @ColumnInfo(name = "class_restriction")
    private String classRestriction;
    @ColumnInfo(name = "modifiers")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> modifiers;
    @TypeConverters(GithubTypeConverters.class)
    @ColumnInfo(name = "penalties")
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "level")
    private Integer level = 0;

    public Skill(@NonNull String name, @NonNull String classRestriction, @NonNull String type) {
        this.name = name;
        this.modifiers = new ArrayList<>(0);
        this.penalties = new ArrayList<>(0);
        this.classRestriction = classRestriction;
        this.type = type;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public String getClassRestriction() {
        return classRestriction;
    }

    public void setClassRestriction(@NonNull String classRestriction) {
        this.classRestriction = classRestriction;
    }

    public ArrayList<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(ArrayList<String> modifiers) {
        this.modifiers = modifiers;
    }

    public ArrayList<String> getPenalties() {
        return penalties;
    }

    public void setPenalties(ArrayList<String> penalties) {
        this.penalties = penalties;
    }

    @NonNull
    public Integer getLevel() {
        return level;
    }

    public void setLevel(@NonNull Integer level) {
        this.level = level;
    }
    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }
    public void addPenalty(String penalty) {
        this.penalties.add(penalty);
    }
}
