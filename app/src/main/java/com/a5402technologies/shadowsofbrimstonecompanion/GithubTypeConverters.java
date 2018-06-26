package com.a5402technologies.shadowsofbrimstonecompanion;

import android.arch.persistence.room.TypeConverter;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.CharacterClassEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
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
    public static String GearListToString(ArrayList<GearBase> gearBases) {
        return gson.toJson(gearBases);
    }

    @TypeConverter
    public static ArrayList<Clothing> stringToClothingList(String data) {
        if (null == data) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<Clothing>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String ClothingListToString(ArrayList<Clothing> clothing) {
        return gson.toJson(clothing);
    }

    @TypeConverter
    public static ArrayList<MeleeWeapon> stringToMeleeList(String data) {
        if (null == data) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<MeleeWeapon>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String MeleeListToString(ArrayList<MeleeWeapon> meleeWeapons) {
        return gson.toJson(meleeWeapons);
    }

    @TypeConverter
    public static ArrayList<RangedWeapon> stringToRangedList(String data) {
        if (null == data) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<RangedWeapon>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String RangedListToString(ArrayList<RangedWeapon> rangedWeapons) {
        return gson.toJson(rangedWeapons);
    }

    @TypeConverter
    public static CharacterClass stringToCharacterClass(String data) {
        if (null == data) {
            return new CharacterClass("null");
        }

        Type listType = new TypeToken<CharacterClass>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String CharacterClassToString(CharacterClass characterClass) {
        return gson.toJson(characterClass);
    }

    @TypeConverter
    public static ArrayList<Skill> stringToSkillList(String data) {
        if (null == data) {
            return new ArrayList<>();
        }

        Type listType = new TypeToken<ArrayList<Skill>>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String SkillListToString(ArrayList<Skill> skillList) {
        return gson.toJson(skillList);
    }

    @TypeConverter
    public static CharacterClassEnum stringToCharacterClassEnum(String data) {
        if (null == data) {
            return CharacterClassEnum.ANY;
        }

        Type listType = new TypeToken<CharacterClassEnum>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String CharacterClassEnumToString(CharacterClassEnum characterClassEnum) {
        return gson.toJson(characterClassEnum);
    }

    @TypeConverter
    public static RangedWeapon stringToRangedWeapon(String data) {
        if (null == data) {
            return new RangedWeapon("",0,0);
        }

        Type listType = new TypeToken<CharacterClass>() {}.getType();

        return gson.fromJson(data,listType);
    }

    @TypeConverter
    public static String RangedWeaponToString(RangedWeapon rangedWeapon) {
        return gson.toJson(rangedWeapon);
    }
}
