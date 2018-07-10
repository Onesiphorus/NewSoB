package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;

@Entity(tableName = "ally_class_table")
public class AllyClass implements Serializable {

    @TypeConverters(GithubTypeConverters.class)

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ally_name")
    private String name;

    public AllyClass(@NonNull String name) {
        this.name = name;
    }
}
