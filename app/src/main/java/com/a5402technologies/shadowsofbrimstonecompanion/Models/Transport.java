package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "transport_table")
public class Transport implements Serializable {

    @NonNull
    @ColumnInfo(name = "gear_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<GearBase> gear;
    @NonNull
    @ColumnInfo(name = "clothing_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Clothing> clothing;
    @NonNull
    @ColumnInfo(name = "melee_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<MeleeWeapon> meleeWeapons;
    @NonNull
    @ColumnInfo(name = "ranged_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<RangedWeapon> rangedWeapons;
    @NonNull
    @ColumnInfo(name = "attachments")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Attachment> attachments;
    @NonNull
    @ColumnInfo(name = "side_bag_items")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> sideBagItems;

    public Transport() {
        gear = new ArrayList<>(0);
        clothing = new ArrayList<>(0);
        meleeWeapons = new ArrayList<>(0);
        rangedWeapons = new ArrayList<>(0);
        attachments = new ArrayList<>(0);
        sideBagItems = new ArrayList<>(0);
    }

    @NonNull
    public ArrayList<GearBase> getGear() {
        return gear;
    }

    public void setGear(@NonNull ArrayList<GearBase> gear) {
        this.gear = gear;
    }

    @NonNull
    public ArrayList<Clothing> getClothing() {
        return clothing;
    }

    public void setClothing(@NonNull ArrayList<Clothing> clothing) {
        this.clothing = clothing;
    }

    @NonNull
    public ArrayList<MeleeWeapon> getMeleeWeapons() {
        return meleeWeapons;
    }

    public void setMeleeWeapons(@NonNull ArrayList<MeleeWeapon> meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    @NonNull
    public ArrayList<RangedWeapon> getRangedWeapons() {
        return rangedWeapons;
    }

    public void setRangedWeapons(@NonNull ArrayList<RangedWeapon> rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    @NonNull
    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(@NonNull ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    @NonNull
    public ArrayList<String> getSideBagItems() {
        return sideBagItems;
    }

    public void setSideBagItems(@NonNull ArrayList<String> sideBagItems) {
        this.sideBagItems = sideBagItems;
    }
}
