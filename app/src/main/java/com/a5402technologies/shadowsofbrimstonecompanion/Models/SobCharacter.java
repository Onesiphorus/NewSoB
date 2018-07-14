package com.a5402technologies.shadowsofbrimstonecompanion.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;
import android.util.Log;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.CharacterClassEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.RuleExceptionEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.TraitsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Entity(tableName = "character_table")
public class SobCharacter implements Serializable {
    @TypeConverters(GithubTypeConverters.class)

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "character_name")
    private String characterName;
    @NonNull
    @ColumnInfo(name = "character_class")
    private CharacterClass characterClass;
    @NonNull
    @ColumnInfo(name = "gear_list")
    private ArrayList<GearBase> gear;
    @NonNull
    @ColumnInfo(name = "clothing_list")
    private ArrayList<Clothing> clothing;
    @NonNull
    @ColumnInfo(name = "melee_list")
    private ArrayList<MeleeWeapon> meleeWeapons;
    @NonNull
    @ColumnInfo(name = "ranged_list")
    private ArrayList<RangedWeapon> rangedWeapons;
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
    private Integer armor = 7;
    @NonNull
    @ColumnInfo(name = "spirit_armor")
    private Integer spiritArmor = 7;
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
    private ArrayList<Skill> upgrades;
    @NonNull
    @ColumnInfo(name = "move_bonus")
    private Integer moveBonus = 0;
    @ColumnInfo(name = "right_hand")
    private RangedWeapon rightHand;
    @ColumnInfo(name = "left_hand")
    private RangedWeapon leftHand;
    @ColumnInfo(name = "right_melee")
    private MeleeWeapon rightMelee;
    @ColumnInfo(name = "left_melee")
    private MeleeWeapon leftMelee;
    @ColumnInfo(name = "tail_melee")
    private MeleeWeapon prehensileMelee;
    @ColumnInfo(name = "prehensile_tail")
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
    @NonNull
    @ColumnInfo(name = "traits")
    private ArrayList<String> traits;
    @NonNull
    @ColumnInfo(name = "attachments")
    private ArrayList<Attachment> attachments;
    @NonNull
    @ColumnInfo(name = "current_health")
    private Integer currentHealth;
    @NonNull
    @ColumnInfo(name = "current_sanity")
    private Integer currentSanity;
    @NonNull
    @ColumnInfo(name = "modifiers")
    private ArrayList<String> modifiers;
    @NonNull
    @ColumnInfo(name = "weight")
    private Integer weight = 0;
    @NonNull
    @ColumnInfo(name = "max_weight")
    private Integer maxWeight = 0;
    @NonNull
    @ColumnInfo(name = "side_bag")
    private ArrayList<String> sideBag;
    @NonNull
    @ColumnInfo(name = "side_bag_size")
    private Integer sideBagSize = 5;
    @NonNull
    @ColumnInfo(name = "max_corruption")
    private Integer maxCorruption = 5;
    @NonNull
    @ColumnInfo(name = "current_corruption")
    private Integer currentCorruption = 0;
    @NonNull
    @ColumnInfo(name = "dark_stone_count")
    private Integer darkStoneCount = 0;
    @NonNull
    @ColumnInfo(name = "injuries")
    private ArrayList<PermanentCondition> injuries;
    @NonNull
    @ColumnInfo(name = "madness")
    private ArrayList<PermanentCondition> madness;
    @NonNull
    @ColumnInfo(name = "mutations")
    private ArrayList<PermanentCondition> mutations;
    @NonNull
    @ColumnInfo(name = "transport")
    private Transport transport;

    @NonNull
    @ColumnInfo(name = "defense")
    private Integer defense;
    @NonNull
    @ColumnInfo(name = "willpwower")
    private Integer willpower;
    @NonNull
    @ColumnInfo(name = "melee_to_hit")
    private Integer meleeToHit;
    @NonNull
    @ColumnInfo(name = "ranged_to_hit")
    private Integer rangedToHit;


    public SobCharacter(@NonNull String characterName, CharacterClass characterClass) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.defense = characterClass.getDefense();
        this.willpower = characterClass.getWillpower();
        this.meleeToHit = characterClass.getMeleeToHit();
        this.rangedToHit = characterClass.getRangedToHit();
        clothing = new ArrayList<>(0);
        gear = new ArrayList<>(0);
        meleeWeapons = new ArrayList<>(0);
        rangedWeapons = new ArrayList<>(0);
        upgrades = new ArrayList<>(0);
        attachments = new ArrayList<>(0);
        currentHealth = characterClass.getHealth();
        currentSanity = characterClass.getSanity();
        modifiers = new ArrayList<>(0);
        traits = characterClass.getTraits();
        sideBag = new ArrayList<>(0);
        injuries = new ArrayList<>(0);
        madness = new ArrayList<>(0);
        mutations = new ArrayList<>(0);
        transport = new Transport();
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
            this.setBelt(TRUE);
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
            Log.e("getEquipped: ",
                    clothing.getName()
                            + ": "
                            + findClothingByName(clothing.getName()).getEquipped());
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
            findClothingByName(clothing.getName()).setEquipped(FALSE);
        }
    }

    public void equipRightMelee(MeleeWeapon meleeWeapon) {
        if (null != rightMelee) {
            unequipRightMelee();
        }
        findMeleeWeaponByName(meleeWeapon.getName()).setEquipped(TRUE);
        rightMelee = meleeWeapon;
        if (null != leftMelee && (meleeWeapon.getTwoHanded().equals(TRUE)
                || leftMelee.getTwoHanded().equals(TRUE))) {
            unequipLeftMelee();
        }
        if (null != rightHand) {
            unequipRightHand();
        }
        if (null != leftHand && (leftHand.getTwoHanded().equals(TRUE)
                || rightMelee.getTwoHanded().equals(TRUE))) {
            unequipLeftHand();
        }
    }

    public void equipLeftMelee(MeleeWeapon meleeWeapon) {
        if (null != leftMelee) {
            unequipLeftMelee();
        }
        findMeleeWeaponByName(meleeWeapon.getName()).setEquipped(TRUE);
        leftMelee = meleeWeapon;
        if (null != rightMelee && (meleeWeapon.getTwoHanded().equals(TRUE)
                || rightMelee.getTwoHanded().equals(TRUE))) {
            unequipRightMelee();
        }
        if (null != leftHand) {
            unequipLeftHand();
        }
        if (null != rightHand && (rightHand.getTwoHanded().equals(TRUE)
                || leftMelee.getTwoHanded().equals(TRUE))) {
            unequipRightHand();
        }
    }

    public void unequipRightHand() {
        if (null != rightHand) findRangedWeaponByName(rightHand.getName()).setEquipped(FALSE);
        rightHand = null;
    }

    public void unequipLeftHand() {
        if (null != leftHand) findRangedWeaponByName(leftHand.getName()).setEquipped(FALSE);
        leftHand = null;
    }

    public void unequipRightMelee() {
        if (null != rightMelee) findMeleeWeaponByName(rightMelee.getName()).setEquipped(FALSE);
        rightMelee = null;
    }

    public void unequipLeftMelee() {
        if (null != leftMelee) findMeleeWeaponByName(leftMelee.getName()).setEquipped(FALSE);
        leftMelee = null;
    }

    public void equipRightHand(RangedWeapon rangedWeapon) {
        if (null != rightHand) {
            unequipRightHand();
        }
        findRangedWeaponByName(rangedWeapon.getName()).setEquipped(TRUE);
        rightHand = rangedWeapon;
        if (null != leftHand && (rangedWeapon.getTwoHanded().equals(TRUE)
                || leftHand.getTwoHanded().equals(TRUE))) {
            unequipLeftHand();
        }
        if (null != rightMelee) {
            unequipRightMelee();
        }
        if (null != leftMelee && (leftMelee.getTwoHanded().equals(TRUE) || rightHand.getTwoHanded().equals(TRUE))) {
            unequipLeftMelee();
        }
    }

    public void equipLeftHand(RangedWeapon rangedWeapon) {
        if (null != leftHand) {
            unequipLeftHand();
        }
        findRangedWeaponByName(rangedWeapon.getName()).setEquipped(TRUE);
        leftHand = rangedWeapon;
        if (null != rightHand && (rangedWeapon.getTwoHanded().equals(TRUE)
                || rightHand.getTwoHanded().equals(TRUE))) {
            unequipRightHand();
        }
        if (null != leftMelee) {
            unequipLeftMelee();
        }
        if (null != rightMelee && (rightMelee.getTwoHanded().equals(TRUE) || leftHand.getTwoHanded().equals(TRUE))) {
            unequipRightMelee();
        }
    }

    public void setBonuses() {
        resetBonuses();
        Integer tombChests = 0;
        for (String modifier : this.modifiers) {
            findBonus(modifier);
        }
        for (Clothing clothing : this.getClothing()) {
            if (clothing.getEquipped().equals(TRUE)) {
                for (String string : clothing.getModifiers()) {
                    findBonus(string);
                }
                for (String string : clothing.getPenalties()) {
                    findPenalty(string);
                }
                if (clothing.getArmor() > 0 && clothing.getArmor() < this.armor) {
                    this.armor = clothing.getArmor();
                }
                if (clothing.getSpiritArmor() > 0 && clothing.getSpiritArmor() < this.spiritArmor) {
                    this.spiritArmor = clothing.getSpiritArmor();
                }
            }
            if (clothing.getWeight() < 0) {
                if (clothing.getEquipped().equals(TRUE)) {
                    this.maxWeight -= clothing.getWeight();
                }
            } else this.weight += clothing.getWeight();
            darkStoneCount += clothing.getDarkStone();

        }
        for (MeleeWeapon meleeWeapon : this.getMeleeWeapons()) {
            if (meleeWeapon.getEquipped().equals(TRUE)) {
                this.setCombat(meleeWeapon);
            }
            if (meleeWeapon.getArmor() > 0 && meleeWeapon.getArmor() < this.armor) {
                this.armor = meleeWeapon.getArmor();
            }
            if (meleeWeapon.getName().equals(RuleExceptionEnum.DARK_STONE_CLUB.label())) {
                traits.add(TraitsEnum.TRIBAL.label());
            }
            this.weight += meleeWeapon.getWeight();
            darkStoneCount += meleeWeapon.getDarkStone();

        }
        for (RangedWeapon rangedWeapon : this.getRangedWeapons()) {
            if (rangedWeapon.getEquipped().equals(TRUE)) {
                setRanged(rangedWeapon);
            }
            this.weight += rangedWeapon.getWeight();
            darkStoneCount += rangedWeapon.getDarkStone();
        }
        for (GearBase gearBase : this.getGear()) {
            for (String string : gearBase.getModifiers()) {
                findBonus(string);
            }
            for (String string : gearBase.getPenalties()) {
                findPenalty(string);
            }
            if (gearBase.getArmor() > 0 && gearBase.getArmor() < this.armor) {
                this.armor = gearBase.getArmor();
            }
            if (gearBase.getSpiritArmor() > 0 && gearBase.getSpiritArmor() < this.spiritArmor) {
                this.spiritArmor = gearBase.getSpiritArmor();
            }
            if (gearBase.getName().equals(RuleExceptionEnum.BOUNTY_HUNTERS_BADGE.label())) {
                traits.add(TraitsEnum.TRAVELER.label());
            }
            if (gearBase.getName().equals(RuleExceptionEnum.HARTHBONE_NECKLACE.label())) {
                traits.add(TraitsEnum.TRIBAL.label());
            }
            if (gearBase.getWeight() < 0) {
                this.maxWeight -= gearBase.getWeight();
            } else this.weight += gearBase.getWeight();
            darkStoneCount += gearBase.getDarkStone();
            if (gearBase.getName().equals(RuleExceptionEnum.TOMB_CHEST.label())) tombChests++;
        }
        Boolean markFaithfulBonus = FALSE;
        for (Attachment attachment : this.getAttachments()) {
            if (attachment.getEquipped().equals(TRUE)) {
                for (String string : attachment.getModifiers()) {
                    findBonus(string);
                }
                if (attachment.getName().equals(RuleExceptionEnum.MARK_OF_THE_FAITHFUL.label())) {
                    markFaithfulBonus = TRUE;
                }
                for (String string : attachment.getPenalties()) {
                    findPenalty(string);
                }
            }
            this.weight += attachment.getWeight();
            darkStoneCount += attachment.getDarkStone();
        }
        Boolean shieldBash = FALSE;
        Boolean spinningSlash = FALSE;
        for (Skill skill : this.getUpgrades()) {
            for (String string : skill.getModifiers()) {
                findBonus(string);
            }
            for (String string : skill.getPenalties()) {
                findPenalty(string);
            }
            if (skill.getName().equals(RuleExceptionEnum.PERSONAL_TOUCH.label())) {
                this.getCharacterClass().setDefense(4);
                for (Clothing clothing : this.getClothing()) {
                    if (clothing.getHat().equals(TRUE) && this.getClothing().size() > 3) {
                        this.getCharacterClass().setDefense(3);
                    }
                }
            }
            armor = skill.getArmor() < armor ? skill.getArmor() : armor;
            spiritArmor = skill.getSpiritArmor() < spiritArmor ? skill.getSpiritArmor() : spiritArmor;
            defense = skill.getDefense() < defense ? skill.getDefense() : defense;
            willpower = skill.getWillpower() < willpower ? skill.getWillpower() : willpower;
            meleeToHit = skill.getMeleeToHit() < meleeToHit ? skill.getMeleeToHit() : meleeToHit;
            rangedToHit = skill.getRangedToHit() < rangedToHit ? skill.getRangedToHit() : rangedToHit;
            meleeCritChance = skill.getMeleeCritChance() < meleeCritChance ? skill.getMeleeCritChance() : meleeCritChance;
            if (skill.getName().equals(RuleExceptionEnum.SPINNING_SLASH.label())) spinningSlash = TRUE;
            if (skill.getName().equals(RuleExceptionEnum.SHIELD_BASH.label())) shieldBash = TRUE;
        }
        for(PermanentCondition permanentCondition : getInjuries()) {
            for (String string : permanentCondition.getModifiers()) {
                findBonus(string);
            }
            for (String string : permanentCondition.getPenalties()) {
                findPenalty(string);
            }
        }
        for(PermanentCondition permanentCondition : getMadness()) {
            for (String string : permanentCondition.getModifiers()) {
                findBonus(string);
            }
            for (String string : permanentCondition.getPenalties()) {
                findPenalty(string);
            }
        }
        for(PermanentCondition permanentCondition : getMutations()) {
            for (String string : permanentCondition.getModifiers()) {
                findBonus(string);
            }
            for (String string : permanentCondition.getPenalties()) {
                findPenalty(string);
            }
        }
        this.maxWeight += this.strengthBonus + this.characterClass.getStrength() + 4;
        if (markFaithfulBonus.equals(TRUE))
            setHealthBonus(getHealthBonus() + getSanityBonus() + getCharacterClass().getSanity());
        darkStoneCount += darkStoneShards;
        darkStoneCount -= (8 * tombChests);
        darkStoneCount = darkStoneCount < 0 ? 0 : darkStoneCount;
        if (characterClass.getClassName().equals(CharacterClassEnum.JARGONO_NATIVE.male())) {
            //Spinning Slash Exception
            if ((null != leftMelee && leftMelee.getTwoHanded().equals(TRUE)) || (null != rightMelee && rightMelee.getTwoHanded().equals(TRUE))) {
                if (spinningSlash.equals(TRUE)) meleeDamageBonus++;
            }
            //Shield Bash Exception
            Boolean shieldEquipped = FALSE;
            if (null != leftMelee) {
                for(String trait : leftMelee.getTraits()) {
                    if(trait.equals(TraitsEnum.SHIELD.label())) shieldEquipped = TRUE;
                }
            }
            if (null != rightMelee) {
                for(String trait : rightMelee.getTraits()) {
                    if(trait.equals(TraitsEnum.SHIELD.label())) shieldEquipped = TRUE;
                }
            }
            if(shieldBash.equals(TRUE) && shieldEquipped.equals(TRUE)) combatBonus++;
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
        for (Attachment attachment : meleeWeapon.getAttachments()) {
            if (attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                this.combatBonus++;
            }
        }
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
        } else if (ModifiersEnum.SIDE_BAG_CAPACITY.label().equals(modifier)) {
            sideBagSize += 1;
        } else if (ModifiersEnum.MAX_CORRUPTION.label().equals(modifier)) {
            maxCorruption += 1;
        } else if (ModifiersEnum.MAX_WEIGHT.label().equals(modifier)) {
            maxWeight++;
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
        } else if (ModifiersEnum.SIDE_BAG_CAPACITY.label().equals(modifier)) {
            sideBagSize -= 1;
        } else if (ModifiersEnum.MAX_CORRUPTION.label().equals(modifier)) {
            maxCorruption -= 1;
        } else if (ModifiersEnum.MAX_WEIGHT.label().equals(modifier)) {
            maxWeight--;
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
        this.setArmor(7);
        this.setSpiritArmor(7);
        this.setMeleeToHitDie(6);
        this.setMeleeDamageDie(6);
        this.traits = getCharacterClass().getTraits();
        this.weight = 0;
        this.maxWeight = 0;
        this.sideBagSize = 5;
        this.maxCorruption = 5;
        this.darkStoneCount = 0;
        this.meleeCritChance = 6;
        this.rangedToHit = getCharacterClass().getRangedToHit();
        this.meleeToHit = getCharacterClass().getMeleeToHit();
        this.defense = getCharacterClass().getDefense();
        this.willpower = getCharacterClass().getWillpower();
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

    public Attachment findAttachmentByName(String name) {
        for (Attachment attachment : this.getAttachments()) {
            if (attachment.getName().equals(name)) return attachment;
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

    public void removeExp(Integer exp) {
        this.experience -= exp;
    }

    public void addDarkstoneShards(Integer darkStone) {
        this.darkStoneShards += darkStone;
    }

    public void removeDarkstoneShards(Integer darkStone) {
        this.darkStoneShards -= darkStone;
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

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void removeModifier(String modifier) {
        this.modifiers.remove(modifier);
    }

    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public void removeAttachment(Attachment attachment) {
        this.attachments.remove(attachment);
    }

    @NonNull
    public ArrayList<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(@NonNull ArrayList<Attachment> attachments) {
        this.attachments = attachments;
    }

    @NonNull
    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(@NonNull Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    @NonNull
    public Integer getCurrentSanity() {
        return currentSanity;
    }

    public void setCurrentSanity(@NonNull Integer currentSanity) {
        this.currentSanity = currentSanity;
    }

    @NonNull
    public ArrayList<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(@NonNull ArrayList<String> modifiers) {
        this.modifiers = modifiers;
    }

    @NonNull
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(@NonNull Integer weight) {
        this.weight = weight;
    }

    @NonNull
    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(@NonNull Integer maxWeight) {
        this.maxWeight = maxWeight;
    }

    @NonNull
    public ArrayList<String> getSideBag() {
        return sideBag;
    }

    public void setSideBag(@NonNull ArrayList<String> sideBag) {
        this.sideBag = sideBag;
    }

    @NonNull
    public Integer getSideBagSize() {
        return sideBagSize;
    }

    public void setSideBagSize(@NonNull Integer sideBagSize) {
        this.sideBagSize = sideBagSize;
    }

    public void addToSideBag(String string) {
        this.sideBag.add(string);
    }

    public void removeFromSideBag(String string) {
        this.sideBag.remove(string);
    }

    public void addMutation(PermanentCondition permanentCondition) {
        this.mutations.add(permanentCondition);
    }

    public void removeMutation(PermanentCondition permanentCondition) {
        this.mutations.remove(permanentCondition);
    }

    public void addInjury(PermanentCondition permanentCondition) {
        this.injuries.add(permanentCondition);
    }

    public void removeInjury(PermanentCondition permanentCondition) {
        this.injuries.remove(permanentCondition);
    }

    public void addMadness(PermanentCondition permanentCondition) {
        this.madness.add(permanentCondition);
    }

    public void removeMadness(PermanentCondition permanentCondition) {
        this.madness.remove(permanentCondition);
    }

    @NonNull
    public Integer getMaxCorruption() {
        return maxCorruption;
    }

    public void setMaxCorruption(@NonNull Integer maxCorruption) {
        this.maxCorruption = maxCorruption;
    }

    @NonNull
    public Integer getCurrentCorruption() {
        return currentCorruption;
    }

    public void setCurrentCorruption(@NonNull Integer currentCorruption) {
        this.currentCorruption = currentCorruption;
    }

    @NonNull
    public Integer getDarkStoneCount() {
        return darkStoneCount;
    }

    public void setDarkStoneCount(@NonNull Integer darkStoneCount) {
        this.darkStoneCount = darkStoneCount;
    }

    @NonNull
    public ArrayList<PermanentCondition> getInjuries() {
        return injuries;
    }

    public void setInjuries(@NonNull ArrayList<PermanentCondition> injuries) {
        this.injuries = injuries;
    }

    @NonNull
    public ArrayList<PermanentCondition> getMadness() {
        return madness;
    }

    public void setMadness(@NonNull ArrayList<PermanentCondition> madness) {
        this.madness = madness;
    }

    @NonNull
    public ArrayList<PermanentCondition> getMutations() {
        return mutations;
    }

    public void setMutations(@NonNull ArrayList<PermanentCondition> mutations) {
        this.mutations = mutations;
    }

    @NonNull
    public Transport getTransport() {
        return transport;
    }

    public void setTransport(@NonNull Transport transport) {
        this.transport = transport;
    }

    @NonNull
    public Integer getDefense() {
        return defense;
    }

    public void setDefense(@NonNull Integer defense) {
        this.defense = defense;
    }

    @NonNull
    public Integer getWillpower() {
        return willpower;
    }

    public void setWillpower(@NonNull Integer willpower) {
        this.willpower = willpower;
    }

    @NonNull
    public Integer getMeleeToHit() {
        return meleeToHit;
    }

    public void setMeleeToHit(@NonNull Integer meleeToHit) {
        this.meleeToHit = meleeToHit;
    }

    @NonNull
    public Integer getRangedToHit() {
        return rangedToHit;
    }

    public void setRangedToHit(@NonNull Integer rangedToHit) {
        this.rangedToHit = rangedToHit;
    }
}

