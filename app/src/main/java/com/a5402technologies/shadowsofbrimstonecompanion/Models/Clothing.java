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

@Entity(tableName = "clothing_table")
public class Clothing implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "clothing_name")
    private String name;
    @ColumnInfo(name = "face")
    private Boolean face = FALSE;
    @ColumnInfo(name = "belt")
    private Boolean belt = FALSE;
    @ColumnInfo(name = "hat")
    private Boolean hat = FALSE;
    @ColumnInfo(name = "shoulders")
    private Boolean shoulders = FALSE;
    @ColumnInfo(name = "torso")
    private Boolean torso = FALSE;
    @ColumnInfo(name = "gloves")
    private Boolean gloves = FALSE;
    @ColumnInfo(name = "pants")
    private Boolean pants = FALSE;
    @ColumnInfo(name = "boots")
    private Boolean boots = FALSE;
    @ColumnInfo(name = "coat")
    private Boolean coat = FALSE;
    @NonNull
    @ColumnInfo(name = "equipped")
    private Boolean equipped = FALSE;
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
    private Boolean personal = FALSE;
    @NonNull
    @ColumnInfo(name = "starting_gear")
    private Boolean starting = FALSE;
    @ColumnInfo(name = "penalties")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<String> penalties;
    @NonNull
    @ColumnInfo(name = "upgrade_slots")
    private Integer upgrades = 0;
    @NonNull
    @ColumnInfo(name = "artifact")
    private Boolean artifact = FALSE;
    @ColumnInfo(name = "armor")
    private Integer armor;
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor;
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
    @NonNull
    @ColumnInfo(name = "traits")
    private ArrayList<String> traits;

    public Clothing(@NonNull String name) {
        this.name = name;
        this.modifiers = new ArrayList<>(0);
        this.restrictions = new ArrayList<>(0);
        this.set = "City of the Ancients";
        this.penalties = new ArrayList<>(0);
        attachments = new ArrayList<>(0);
        traits = new ArrayList<>(0);
    }


    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
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

    public Boolean getBelt() {
        return belt;
    }

    public void setBelt(Boolean belt) {
        this.belt = belt;
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
    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(@NonNull Boolean equipped) {
        this.equipped = equipped;
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

    @NonNull
    public ArrayList<String> getTraits() {
        return traits;
    }

    public void setTraits(@NonNull ArrayList<String> traits) {
        this.traits = traits;
    }

    public void addTrait(String trait) {
        this.traits.add(trait);
    }

    public void removeTrait(String trait) {
        this.traits.remove(trait);
    }
}
