package com.a5402technologies.shadowsofbrimstonecompanion;

import android.arch.persistence.room.TypeConverter;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GithubTypeConverters {

    static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<String> stringToStringList(String data) {
        if (null == data) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<String>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String StringListToString(ArrayList<String> stringList) {
        return gson.toJson(stringList);
    }

    @TypeConverter
    public static ArrayList<GearBase> stringToGearList(String data) {
        if (null == data) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<GearBase>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String GearListToString(ArrayList<SobCharacter> sobCharacters) {
        return gson.toJson(sobCharacters);
    }
}
