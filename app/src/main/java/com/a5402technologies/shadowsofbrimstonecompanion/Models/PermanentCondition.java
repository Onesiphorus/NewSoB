package com.a5402technologies.shadowsofbrimstonecompanion.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

@Entity(tableName = "permanent_condition_table")
public class PermanentCondition implements Serializable{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "condition_name")
    private String name;
    @NonNull
    @ColumnInfo(name = "penalties")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "modifiers")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> modifiers;
    @NonNull
    @ColumnInfo(name = "botched")
    private Boolean isBotched = FALSE;

    public PermanentCondition(@NonNull String name) {
        this.name = name;
        this.penalties = new ArrayList<>(0);
        this.modifiers = new ArrayList<>(0);
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public ArrayList<String> getPenalties() {
        return penalties;
    }

    public void setPenalties(@NonNull ArrayList<String> penalties) {
        this.penalties = penalties;
    }

    @NonNull
    public ArrayList<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(@NonNull ArrayList<String> modifiers) {
        this.modifiers = modifiers;
    }

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public void addPenalty(String penalty) {
        this.penalties.add(penalty);
    }

    @NonNull
    public Boolean getBotched() {
        return isBotched;
    }

    public void setBotched(@NonNull Boolean botched) {
        isBotched = botched;
    }
}
