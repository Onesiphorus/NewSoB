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
public class PermanentCondition implements Serializable {

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
    @NonNull
    @ColumnInfo(name = "condition_type")
    private String type;
    @NonNull
    @ColumnInfo(name = "armor")
    private Integer armor = 7;
    @NonNull
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor = 7;
    @NonNull
    @ColumnInfo(name = "defense")
    private Integer defense = 7;
    @NonNull
    @ColumnInfo(name = "willpower")
    private Integer willpwer = 7;
    @NonNull
    @ColumnInfo(name = "temporary_boost")
    private Boolean temporaryBoost = FALSE;

    public PermanentCondition(@NonNull String name, @NonNull String type) {
        this.name = name;
        this.type = type;
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

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @NonNull
    public Integer getArmor() {
        return armor;
    }

    public void setArmor(@NonNull Integer armor) {
        this.armor = armor;
    }

    @NonNull
    public Integer getSpiritArmor() {
        return spiritArmor;
    }

    public void setSpiritArmor(@NonNull Integer spiritArmor) {
        this.spiritArmor = spiritArmor;
    }

    @NonNull
    public Integer getDefense() {
        return defense;
    }

    public void setDefense(@NonNull Integer defense) {
        this.defense = defense;
    }

    @NonNull
    public Integer getWillpwer() {
        return willpwer;
    }

    public void setWillpwer(@NonNull Integer willpwer) {
        this.willpwer = willpwer;
    }

    @NonNull
    public Boolean getTemporaryBoost() {
        return temporaryBoost;
    }

    public void setTemporaryBoost(@NonNull Boolean temporaryBoost) {
        this.temporaryBoost = temporaryBoost;
    }
}
