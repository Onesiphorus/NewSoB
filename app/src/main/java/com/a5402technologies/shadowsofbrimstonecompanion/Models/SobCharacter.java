package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import java.util.ArrayList;

import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "character_table")
public class SobCharacter implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "character_name")
    private String characterName;
    @ColumnInfo(name = "character_class")
    private CharacterClass characterClass;
    //TODO create table to store lists, link lists with keys
    @ColumnInfo(name = "gear_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<GearBase> gear = new ArrayList<>();
    @ColumnInfo(name = "clothing_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Clothing> clothing = new ArrayList<>();
    @ColumnInfo(name = "melee_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<MeleeWeapon> meleeWeapons = new ArrayList<>();
    @ColumnInfo(name = "ranged_list")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<RangedWeapon> rangedWeapons = new ArrayList<>();
    @ColumnInfo(name = "agility_bonus")
    private Integer agilityBonus = 0;
    @ColumnInfo(name = "cunning_bonus")
    private Integer cunningBonus = 0;
    @ColumnInfo(name = "spirit_bonus")
    private Integer spiritBonus = 0;
    @ColumnInfo(name = "strength_bonus")
    private Integer strengthBonus = 0;
    @ColumnInfo(name = "lore_bonus")
    private Integer loreBonus = 0;
    @ColumnInfo(name = "luck_bonus")
    private Integer luckBonus = 0;
    @ColumnInfo(name = "health_bonus")
    private Integer healthBonus = 0;
    @ColumnInfo(name = "sanity_bonus")
    private Integer sanityBonus = 0;
    @ColumnInfo(name = "armor")
    private Integer armor = 0;
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor = 0;
    @ColumnInfo(name = "ranged_damage_die")
    private Integer rangedDamageDie = 6;
    @ColumnInfo(name = "ranged_damage_bonus")
    private Integer rangedDamageBonus = 0;
    @ColumnInfo(name = "melee_damage_bonus")
    private Integer meleeDamageBonus = 0;
    @ColumnInfo(name = "melee_damage_die")
    private Integer meleeDamageDie = 6;
    @ColumnInfo(name = "combat_bonus")
    private Integer combatBonus = 0;
    @ColumnInfo(name = "initiative_bonus")
    private Integer initiativeBonus = 0;
    @ColumnInfo(name = "max_grit_bonus")
    private Integer maxGritBonus = 0;
    @ColumnInfo(name = "gold")
    private Integer gold = 0;
    @ColumnInfo(name = "experience")
    private Integer experience = 0;
    @ColumnInfo(name = "level")
    private Integer level = 1;
    @ColumnInfo(name = "face_slot")
    private Boolean face = false;
    @ColumnInfo(name = "hat_slot")
    private Boolean hat = false;
    @ColumnInfo(name = "shoulders_slot")
    private Boolean shoulders = false;
    @ColumnInfo(name = "torso_slot")
    private Boolean torso =false;
    @ColumnInfo(name = "gloves_slot")
    private Boolean gloves = false;
    @ColumnInfo(name = "pants_slot")
    private Boolean pants = false;
    @ColumnInfo(name = "boots_slot")
    private Boolean boots = false;
    @ColumnInfo(name = "coat_slot")
    private Boolean coat = false;
    @ColumnInfo(name = "left_hand_slot")
    private Boolean leftHand = false;
    @ColumnInfo(name = "right_hand_slot")
    private Boolean rightHand = false;
    @ColumnInfo(name = "has_prehensile_tail")
    private Boolean hasPTail = false;
    @ColumnInfo(name = "prehensile_tail_slot")
    private Boolean prehensileTail = false;

    public SobCharacter(@NonNull String characterName, CharacterClass characterClass) {
        this.characterName = characterName;
        this.characterClass = characterClass;
    }

    @NonNull
    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(@NonNull String characterName) {
        this.characterName = characterName;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public ArrayList<GearBase> getGear() {
        return gear;
    }

    public void setGear(ArrayList<GearBase> gear) {
        this.gear = gear;
    }

    public ArrayList<Clothing> getClothing() {
        return clothing;
    }

    public void setClothing(ArrayList<Clothing> clothing) {
        this.clothing = clothing;
    }

    public ArrayList<MeleeWeapon> getMeleeWeapons() {
        return meleeWeapons;
    }

    public void setMeleeWeapons(ArrayList<MeleeWeapon> meleeWeapons) {
        this.meleeWeapons = meleeWeapons;
    }

    public ArrayList<RangedWeapon> getRangedWeapons() {
        return rangedWeapons;
    }

    public void setRangedWeapons(ArrayList<RangedWeapon> rangedWeapons) {
        this.rangedWeapons = rangedWeapons;
    }

    public Integer getAgilityBonus() {
        return agilityBonus;
    }

    public void setAgilityBonus(Integer agilityBonus) {
        this.agilityBonus = agilityBonus;
    }

    public Integer getCunningBonus() {
        return cunningBonus;
    }

    public void setCunningBonus(Integer cunningBonus) {
        this.cunningBonus = cunningBonus;
    }

    public Integer getSpiritBonus() {
        return spiritBonus;
    }

    public void setSpiritBonus(Integer spiritBonus) {
        this.spiritBonus = spiritBonus;
    }

    public Integer getStrengthBonus() {
        return strengthBonus;
    }

    public void setStrengthBonus(Integer strengthBonus) {
        this.strengthBonus = strengthBonus;
    }

    public Integer getLoreBonus() {
        return loreBonus;
    }

    public void setLoreBonus(Integer loreBonus) {
        this.loreBonus = loreBonus;
    }

    public Integer getLuckBonus() {
        return luckBonus;
    }

    public void setLuckBonus(Integer luckBonus) {
        this.luckBonus = luckBonus;
    }

    public Integer getHealthBonus() {
        return healthBonus;
    }

    public void setHealthBonus(Integer healthBonus) {
        this.healthBonus = healthBonus;
    }

    public Integer getSanityBonus() {
        return sanityBonus;
    }

    public void setSanityBonus(Integer sanityBonus) {
        this.sanityBonus = sanityBonus;
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

    public Integer getRangedDamageDie() {
        return rangedDamageDie;
    }

    public void setRangedDamageDie(Integer rangedDamageDie) {
        this.rangedDamageDie = rangedDamageDie;
    }

    public Integer getRangedDamageBonus() {
        return rangedDamageBonus;
    }

    public void setRangedDamageBonus(Integer rangedDamageBonus) {
        this.rangedDamageBonus = rangedDamageBonus;
    }

    public Integer getMeleeDamageBonus() {
        return meleeDamageBonus;
    }

    public void setMeleeDamageBonus(Integer meleeDamageBonus) {
        this.meleeDamageBonus = meleeDamageBonus;
    }

    public Integer getMeleeDamageDie() {
        return meleeDamageDie;
    }

    public void setMeleeDamageDie(Integer meleeDamageDie) {
        this.meleeDamageDie = meleeDamageDie;
    }

    public Integer getCombatBonus() {
        return combatBonus;
    }

    public void setCombatBonus(Integer combatBonus) {
        this.combatBonus = combatBonus;
    }

    public Integer getInitiativeBonus() {
        return initiativeBonus;
    }

    public void setInitiativeBonus(Integer initiativeBonus) {
        this.initiativeBonus = initiativeBonus;
    }

    public Integer getMaxGritBonus() {
        return maxGritBonus;
    }

    public void setMaxGritBonus(Integer maxGritBonus) {
        this.maxGritBonus = maxGritBonus;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getFace() {
        return face;
    }

    public void setFace(Boolean face) {
        this.face = face;
    }

    public Boolean getHat() {
        return hat;
    }

    public void setHat(Boolean hat) {
        this.hat = hat;
    }

    public Boolean getShoulders() {
        return shoulders;
    }

    public void setShoulders(Boolean shoulders) {
        this.shoulders = shoulders;
    }

    public Boolean getTorso() {
        return torso;
    }

    public void setTorso(Boolean torso) {
        this.torso = torso;
    }

    public Boolean getGloves() {
        return gloves;
    }

    public void setGloves(Boolean gloves) {
        this.gloves = gloves;
    }

    public Boolean getPants() {
        return pants;
    }

    public void setPants(Boolean pants) {
        this.pants = pants;
    }

    public Boolean getBoots() {
        return boots;
    }

    public void setBoots(Boolean boots) {
        this.boots = boots;
    }

    public Boolean getCoat() {
        return coat;
    }

    public void setCoat(Boolean coat) {
        this.coat = coat;
    }

    public Boolean getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Boolean leftHand) {
        this.leftHand = leftHand;
    }

    public Boolean getRightHand() {
        return rightHand;
    }

    public void setRightHand(Boolean rightHand) {
        this.rightHand = rightHand;
    }

    public Boolean getHasPTail() {
        return hasPTail;
    }

    public void setHasPTail(Boolean hasPTail) {
        this.hasPTail = hasPTail;
    }

    public Boolean getPrehensileTail() {
        return prehensileTail;
    }

    public void setPrehensileTail(Boolean prehensileTail) {
        this.prehensileTail = prehensileTail;
    }

    public void addMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapons.add(meleeWeapon);
    }

    public void removeMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapons.remove(meleeWeapon);
    }

    public void addRangedWeapon(RangedWeapon rangedWeapon) {
        this.rangedWeapons.add(rangedWeapon);
    }

    public void removeRangedWeapon(RangedWeapon rangedWeapon) {
        this.rangedWeapons.remove(rangedWeapon);
    }

    public void addGear(GearBase gearBase) {
        this.gear.add(gearBase);
    }

    public void removeGear(GearBase gearBase) {
        this.gear.remove(gearBase);
    }

    public void addClothing(Clothing clothing) {
        this.clothing.add(clothing);
    }

    public void removeCothing(Clothing clothing) {
        this.clothing.remove(clothing);
    }
}

