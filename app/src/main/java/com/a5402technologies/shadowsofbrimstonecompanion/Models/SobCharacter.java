package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.app.job.JobInfo;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Range;
import android.widget.Toast;

import java.util.ArrayList;

import com.a5402technologies.shadowsofbrimstonecompanion.Adapters.StringListAdapter;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

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
    private Boolean face = Boolean.FALSE;
    @ColumnInfo(name = "hat_slot")
    private Boolean hat = Boolean.FALSE;
    @ColumnInfo(name = "shoulders_slot")
    private Boolean shoulders = Boolean.FALSE;
    @ColumnInfo(name = "torso_slot")
    private Boolean torso = Boolean.FALSE;
    @ColumnInfo(name = "gloves_slot")
    private Boolean gloves = Boolean.FALSE;
    @ColumnInfo(name = "pants_slot")
    private Boolean pants = Boolean.FALSE;
    @ColumnInfo(name = "boots_slot")
    private Boolean boots = Boolean.FALSE;
    @ColumnInfo(name = "coat_slot")
    private Boolean coat = Boolean.FALSE;
    @ColumnInfo(name = "has_prehensile_tail")
    private Boolean hasPTail = Boolean.FALSE;
    @ColumnInfo(name = "earned_skills")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Skill> upgrades;
    @NonNull
    @ColumnInfo(name = "hands_used")
    private Integer handsUsed = 0;
    @NonNull
    @ColumnInfo(name = "move_bonus")
    private Integer moveBonus = 0;
    @ColumnInfo(name = "right_hand")
    @TypeConverters(GithubTypeConverters.class)
    private RangedWeapon rightHand;
    @ColumnInfo(name = "left_hand")
    @TypeConverters(GithubTypeConverters.class)
    private RangedWeapon leftHand;
    @ColumnInfo(name = "prehensile_tail")
    @TypeConverters(GithubTypeConverters.class)
    private RangedWeapon prehensileTail;
    @NonNull
    @ColumnInfo(name = "melee_to_hit_die")
    private Integer meleeToHitDie = 6;
    @NonNull
    @ColumnInfo(name = "melee_crit_chance")
    private Integer meleeCritChance = 6;



    public SobCharacter(@NonNull String characterName, CharacterClass characterClass) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        clothing = new ArrayList<>(0);
        gear = new ArrayList<>(0);
        meleeWeapons = new ArrayList<>(0);
        rangedWeapons = new ArrayList<>(0);
        upgrades = new ArrayList<>(0);
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

    public Boolean getHasPTail() {
        return hasPTail;
    }

    public void setHasPTail(Boolean hasPTail) {
        this.hasPTail = hasPTail;
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

    public void addUpgrade(Skill skill) {this.upgrades.add(skill);}

    public void removeUpgrade(Skill skill) {
        this.upgrades.remove(skill);
    }

    public ArrayList<Skill> getUpgrades() {
        return upgrades;
    }

    public void setUpgrades(ArrayList<Skill> upgrades) {
        this.upgrades = upgrades;
    }

    @NonNull
    public Integer getMoveBonus() {
        return moveBonus;
    }

    public void setMoveBonus(@NonNull Integer moveBonus) {
        this.moveBonus = moveBonus;
    }

    @NonNull
    public Integer getHandsUsed() {
        return handsUsed;
    }

    public void setHandsUsed(@NonNull Integer handsUsed) {
        this.handsUsed = handsUsed;
    }

    public Boolean equipClothing(Clothing clothing) {
        if(clothing.getHat().equals(Boolean.TRUE)) {
            if(this.getHat().equals(Boolean.FALSE)) {
                this.setHat(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
        if(clothing.getBoots().equals(Boolean.TRUE)) {
            if(this.getBoots().equals(Boolean.FALSE)) {
                this.setBoots(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
        if(clothing.getCoat().equals(Boolean.TRUE)) {
            if(this.getCoat().equals(Boolean.FALSE)) {
                this.setCoat(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
        if(clothing.getFace().equals(Boolean.TRUE)) {
            if (this.getFace().equals(Boolean.FALSE)) {
                this.setFace(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            } else return Boolean.FALSE;
        }
        if(clothing.getGloves().equals(Boolean.TRUE)) {
            if(this.getGloves().equals(Boolean.FALSE)) {
                this.setGloves(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
        if(clothing.getPants().equals(Boolean.TRUE)) {
            if(this.getPants().equals(Boolean.FALSE)) {
                this.setPants(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
        if(clothing.getShoulders().equals(Boolean.TRUE)) {
            if(this.getShoulders().equals(Boolean.FALSE)) {
                this.setShoulders(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
        if(clothing.getTorso().equals(Boolean.TRUE)) {
            if(this.getTorso().equals(Boolean.FALSE)) {
                this.setTorso(Boolean.TRUE);
                clothing.setEquipped(Boolean.TRUE);
            }
            else return Boolean.FALSE;
        }
            return Boolean.TRUE;
    }

    public Boolean unequipClothing(Clothing clothing) {
        if(clothing.getEquipped().equals(Boolean.TRUE)) {
            if(clothing.getTorso().equals(Boolean.TRUE)) {
                this.setTorso(Boolean.FALSE);
            }
            if(clothing.getShoulders().equals(Boolean.TRUE)) {
                this.setShoulders(Boolean.FALSE);
            }
            if(clothing.getPants().equals(Boolean.TRUE)) {
                this.setPants(Boolean.FALSE);
            }
            if(clothing.getGloves().equals(Boolean.TRUE)) {
                this.setGloves(Boolean.FALSE);
            }
            if(clothing.getFace().equals(Boolean.TRUE)) {
                this.setFace(Boolean.FALSE);
            }
            if(clothing.getCoat().equals(Boolean.TRUE)) {
                this.setCoat(Boolean.FALSE);
            }
            if(clothing.getBoots().equals(Boolean.TRUE)) {
                this.setBoots(Boolean.FALSE);
            }
            if(clothing.getHat().equals(Boolean.TRUE)) {
                this.setHat(Boolean.FALSE);
            }
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Integer handsFree() {
        return hasPTail ? (3 - getHandsUsed()) : (2-getHandsUsed());
    }

    public String equipRanged(RangedWeapon rangedWeapon) {
        if (rangedWeapon.getTwoHanded().equals(Boolean.TRUE) && this.handsFree() > 1) {
            setHandsUsed(getHandsUsed() + 2);
            if(null == this.rightHand || this.rightHand.getName().isEmpty()) {
                this.rightHand = rangedWeapon;
                Log.e(TAG, "equipRanged: " + rangedWeapon.getName() + " equipped to both hands.");
            } else {
                this.leftHand = rangedWeapon;
            }
            return "equipRanged: " + rangedWeapon.getName() + " equipped to both hands.";
        } else if (this.handsFree() > 0){
            setHandsUsed(getHandsUsed() + 1);
            if(null == this.rightHand || this.rightHand.getName().isEmpty()) {
                this.rightHand = rangedWeapon;
                Log.e(TAG, "equipRanged: " + rangedWeapon.getName() + " equipped to right hand.");
                return "equipRanged: " + rangedWeapon.getName() + " equipped to right hand.";
            } else if (null == this.leftHand || this.leftHand.getName().isEmpty()) {
                this.leftHand = rangedWeapon;
                Log.e(TAG, "equipRanged: " + rangedWeapon.getName() + " equipped to left hand.");
                return "equipRanged: " + rangedWeapon.getName() + " equipped to left hand.";
            } else {
                this.prehensileTail = rangedWeapon;
                return "equipRanged: " + rangedWeapon.getName() + " equipped to tail.";
            }
        }
        return "No free hands!";
    }

    public Boolean unequipRanged(RangedWeapon rangedWeapon) {
        if(rangedWeapon.getEquipped().equals(Boolean.TRUE)) {
            if(rangedWeapon.getTwoHanded().equals(Boolean.TRUE)) {
                setHandsUsed(getHandsUsed() - 2);
            } else setHandsUsed(getHandsUsed() -1);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public Boolean equipMelee(MeleeWeapon meleeWeapon) {
        if (meleeWeapon.getTwoHanded().equals(Boolean.TRUE) && this.handsFree() > 1) {
            setHandsUsed(getHandsUsed() + 2);
            return Boolean.TRUE;
        } else if (this.handsFree() > 0){
            setHandsUsed(getHandsUsed() + 1);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean unequipMelee(MeleeWeapon meleeWeapon) {
        if(meleeWeapon.getEquipped().equals(Boolean.TRUE)) {
            if(meleeWeapon.getTwoHanded().equals(Boolean.TRUE)) {
                setHandsUsed(getHandsUsed() - 2);
            } else setHandsUsed(getHandsUsed() -1);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public void setBonuses() {
        resetBonuses();
        for(Clothing clothing : this.getClothing()) {
            if(clothing.getEquipped().equals(Boolean.TRUE)) {
                for (String string : clothing.getModifiers()) {
                    findBonus(string);
                }
                for (String string : clothing.getPenalties()) {
                    findPenalty(string);
                }
            }
        }
        for(MeleeWeapon meleeWeapon : this.getMeleeWeapons()) {
            if(meleeWeapon.getEquipped().equals(Boolean.TRUE))
            setCombat(meleeWeapon);
        }
        for(RangedWeapon rangedWeapon : this.getRangedWeapons()) {
            if(rangedWeapon.getEquipped().equals(Boolean.TRUE))
            setRanged(rangedWeapon);
        }
        for(GearBase gearBase : this.getGear()) {
            for(String string : gearBase.getModifiers()) {
                findBonus(string);
            }
            for(String string : gearBase.getPenalties()) {
                findPenalty(string);
            }
        }
    }

    private void setRanged(RangedWeapon rangedWeapon) {
        for(String string : rangedWeapon.getModifiers()) {
            findBonus(string);
        }
        for(String string : rangedWeapon.getPenalties()) {
            findPenalty(string);
        }
    }

    private void setCombat(MeleeWeapon meleeWeapon) {
        this.combatBonus += meleeWeapon.getCombat();
        if(this.meleeDamageDie < meleeWeapon.getDamageDie()) {
            this.setMeleeDamageDie(meleeWeapon.getDamageDie());
        }
        this.meleeDamageBonus += meleeWeapon.getDamageBonus();
        for(String string : meleeWeapon.getModifiers()) {
            findBonus(string);
        }
        if(this.meleeDamageDie < meleeWeapon.getDamageDie()) this.meleeDamageDie = meleeWeapon.getDamageDie();
        if(this.meleeToHitDie < meleeWeapon.getMeleeToHitDie()) this.meleeToHitDie = meleeWeapon.getMeleeToHitDie();
        if(this.meleeCritChance > meleeWeapon.getCritChance()) this.meleeCritChance = meleeWeapon.getCritChance();
        for(String string : meleeWeapon.getPenalties()) {
            findPenalty(string);
        }
    }

    private void findBonus(String modifier) {
        if(ModifiersEnum.STRENGTH.label().equals(modifier)) {
            setStrengthBonus(getStrengthBonus() + 1);
        }
        else if(ModifiersEnum.AGILITY.label().equals(modifier)) {
            setAgilityBonus(getAgilityBonus() + 1);
        }
        else if(ModifiersEnum.CUNNING.label().equals(modifier)) {
            setCunningBonus(getCunningBonus() + 1);
        }
        else if(ModifiersEnum.SPIRIT.label().equals(modifier)) {
            setSpiritBonus(getSpiritBonus() + 1);
        }
        else if(ModifiersEnum.LORE.label().equals(modifier)) {
            setLoreBonus(getLoreBonus() + 1);
        }
        else if(ModifiersEnum.LUCK.label().equals(modifier)) {
            setLuckBonus(getLuckBonus() + 1);
        }
        else if(ModifiersEnum.MAX_HEALTH.label().equals(modifier)) {
            setHealthBonus(getHealthBonus() + 1);
        }
        else if(ModifiersEnum.MAX_SANITY.label().equals(modifier)) {
            setSanityBonus(getSanityBonus() + 1);
        }
        else if(ModifiersEnum.INITIATIVE.label().equals(modifier)) {
            setInitiativeBonus(getInitiativeBonus() + 1);
        }
        else if(ModifiersEnum.MOVE.label().equals(modifier)) {
            setMoveBonus(getMoveBonus() + 1);
        }
        else if(ModifiersEnum.MAX_GRIT.label().equals(modifier)) {
            setMaxGritBonus(getMaxGritBonus() + 1);
        }
    }

    private void findPenalty(String modifier) {
        if(ModifiersEnum.STRENGTH.label().equals(modifier)) {
            setStrengthBonus(getStrengthBonus() - 1);
        }
        else if(ModifiersEnum.AGILITY.label().equals(modifier)) {
            setAgilityBonus(getAgilityBonus() - 1);
        }
        else if(ModifiersEnum.CUNNING.label().equals(modifier)) {
            setCunningBonus(getCunningBonus() - 1);
        }
        else if(ModifiersEnum.SPIRIT.label().equals(modifier)) {
            setSpiritBonus(getSpiritBonus() - 1);
        }
        else if(ModifiersEnum.LORE.label().equals(modifier)) {
            setLoreBonus(getLoreBonus() - 1);
        }
        else if(ModifiersEnum.LUCK.label().equals(modifier)) {
            setLuckBonus(getLuckBonus() - 1);
        }
        else if(ModifiersEnum.MAX_HEALTH.label().equals(modifier)) {
            setHealthBonus(getHealthBonus() - 1);
        }
        else if(ModifiersEnum.MAX_SANITY.label().equals(modifier)) {
            setSanityBonus(getSanityBonus() - 1);
        }
        else if(ModifiersEnum.INITIATIVE.label().equals(modifier)) {
            setInitiativeBonus(getInitiativeBonus() - 1);
        }
        else if(ModifiersEnum.MOVE.label().equals(modifier)) {
            setMoveBonus(getMoveBonus() - 1);
        }
        else if(ModifiersEnum.MAX_GRIT.label().equals(modifier)) {
            setMaxGritBonus(getMaxGritBonus() - 1);
        }
    }

    private void resetBonuses() {
        this.setAgilityBonus(0);
        this.setCunningBonus(0);
        this.setSpiritBonus(0);
        this.setStrengthBonus(0);
        this.setLoreBonus(0);
        this.setLuckBonus(0);
        this.setInitiativeBonus(0);
        this.setSanityBonus(0);
        this.setHealthBonus(0);
        this.setMoveBonus(0);
        this.setCombatBonus(0);
        this.setMaxGritBonus(0);
        this.setMeleeDamageBonus(0);
        this.setRangedDamageBonus(0);
        this.setMeleeDamageDie(6);
        this.setArmor(0);
        this.setSpiritArmor(0);
        this.setMeleeToHitDie(6);
        this.setMeleeDamageDie(6);
    }

    public RangedWeapon getRightHand() {
        return rightHand;
    }

    public void setRightHand(RangedWeapon rightHand) {
        this.rightHand = rightHand;
    }

    public RangedWeapon getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(RangedWeapon leftHand) {
        this.leftHand = leftHand;
    }

    public RangedWeapon getPrehensileTail() {
        return prehensileTail;
    }

    public void setPrehensileTail(RangedWeapon prehensileTail) {
        this.prehensileTail = prehensileTail;
    }

    @NonNull
    public Integer getMeleeToHitDie() {
        return meleeToHitDie;
    }

    public void setMeleeToHitDie(@NonNull Integer meleeToHitDie) {
        this.meleeToHitDie = meleeToHitDie;
    }

    @NonNull
    public Integer getMeleeCritChance() {
        return meleeCritChance;
    }

    public void setMeleeCritChance(@NonNull Integer meleeCritChance) {
        this.meleeCritChance = meleeCritChance;
    }

    public RangedWeapon findRangedWeaponByName(String name) {
        for (RangedWeapon rangedWeapon : this.rangedWeapons) {
            if(rangedWeapon.getName().equals(name)) return rangedWeapon;
        }
        return null;
    }
}

