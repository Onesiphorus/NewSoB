package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.util.Log;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

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
    @NonNull
    @ColumnInfo(name = "agility_bonus")
    private Integer agilityBonus = 0;
    @NonNull
    @ColumnInfo(name = "cunning_bonus")
    private Integer cunningBonus = 0;
    @NonNull
    @ColumnInfo(name = "spirit_bonus")
    private Integer spiritBonus = 0;
    @NonNull
    @ColumnInfo(name = "strength_bonus")
    private Integer strengthBonus = 0;
    @NonNull
    @ColumnInfo(name = "lore_bonus")
    private Integer loreBonus = 0;
    @NonNull
    @ColumnInfo(name = "luck_bonus")
    private Integer luckBonus = 0;
    @NonNull
    @ColumnInfo(name = "health_bonus")
    private Integer healthBonus = 0;
    @NonNull
    @ColumnInfo(name = "sanity_bonus")
    private Integer sanityBonus = 0;
    @NonNull
    @ColumnInfo(name = "armor")
    private Integer armor = 0;
    @NonNull
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor = 0;
    @NonNull
    @ColumnInfo(name = "ranged_damage_die")
    private Integer rangedDamageDie = 6;
    @NonNull
    @ColumnInfo(name = "ranged_damage_bonus")
    private Integer rangedDamageBonus = 0;
    @NonNull
    @ColumnInfo(name = "melee_damage_bonus")
    private Integer meleeDamageBonus = 0;
    @NonNull
    @ColumnInfo(name = "melee_damage_die")
    private Integer meleeDamageDie = 6;
    @NonNull
    @ColumnInfo(name = "combat_bonus")
    private Integer combatBonus = 0;
    @NonNull
    @ColumnInfo(name = "initiative_bonus")
    private Integer initiativeBonus = 0;
    @NonNull
    @ColumnInfo(name = "max_grit_bonus")
    private Integer maxGritBonus = 0;
    @NonNull
    @ColumnInfo(name = "gold")
    private Integer gold = 0;
    @NonNull
    @ColumnInfo(name = "experience")
    private Integer experience = 0;
    @NonNull
    @ColumnInfo(name = "level")
    private Integer level = 1;
    @NonNull
    @ColumnInfo(name = "face_slot")
    private Boolean face = FALSE;
    @NonNull
    @ColumnInfo(name = "hat_slot")
    private Boolean hat = FALSE;
    @NonNull
    @ColumnInfo(name = "shoulders_slot")
    private Boolean shoulders = FALSE;
    @NonNull
    @ColumnInfo(name = "torso_slot")
    private Boolean torso = FALSE;
    @NonNull
    @ColumnInfo(name = "gloves_slot")
    private Boolean gloves = FALSE;
    @NonNull
    @ColumnInfo(name = "pants_slot")
    private Boolean pants = FALSE;
    @NonNull
    @ColumnInfo(name = "boots_slot")
    private Boolean boots = FALSE;
    @NonNull
    @ColumnInfo(name = "coat_slot")
    private Boolean coat = FALSE;
    @NonNull
    @ColumnInfo(name = "belt")
    private Boolean belt = FALSE;
    @NonNull
    @ColumnInfo(name = "has_prehensile_tail")
    private Boolean hasPTail = FALSE;
    @NonNull
    @ColumnInfo(name = "earned_skills")
    @TypeConverters(GithubTypeConverters.class)
    private ArrayList<Skill> upgrades;
    @NonNull
    @ColumnInfo(name = "move_bonus")
    private Integer moveBonus = 0;
    @ColumnInfo(name = "right_hand")
    @TypeConverters(GithubTypeConverters.class)
    private RangedWeapon rightHand;
    @ColumnInfo(name = "left_hand")
    @TypeConverters(GithubTypeConverters.class)
    private RangedWeapon leftHand;
    @ColumnInfo(name = "right_melee")
    @TypeConverters(GithubTypeConverters.class)
    private MeleeWeapon rightMelee;
    @ColumnInfo(name = "left_melee")
    @TypeConverters(GithubTypeConverters.class)
    private MeleeWeapon leftMelee;
    @ColumnInfo(name = "tail_melee")
    @TypeConverters(GithubTypeConverters.class)
    private MeleeWeapon prehensileMelee;
    @ColumnInfo(name = "prehensile_tail")
    @TypeConverters(GithubTypeConverters.class)
    private RangedWeapon prehensileTail;
    @NonNull
    @ColumnInfo(name = "melee_to_hit_die")
    private Integer meleeToHitDie = 6;
    @NonNull
    @ColumnInfo(name = "melee_crit_chance")
    private Integer meleeCritChance = 6;
    @NonNull
    @ColumnInfo(name = "darkstone_shards")
    private Integer darkStoneShards = 0;


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
        return this.gold;
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

    public void removeClothing(Clothing clothing) {
        this.clothing.remove(clothing);
    }

    public void addUpgrade(Skill skill) {
        this.upgrades.add(skill);
    }

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

    public Boolean getBelt() {
        return belt;
    }

    public void setBelt(Boolean belt) {
        this.belt = belt;
    }

    public void equipClothing(Clothing clothing) {
        if (clothing.getHat().equals(TRUE)) {
            this.setHat(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getBelt().equals(TRUE)) {
            this.setHat(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getBoots().equals(TRUE)) {
            this.setBoots(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getCoat().equals(TRUE)) {
            this.setCoat(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getFace().equals(TRUE)) {
            this.setFace(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getGloves().equals(TRUE)) {
            this.setGloves(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getPants().equals(TRUE)) {
            this.setPants(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getShoulders().equals(TRUE)) {
            this.setShoulders(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
        if (clothing.getTorso().equals(TRUE)) {
            this.setTorso(TRUE);
            findClothingByName(clothing.getName()).setEquipped(TRUE);
        }
    }

    public void unequipClothing(Clothing clothing) {
        if (null != clothing && null != findClothingByName(clothing.getName())) {
            findClothingByName(clothing.getName()).setEquipped(FALSE);
            Log.e("getEquipped: ", clothing.getName() + ": " + findClothingByName(clothing.getName()).getEquipped());
            if (clothing.getEquipped().equals(TRUE)) {
                if (clothing.getTorso().equals(TRUE)) {
                    this.setTorso(FALSE);
                }
                if (clothing.getShoulders().equals(TRUE)) {
                    this.setShoulders(FALSE);
                }
                if (clothing.getBelt().equals(TRUE)) {
                    this.setBelt(FALSE);
                }
                if (clothing.getPants().equals(TRUE)) {
                    this.setPants(FALSE);
                }
                if (clothing.getGloves().equals(TRUE)) {
                    this.setGloves(FALSE);
                }
                if (clothing.getFace().equals(TRUE)) {
                    this.setFace(FALSE);
                }
                if (clothing.getCoat().equals(TRUE)) {
                    this.setCoat(FALSE);
                }
                if (clothing.getBoots().equals(TRUE)) {
                    this.setBoots(FALSE);
                }
                if (clothing.getHat().equals(TRUE)) {
                    this.setHat(FALSE);
                }
            }
        }
    }

    public void equipRightMelee(MeleeWeapon meleeWeapon) {
        if (null != rightMelee) {
            findMeleeWeaponByName(rightMelee.getName()).setEquipped(FALSE);
        }
        findMeleeWeaponByName(meleeWeapon.getName()).setEquipped(TRUE);
        rightMelee = meleeWeapon;
        if (null != leftMelee && (meleeWeapon.getTwoHanded().equals(TRUE)
                || leftMelee.getTwoHanded().equals(TRUE))) {
            findMeleeWeaponByName(leftMelee.getName()).setEquipped(FALSE);
            leftMelee = null;
        }
        if (null != rightHand) {
            findRangedWeaponByName(rightHand.getName()).setEquipped(FALSE);
            rightHand = null;
        }
        if (null != leftHand && (leftHand.getTwoHanded().equals(TRUE) || rightMelee.getTwoHanded().equals(TRUE))) {
            findRangedWeaponByName(leftHand.getName()).setEquipped(FALSE);
            leftHand = null;
        }
    }

    public void equipLeftMelee(MeleeWeapon meleeWeapon) {
        if (null != leftMelee) {
            findMeleeWeaponByName(leftMelee.getName()).setEquipped(FALSE);
        }
        findMeleeWeaponByName(meleeWeapon.getName()).setEquipped(TRUE);
        leftMelee = meleeWeapon;
        if (null != rightMelee && (meleeWeapon.getTwoHanded().equals(TRUE)
                || rightMelee.getTwoHanded().equals(TRUE))) {
            findMeleeWeaponByName(rightMelee.getName()).setEquipped(FALSE);
            rightMelee = null;
        }
        if (null != leftHand) {
            findRangedWeaponByName(leftHand.getName()).setEquipped(FALSE);
            leftHand = null;
        }
        if (null != rightHand && (rightHand.getTwoHanded().equals(TRUE) || leftMelee.getTwoHanded().equals(TRUE))) {
            findRangedWeaponByName(rightHand.getName()).setEquipped(FALSE);
            rightHand = null;
        }
    }

    public void equipRightHand(RangedWeapon rangedWeapon) {
        if (null != rightHand) {
            findRangedWeaponByName(rightHand.getName()).setEquipped(FALSE);
        }
        findRangedWeaponByName(rangedWeapon.getName()).setEquipped(TRUE);
        rightHand = rangedWeapon;
        if (null != leftHand && (rangedWeapon.getTwoHanded().equals(TRUE)
                || leftHand.getTwoHanded().equals(TRUE))) {
            findRangedWeaponByName(leftHand.getName()).setEquipped(FALSE);
            leftHand = null;
        }
        if (null != rightMelee) {
            findMeleeWeaponByName(rightMelee.getName()).setEquipped(FALSE);
            rightMelee = null;
        }
        if (null != leftMelee && (leftMelee.getTwoHanded().equals(TRUE) || rightHand.getTwoHanded().equals(TRUE))) {
            findMeleeWeaponByName(leftMelee.getName()).setEquipped(FALSE);
            leftMelee = null;
        }
    }

    public void equipLeftHand(RangedWeapon rangedWeapon) {
        if (null != leftHand) {
            findRangedWeaponByName(leftHand.getName()).setEquipped(FALSE);
        }
        findRangedWeaponByName(rangedWeapon.getName()).setEquipped(TRUE);
        leftHand = rangedWeapon;
        if (null != rightHand && (rangedWeapon.getTwoHanded().equals(TRUE)
                || rightHand.getTwoHanded().equals(TRUE))) {
            findRangedWeaponByName(rightHand.getName()).setEquipped(FALSE);
            rightHand = null;
        }
        if (null != leftMelee) {
            findMeleeWeaponByName(leftMelee.getName()).setEquipped(FALSE);
            leftMelee = null;
        }
        if (null != rightMelee && (rightMelee.getTwoHanded().equals(TRUE) || leftHand.getTwoHanded().equals(TRUE))) {
            findMeleeWeaponByName(rightMelee.getName()).setEquipped(FALSE);
            rightMelee = null;
        }
    }

    public void setBonuses() {
        resetBonuses();
        for (Clothing clothing : this.getClothing()) {
            if (clothing.getEquipped().equals(TRUE)) {
                for (String string : clothing.getModifiers()) {
                    findBonus(string);
                }
                for (String string : clothing.getPenalties()) {
                    findPenalty(string);
                }
            }
        }
        for (MeleeWeapon meleeWeapon : this.getMeleeWeapons()) {
            if (meleeWeapon.getEquipped().equals(TRUE))
                setCombat(meleeWeapon);
        }
        for (RangedWeapon rangedWeapon : this.getRangedWeapons()) {
            if (rangedWeapon.getEquipped().equals(TRUE))
                setRanged(rangedWeapon);
        }
        for (GearBase gearBase : this.getGear()) {
            for (String string : gearBase.getModifiers()) {
                findBonus(string);
            }
            for (String string : gearBase.getPenalties()) {
                findPenalty(string);
            }
        }
    }

    private void setRanged(RangedWeapon rangedWeapon) {
        for (String string : rangedWeapon.getModifiers()) {
            findBonus(string);
        }
        for (String string : rangedWeapon.getPenalties()) {
            findPenalty(string);
        }
    }

    private void setCombat(MeleeWeapon meleeWeapon) {
        this.combatBonus += meleeWeapon.getCombat();
        if (this.meleeDamageDie < meleeWeapon.getDamageDie()) {
            this.setMeleeDamageDie(meleeWeapon.getDamageDie());
        }
        this.meleeDamageBonus += meleeWeapon.getDamageBonus();
        for (String string : meleeWeapon.getModifiers()) {
            findBonus(string);
        }
        if (this.meleeDamageDie < meleeWeapon.getDamageDie())
            this.meleeDamageDie = meleeWeapon.getDamageDie();
        if (this.meleeToHitDie < meleeWeapon.getMeleeToHitDie())
            this.meleeToHitDie = meleeWeapon.getMeleeToHitDie();
        if (this.meleeCritChance > meleeWeapon.getCritChance())
            this.meleeCritChance = meleeWeapon.getCritChance();
        for (String string : meleeWeapon.getPenalties()) {
            findPenalty(string);
        }
    }

    private void findBonus(String modifier) {
        if (ModifiersEnum.STRENGTH.label().equals(modifier)) {
            setStrengthBonus(getStrengthBonus() + 1);
        } else if (ModifiersEnum.AGILITY.label().equals(modifier)) {
            setAgilityBonus(getAgilityBonus() + 1);
        } else if (ModifiersEnum.CUNNING.label().equals(modifier)) {
            setCunningBonus(getCunningBonus() + 1);
        } else if (ModifiersEnum.SPIRIT.label().equals(modifier)) {
            setSpiritBonus(getSpiritBonus() + 1);
        } else if (ModifiersEnum.LORE.label().equals(modifier)) {
            setLoreBonus(getLoreBonus() + 1);
        } else if (ModifiersEnum.LUCK.label().equals(modifier)) {
            setLuckBonus(getLuckBonus() + 1);
        } else if (ModifiersEnum.MAX_HEALTH.label().equals(modifier)) {
            setHealthBonus(getHealthBonus() + 1);
        } else if (ModifiersEnum.MAX_SANITY.label().equals(modifier)) {
            setSanityBonus(getSanityBonus() + 1);
        } else if (ModifiersEnum.INITIATIVE.label().equals(modifier)) {
            setInitiativeBonus(getInitiativeBonus() + 1);
        } else if (ModifiersEnum.MOVE.label().equals(modifier)) {
            setMoveBonus(getMoveBonus() + 1);
        } else if (ModifiersEnum.MAX_GRIT.label().equals(modifier)) {
            setMaxGritBonus(getMaxGritBonus() + 1);
        } else if (ModifiersEnum.MELEE_DAMAGE.label().equals(modifier)) {
            setMeleeDamageBonus(getMeleeDamageBonus() + 1);
        } else if (ModifiersEnum.COMBAT.label().equals(modifier)) {
            setCombatBonus(getCombatBonus() + 1);
        }
    }

    private void findPenalty(String modifier) {
        if (ModifiersEnum.STRENGTH.label().equals(modifier)) {
            setStrengthBonus(getStrengthBonus() - 1);
        } else if (ModifiersEnum.AGILITY.label().equals(modifier)) {
            setAgilityBonus(getAgilityBonus() - 1);
        } else if (ModifiersEnum.CUNNING.label().equals(modifier)) {
            setCunningBonus(getCunningBonus() - 1);
        } else if (ModifiersEnum.SPIRIT.label().equals(modifier)) {
            setSpiritBonus(getSpiritBonus() - 1);
        } else if (ModifiersEnum.LORE.label().equals(modifier)) {
            setLoreBonus(getLoreBonus() - 1);
        } else if (ModifiersEnum.LUCK.label().equals(modifier)) {
            setLuckBonus(getLuckBonus() - 1);
        } else if (ModifiersEnum.MAX_HEALTH.label().equals(modifier)) {
            setHealthBonus(getHealthBonus() - 1);
        } else if (ModifiersEnum.MAX_SANITY.label().equals(modifier)) {
            setSanityBonus(getSanityBonus() - 1);
        } else if (ModifiersEnum.INITIATIVE.label().equals(modifier)) {
            setInitiativeBonus(getInitiativeBonus() - 1);
        } else if (ModifiersEnum.MOVE.label().equals(modifier)) {
            setMoveBonus(getMoveBonus() - 1);
        } else if (ModifiersEnum.MAX_GRIT.label().equals(modifier)) {
            setMaxGritBonus(getMaxGritBonus() - 1);
        } else if (ModifiersEnum.MELEE_DAMAGE.label().equals(modifier)) {
            setMeleeDamageBonus(getMeleeDamageBonus() - 1);
        } else if (ModifiersEnum.COMBAT.label().equals(modifier)) {
            setCombatBonus(getCombatBonus() - 1);
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

    private RangedWeapon findRangedWeaponByName(String name) {
        for (RangedWeapon rangedWeapon : this.rangedWeapons) {
            if (rangedWeapon.getName().equals(name)) return rangedWeapon;
        }
        return null;
    }

    private MeleeWeapon findMeleeWeaponByName(String name) {
        for (MeleeWeapon meleeWeapon : this.meleeWeapons) {
            if (meleeWeapon.getName().equals(name)) return meleeWeapon;
        }
        return null;
    }

    public MeleeWeapon getRightMelee() {
        return rightMelee;
    }

    public void setRightMelee(MeleeWeapon rightMelee) {
        this.rightMelee = rightMelee;
    }

    public MeleeWeapon getLeftMelee() {
        return leftMelee;
    }

    public void setLeftMelee(MeleeWeapon leftMelee) {
        this.leftMelee = leftMelee;
    }

    public MeleeWeapon getPrehensileMelee() {
        return prehensileMelee;
    }

    public void setPrehensileMelee(MeleeWeapon prehensileMelee) {
        this.prehensileMelee = prehensileMelee;
    }

    public Clothing findClothingByName(String name) {
        for (Clothing clothing : this.getClothing()) {
            if (clothing.getName().equals(name)) return clothing;
        }
        return null;
    }



    @NonNull
    public Integer getDarkStoneShards() {
        return darkStoneShards;
    }

    public void setDarkStoneShards(@NonNull Integer darkStoneShards) {
        this.darkStoneShards = darkStoneShards;
    }

    public void addGold(Integer gold) {
        this.gold += gold;
    }
    public void removeGold(Integer gold) {
        this.gold -= gold;
    }
    public void addExp(Integer exp) {
        this.experience += exp;
    }
    public void removeExp(Integer exp){
        this.experience -= exp;
    }
    public void addDarkstoneShards(Integer darkStone) {
        this.darkStoneShards += darkStone;
    }
    public void removeDarkstoneShards(Integer darkStone) {
        this.darkStoneShards -= darkStone;
    }
}

