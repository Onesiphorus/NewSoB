package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;


@Entity(tableName="character_class_table")
public class CharacterClass implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="class_name")
    private String className;
    @NonNull
    @ColumnInfo(name="agility")
    private Integer Agility = 0;
    @NonNull
    @ColumnInfo(name="cunning")
    private Integer Cunning = 0;
    @NonNull
    @ColumnInfo(name="spirit")
    private Integer Spirit = 0;
    @NonNull
    @ColumnInfo(name="strength")
    private Integer Strength = 0;
    @NonNull
    @ColumnInfo(name="lore")
    private Integer Lore = 0;
    @NonNull
    @ColumnInfo(name="luck")
    private Integer Luck = 0;
    @NonNull
    @ColumnInfo(name="health")
    private Integer Health = 0;
    @NonNull
    @ColumnInfo(name="sanity")
    private Integer Sanity = 0;
    @NonNull
    @ColumnInfo(name="defense")
    private Integer Defense = 0;
    @NonNull
    @ColumnInfo(name="willpower")
    private Integer Willpower = 0;
    @NonNull
    @ColumnInfo(name="ranged_to_hit")
    private Integer RangedToHit = 0;
    @NonNull
    @ColumnInfo(name="melee_to_hit")
    private Integer MeleeToHit = 0;
    @NonNull
    @ColumnInfo(name="combat")
    private Integer Combat = 0;
    @NonNull
    @ColumnInfo(name="initiative")
    private Integer Initiative = 0;
    @NonNull
    @ColumnInfo(name="max_grit")
    private Integer MaxGrit = 0;
    @NonNull
    @ColumnInfo(name = "traits")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> traits;
    @NonNull
    @ColumnInfo(name = "start_gear")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<GearBase> startingGear;
    @NonNull
    @ColumnInfo(name = "start_melee")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<MeleeWeapon> startingMelee;
    @NonNull
    @ColumnInfo(name = "start_ranged")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<RangedWeapon> startingRanged;
    @NonNull
    @ColumnInfo(name = "start_clothing")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Clothing> startingClothing;

    public CharacterClass(@NonNull String className) {
        this.className = className;
        this.traits = new ArrayList<>(0);
        this.startingGear = new ArrayList<>(0);
        this.startingClothing = new ArrayList<>(0);
        this.startingMelee = new ArrayList<>(0);
        this.startingRanged = new ArrayList<>(0);
    }

    public CharacterClass(@NonNull String className, @NonNull Integer agility, @NonNull Integer cunning,
                          @NonNull Integer spirit, @NonNull Integer strength, @NonNull Integer lore,
                          @NonNull Integer luck, @NonNull Integer health, @NonNull Integer sanity,
                          @NonNull Integer defense, @NonNull Integer willpower, @NonNull Integer rangedToHit,
                          @NonNull Integer meleeToHit, @NonNull Integer combat, @NonNull Integer initiative,
                          @NonNull Integer maxGrit, @NonNull ArrayList<String> traits) {
        this.className = className;
        Agility = agility;
        Cunning = cunning;
        Spirit = spirit;
        Strength = strength;
        Lore = lore;
        Luck = luck;
        Health = health;
        Sanity = sanity;
        Defense = defense;
        Willpower = willpower;
        RangedToHit = rangedToHit;
        MeleeToHit = meleeToHit;
        Combat = combat;
        Initiative = initiative;
        MaxGrit = maxGrit;
        this.traits = traits;
        this.startingGear = new ArrayList<>(0);
        this.startingClothing = new ArrayList<>(0);
        this.startingMelee = new ArrayList<>(0);
        this.startingRanged = new ArrayList<>(0);
    }

    @NonNull
    public String getClassName() {
        return className;
    }

    public void setClassName(@NonNull String className) {
        this.className = className;
    }

    @NonNull
    public Integer getAgility() {
        return Agility;
    }

    public void setAgility(@NonNull Integer agility) {
        Agility = agility;
    }

    @NonNull
    public Integer getCunning() {
        return Cunning;
    }

    public void setCunning(@NonNull Integer cunning) {
        Cunning = cunning;
    }

    @NonNull
    public Integer getSpirit() {
        return Spirit;
    }

    public void setSpirit(@NonNull Integer spirit) {
        Spirit = spirit;
    }

    @NonNull
    public Integer getStrength() {
        return Strength;
    }

    public void setStrength(@NonNull Integer strength) {
        Strength = strength;
    }

    @NonNull
    public Integer getLore() {
        return Lore;
    }

    public void setLore(@NonNull Integer lore) {
        Lore = lore;
    }

    @NonNull
    public Integer getLuck() {
        return Luck;
    }

    public void setLuck(@NonNull Integer luck) {
        Luck = luck;
    }

    @NonNull
    public Integer getHealth() {
        return Health;
    }

    public void setHealth(@NonNull Integer health) {
        Health = health;
    }

    @NonNull
    public Integer getSanity() {
        return Sanity;
    }

    public void setSanity(@NonNull Integer sanity) {
        Sanity = sanity;
    }

    @NonNull
    public Integer getDefense() {
        return Defense;
    }

    public void setDefense(@NonNull Integer defense) {
        Defense = defense;
    }

    @NonNull
    public Integer getWillpower() {
        return Willpower;
    }

    public void setWillpower(@NonNull Integer willpower) {
        Willpower = willpower;
    }

    @NonNull
    public Integer getRangedToHit() {
        return RangedToHit;
    }

    public void setRangedToHit(@NonNull Integer rangedToHit) {
        RangedToHit = rangedToHit;
    }

    @NonNull
    public Integer getMeleeToHit() {
        return MeleeToHit;
    }

    public void setMeleeToHit(@NonNull Integer meleeToHit) {
        MeleeToHit = meleeToHit;
    }

    @NonNull
    public Integer getCombat() {
        return Combat;
    }

    public void setCombat(@NonNull Integer combat) {
        Combat = combat;
    }

    @NonNull
    public Integer getInitiative() {
        return Initiative;
    }

    public void setInitiative(@NonNull Integer initiative) {
        Initiative = initiative;
    }

    @NonNull
    public Integer getMaxGrit() {
        return MaxGrit;
    }

    public void setMaxGrit(@NonNull Integer maxGrit) {
        MaxGrit = maxGrit;
    }

    public ArrayList<String> getTraits() {
        return traits;
    }

    public void setTraits(ArrayList<String> traits) {
        this.traits = traits;
    }

    public void addTrait(String trait) {
        traits.add(trait);
    }

    public void removeTrait(String trait) {
        traits.remove(trait);
    }

    @NonNull
    public ArrayList<GearBase> getStartingGear() {
        return startingGear;
    }

    public void setStartingGear(@NonNull ArrayList<GearBase> startingGear) {
        this.startingGear = startingGear;
    }

    public void addStartingGear(GearBase gearBase) {
        startingGear.add(gearBase);
    }

    public void removeStartingGear(GearBase gearBase) {
        startingGear.remove(gearBase);
    }

    @NonNull
    public ArrayList<MeleeWeapon> getStartingMelee() {
        return startingMelee;
    }

    public void setStartingMelee(@NonNull ArrayList<MeleeWeapon> startingMelee) {
        this.startingMelee = startingMelee;
    }

    public void addStartingMelee(MeleeWeapon meleeWeapon) {
        startingMelee.add(meleeWeapon);
    }

    public void removeStartingMelee(MeleeWeapon meleeWeapon) {
        startingMelee.remove(meleeWeapon);
    }

    @NonNull
    public ArrayList<RangedWeapon> getStartingRanged() {
        return startingRanged;
    }

    public void setStartingRanged(@NonNull ArrayList<RangedWeapon> startingRanged) {
        this.startingRanged = startingRanged;
    }

    public void addStartingRanged(RangedWeapon rangedWeapon) {
        startingRanged.add(rangedWeapon);
    }

    public void removeStartingRanged(RangedWeapon rangedWeapon) {
        startingRanged.remove(rangedWeapon);
    }

    @NonNull
    public ArrayList<Clothing> getStartingClothing() {
        return startingClothing;
    }

    public void setStartingClothing(@NonNull ArrayList<Clothing> startingClothing) {
        this.startingClothing = startingClothing;
    }

    public void addStartingClothing(Clothing clothing) {
        startingClothing.add(clothing);
    }

    public void removeStartingClothing(Clothing clothing) {
        startingClothing.remove(clothing);
    }
}

