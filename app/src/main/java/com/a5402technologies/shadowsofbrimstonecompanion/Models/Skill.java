package com.a5402technologies.shadowsofbrimstonecompanion.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "skill_table")
public class Skill implements Serializable{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "skill_name")
    private String name;
    @NonNull
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "modifiers")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> modifiers;
    @TypeConverters(GithubTypeConverters.class)
    @ColumnInfo(name = "penalties")
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "level")
    private Integer level = 0;
    @ColumnInfo(name = "armor")
    private Integer armor;
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor;

    public Skill(@NonNull String name, @NonNull String type) {
        this.name = name;
        this.modifiers = new ArrayList<>(0);
        this.penalties = new ArrayList<>(0);
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

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    public Integer getSpiritArmor() {
        return spiritArmor;
    }

    public void setSpiritArmor(Integer spiritArmor) {
        this.spiritArmor = spiritArmor;
    }
}
