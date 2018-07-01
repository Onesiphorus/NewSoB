package com.a5402technologies.shadowsofbrimstonecompanion.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "permanent_condition_table")
public class PermanentCondition implements Serializable{

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "condition_name")
    private String name;
    @NonNull
    @ColumnInfo(name = "injury")
    private Boolean isInjury;
    @NonNull
    @ColumnInfo(name = "madness")
    private Boolean isMadness;
    @NonNull
    @ColumnInfo(name = "mutation")
    private Boolean isMutation;
    @NonNull
    @ColumnInfo(name = "penalties")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "modifiers")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> modifiers;

    public PermanentCondition(@NonNull String name, @NonNull Boolean isInjury, @NonNull Boolean isMadness, @NonNull Boolean isMutation) {
        this.name = name;
        this.isInjury = isInjury;
        this.isMadness = isMadness;
        this.isMutation = isMutation;
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
    public Boolean getInjury() {
        return isInjury;
    }

    public void setInjury(@NonNull Boolean injury) {
        isInjury = injury;
    }

    @NonNull
    public Boolean getMadness() {
        return isMadness;
    }

    public void setMadness(@NonNull Boolean madness) {
        isMadness = madness;
    }

    @NonNull
    public Boolean getMutation() {
        return isMutation;
    }

    public void setMutation(@NonNull Boolean mutation) {
        isMutation = mutation;
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
}
