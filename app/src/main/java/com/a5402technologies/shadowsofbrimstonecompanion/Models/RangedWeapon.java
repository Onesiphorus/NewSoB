package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ShopEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

@Entity(tableName = "ranged_weapon_table")
public class RangedWeapon implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ranged_name")
    private String name;
    @NonNull
    @ColumnInfo(name = "ranged_die")
    private Integer damageDie = 6;
    @ColumnInfo(name = "ranged_bonus")
    private Integer damageBonus = 0;
    @NonNull
    @ColumnInfo(name = "num_shots")
    private Integer shots = 0;
    @NonNull
    @ColumnInfo(name = "weapon_range")
    private Integer range = 0;
    @NonNull
    @ColumnInfo(name = "is_two_handed")
    private Boolean twoHanded = Boolean.FALSE;
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
    private String set;
    @NonNull
    @ColumnInfo(name = "personal_item")
    private Boolean personal;
    @NonNull
    @ColumnInfo(name = "starting_gear")
    private Boolean starting;
    @ColumnInfo(name = "penalties")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "equipped")
    private Boolean equipped;
    @NonNull
    @ColumnInfo(name = "upgrade_slots")
    private Integer upgrades = 0;
    @NonNull
    @ColumnInfo(name = "artifact")
    private Boolean artifact = Boolean.FALSE;
    @NonNull
    @ColumnInfo(name = "to_hit_die")
    private Integer toHitDie = 6;
    @NonNull
    @ColumnInfo(name = "crit_chance")
    private Integer critChance = 6;
    @NonNull
    @ColumnInfo(name = "free_attack")
    private Boolean free = Boolean.FALSE;
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
    @NonNull
    @ColumnInfo(name = "shop")
    private String shop = ShopEnum.NONE.label();
    @NonNull
    @ColumnInfo(name = "darkstone_cost")
    private Integer darkstoneCost = 0;

    public RangedWeapon(@NonNull String name, @NonNull Integer range, @NonNull Integer shots) {
        this.name = name;
        this.damageDie = 6;
        this.damageBonus = 0;
        this.shots = shots;
        this.range = range;
        this.twoHanded = Boolean.FALSE;
        this.cost = 0;
        this.sell = 0;
        this.weight = 0;
        this.darkStone = 0;
        this.modifiers = new ArrayList<>(0);
        this.restrictions = new ArrayList<>(0);
        this.set = "City of the Ancients";
        this.personal = Boolean.FALSE;
        this.starting = Boolean.FALSE;
        this.penalties = new ArrayList<>(0);
        this.equipped = Boolean.FALSE;
        attachments = new ArrayList<>(0);
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Integer getDamageDie() {
        return damageDie;
    }

    public void setDamageDie(@NonNull Integer damageDie) {
        this.damageDie = damageDie;
    }

    public Integer getDamageBonus() {
        return damageBonus;
    }

    public void setDamageBonus(Integer damageBonus) {
        this.damageBonus = damageBonus;
    }

    @NonNull
    public Integer getShots() {
        return shots;
    }

    public void setShots(@NonNull Integer shots) {
        this.shots = shots;
    }

    @NonNull
    public Integer getRange() {
        return range;
    }

    public void setRange(@NonNull Integer range) {
        this.range = range;
    }

    @NonNull
    public Boolean getTwoHanded() {
        return twoHanded;
    }

    public void setTwoHanded(@NonNull Boolean twoHanded) {
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

    @NonNull
    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(@NonNull Boolean equipped) {
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
    public Integer getToHitDie() {
        return toHitDie;
    }

    public void setToHitDie(@NonNull Integer toHitDie) {
        this.toHitDie = toHitDie;
    }

    @NonNull
    public Integer getCritChance() {
        return critChance;
    }

    public void setCritChance(@NonNull Integer critChance) {
        this.critChance = critChance;
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

    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }

    @NonNull
    public String getShop() {
        return shop;
    }

    public void setShop(@NonNull String shop) {
        this.shop = shop;
    }

    @NonNull
    public Integer getDarkstoneCost() {
        return darkstoneCost;
    }

    public void setDarkstoneCost(@NonNull Integer darkstoneCost) {
        this.darkstoneCost = darkstoneCost;
    }
}
