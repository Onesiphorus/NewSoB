package com.a5402technologies.shadowsofbrimstonecompanion.Models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

    public Skill(@NonNull String name) {
        this.name = name;
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
}
