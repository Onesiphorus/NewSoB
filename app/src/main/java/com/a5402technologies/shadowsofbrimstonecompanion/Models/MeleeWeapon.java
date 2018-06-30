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

@Entity(tableName = "melee_weapon_table")
public class MeleeWeapon implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "melee_name")
    private String name;
    @ColumnInfo(name = "combat_bonus")
    private Integer combat = 0;
    @ColumnInfo(name = "melee_bonus")
    private Integer damageBonus = 0;
    @ColumnInfo(name = "melee_die")
    private Integer damageDie = 6;
    @ColumnInfo(name = "is_two_handed")
    private Boolean twoHanded = FALSE;
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
    private String set = "City of the Ancients";
    @NonNull
    @ColumnInfo(name = "personal_item")
    private Boolean personal = FALSE;
    @NonNull
    @ColumnInfo(name = "starting_gear")
    private Boolean starting = FALSE;
    @ColumnInfo(name = "penalties")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> penalties;
    @ColumnInfo(name = "equipped")
    private Boolean equipped = FALSE;
    @NonNull
    @ColumnInfo(name = "upgrade_slots")
    private Integer upgrades = 0;
    @NonNull
    @ColumnInfo(name = "artifact")
    private Boolean artifact = FALSE;
    @NonNull
    @ColumnInfo(name = "crit_chance")
    private Integer critChance = 6;
    @ColumnInfo(name = "armor")
    private Integer armor;
    @NonNull
    @ColumnInfo(name = "melee_to_hit_die")
    private Integer meleeToHitDie = 6;
    @NonNull
    @ColumnInfo(name = "free_attack")
    private Boolean free = FALSE;
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
    @ColumnInfo(name = "attachments")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Attachment> attachments;


    public MeleeWeapon(@NonNull String name) {
        this.name = name;
        this.restrictions = new ArrayList<>(0);
        this.modifiers = new ArrayList<>(0);
        this.penalties = new ArrayList<>(0);
        attachments = new ArrayList<>(0);
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Integer getCombat() {
        return combat;
    }

    public void setCombat(Integer combat) {
        this.combat = combat;
    }

    public Integer getDamageBonus() {
        return damageBonus;
    }

    public void setDamageBonus(Integer damageBonus) {
        this.damageBonus = damageBonus;
    }

    public Integer getDamageDie() {
        return damageDie;
    }

    public void setDamageDie(Integer damageDie) {
        this.damageDie = damageDie;
    }

    public Boolean getTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(Boolean twoHanded) {
        this.twoHanded = twoHanded;
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
    public Boolean getPersonal() {
        return personal;
    }

    public void setPersonal(@NonNull Boolean personal) {
        this.personal = personal;
    }

    @NonNull
    public Boolean getStarting() {
        return starting;
    }

    public void setStarting(@NonNull Boolean starting) {
        this.starting = starting;
    }

    public ArrayList<String> getPenalties() {
        return penalties;
    }

    public void setPenalties(ArrayList<String> penalties) {
        this.penalties = penalties;
    }


    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(Boolean equipped) {
        this.equipped = equipped;
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

    @NonNull
    public Integer getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(@NonNull Integer upgrades) {
        this.upgrades = upgrades;
    }

    @NonNull
    public Boolean getArtifact() {
        return artifact;
    }

    public void setArtifact(@NonNull Boolean artifact) {
        this.artifact = artifact;
    }

    @NonNull
    public Integer getCritChance() {
        return critChance;
    }

    public void setCritChance(@NonNull Integer critChance) {
        this.critChance = critChance;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    @NonNull
    public Integer getMeleeToHitDie() {
        return meleeToHitDie;
    }

    public void setMeleeToHitDie(@NonNull Integer meleeToHitDie) {
        this.meleeToHitDie = meleeToHitDie;
    }

    @NonNull
    public Boolean getFree() {
        return free;
    }

    public void setFree(@NonNull Boolean free) {
        this.free = free;
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

    private ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    private void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }
}
