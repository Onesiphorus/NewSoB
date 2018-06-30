package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.SetListEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

@Entity(tableName = "attachment_table")
public class Attachment implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "attachment_name")
    private String name;
    @NonNull
    @ColumnInfo(name = "cost")
    private Integer cost = 0;
    @NonNull
    @ColumnInfo(name = "sell")
    private Integer sell = 0;
    @NonNull
    @ColumnInfo(name = "weight")
    private Integer weight = 0;
    @NonNull
    @ColumnInfo(name = "darkStone")
    private Integer darkStone = 0;
    @ColumnInfo(name = "modifiers")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> modifiers;
    @ColumnInfo(name = "trait_restrictions")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> restrictions;
    @NonNull
    @ColumnInfo(name = "set")
    private String set = SetListEnum.BASE.code();
    @NonNull
    @ColumnInfo(name = "slots_required")
    private Integer slotsRequired;
    @ColumnInfo(name = "penalties")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "artifact")
    private Boolean artifact = Boolean.FALSE;
    @ColumnInfo(name = "armor")
    private Integer armor;
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor;
    @NonNull
    @ColumnInfo(name = "trederra_artifact")
    private Boolean trederraArtifact = FALSE;
    @NonNull
    @ColumnInfo(name = "cynder_artifact")
    private Boolean cynderArtifact = FALSE;
    @NonNull
    @ColumnInfo(name = "targa_artifact")
    private Boolean targaArtifact = FALSE;
    @NonNull
    @ColumnInfo(name = "jargono_artifact")
    private Boolean jargonoArtifact = FALSE;
    @NonNull
    @ColumnInfo(name = "derelict_artifact")
    private Boolean derelictArtifact = FALSE;
    @NonNull
    @ColumnInfo(name = "required_darkstone")
    private Integer requiredDarkStoneToAttach = 0;

    public Attachment(@NonNull String name, @NonNull Integer slotsRequired) {
        this.name = name;
        this.slotsRequired = slotsRequired;
        this.modifiers = new ArrayList<>(0);
        this.penalties = new ArrayList<>(0);
        this.restrictions = new ArrayList<>(0);
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getCost() {
        return cost;
    }

    public void setCost(@NonNull Integer cost) {
        this.cost = cost;
    }

    @NonNull
    public Integer getSell() {
        return sell;
    }

    public void setSell(@NonNull Integer sell) {
        this.sell = sell;
    }

    @NonNull
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(@NonNull Integer weight) {
        this.weight = weight;
    }

    @NonNull
    public Integer getDarkStone() {
        return darkStone;
    }

    public void setDarkStone(@NonNull Integer darkStone) {
        this.darkStone = darkStone;
    }

    public ArrayList<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(ArrayList<String> modifiers) {
        this.modifiers = modifiers;
    }

    public ArrayList<String> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(ArrayList<String> restrictions) {
        this.restrictions = restrictions;
    }

    @NonNull
    public String getSet() {
        return set;
    }

    public void setSet(@NonNull String set) {
        this.set = set;
    }

    @NonNull
    public Integer getSlotsRequired() {
        return slotsRequired;
    }

    public void setSlotsRequired(@NonNull Integer slotsRequired) {
        this.slotsRequired = slotsRequired;
    }

    public ArrayList<String> getPenalties() {
        return penalties;
    }

    public void setPenalties(ArrayList<String> penalties) {
        this.penalties = penalties;
    }

    @NonNull
    public Boolean getArtifact() {
        return artifact;
    }

    public void setArtifact(@NonNull Boolean artifact) {
        this.artifact = artifact;
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

    @NonNull
    public Boolean getTrederraArtifact() {
        return trederraArtifact;
    }

    public void setTrederraArtifact(@NonNull Boolean trederraArtifact) {
        this.trederraArtifact = trederraArtifact;
    }

    @NonNull
    public Boolean getCynderArtifact() {
        return cynderArtifact;
    }

    public void setCynderArtifact(@NonNull Boolean cynderArtifact) {
        this.cynderArtifact = cynderArtifact;
    }

    @NonNull
    public Boolean getTargaArtifact() {
        return targaArtifact;
    }

    public void setTargaArtifact(@NonNull Boolean targaArtifact) {
        this.targaArtifact = targaArtifact;
    }

    @NonNull
    public Boolean getJargonoArtifact() {
        return jargonoArtifact;
    }

    public void setJargonoArtifact(@NonNull Boolean jargonoArtifact) {
        this.jargonoArtifact = jargonoArtifact;
    }

    @NonNull
    public Boolean getDerelictArtifact() {
        return derelictArtifact;
    }

    public void setDerelictArtifact(@NonNull Boolean derelictArtifact) {
        this.derelictArtifact = derelictArtifact;
    }

    @NonNull
    public Integer getRequiredDarkStoneToAttach() {
        return requiredDarkStoneToAttach;
    }

    public void setRequiredDarkStoneToAttach(@NonNull Integer requiredDarkStoneToAttach) {
        this.requiredDarkStoneToAttach = requiredDarkStoneToAttach;
    }

    public void addRestriction(String restriction) {
        this.restrictions.add(restriction);
    }

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public void addPenalty(String penalty) {
        this.penalties.add(penalty);
    }
}
