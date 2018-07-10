package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "transport_table")
public class Transport implements Serializable{

    @NonNull
    @ColumnInfo(name = "gear_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<GearBase> gear = new ArrayList<>();
    @NonNull
    @ColumnInfo(name = "clothing_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Clothing> clothing = new ArrayList<>();
    @NonNull
    @ColumnInfo(name = "melee_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<MeleeWeapon> meleeWeapons = new ArrayList<>();
    @NonNull
    @ColumnInfo(name = "ranged_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<RangedWeapon> rangedWeapons = new ArrayList<>();
    @NonNull
    @ColumnInfo(name = "attachments")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Attachment> attachments;
    @NonNull
    @ColumnInfo(name = "side_bag_items")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> sideBagItems;
}
