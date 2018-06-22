package com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterClassDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.ClothingDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.GearBaseDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.MeleeWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.RangedWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.SkillDao;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

import java.util.ArrayList;

@Database(entities = {SobCharacter.class, CharacterClass.class, GearBase.class, MeleeWeapon.class,
        RangedWeapon.class, Clothing.class, Skill.class}, version = 3)
@TypeConverters({GithubTypeConverters.class})
public abstract class SOBRoomDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
    public abstract CharacterClassDao characterClassDao();
    public abstract GearBaseDao gearBaseDao();
    public abstract MeleeWeaponDao meleeWeaponDao();
    public abstract RangedWeaponDao rangedWeaponDao();
    public abstract ClothingDao clothingDao();
    public abstract SkillDao skillDao();

    private static SOBRoomDatabase INSTANCE;

    public static SOBRoomDatabase getDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (SOBRoomDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SOBRoomDatabase.class, "sob_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final CharacterDao mCharacterDao;
        private final CharacterClassDao mCharacterClassDao;
        private final GearBaseDao mGearBaseDao;
        private final MeleeWeaponDao mMeleeWeaponDao;
        private final RangedWeaponDao mRangedWeaponDao;
        private final ClothingDao mClothingDao;
        private final SkillDao mSkillDao;

        PopulateDbAsync(SOBRoomDatabase db) {
            mCharacterDao = db.characterDao();
            mCharacterClassDao = db.characterClassDao();
            mGearBaseDao = db.gearBaseDao();
            mMeleeWeaponDao = db.meleeWeaponDao();
            mRangedWeaponDao = db.rangedWeaponDao();
            mClothingDao = db.clothingDao();
            mSkillDao = db.skillDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mCharacterClassDao.deleteAllCharacterClasses();
            mGearBaseDao.deleteAllGearBase();
            mMeleeWeaponDao.deleteAllMeleeWeapons();
            mRangedWeaponDao.deleteAllRangedWeapons();
            mClothingDao.deleteAllClothing();
            mSkillDao.deleteAllSkill();
            CharacterClass characterClass;
            Clothing clothing;
            GearBase gearBase;
            MeleeWeapon meleeWeapon;
            RangedWeapon rangedWeapon;
            ArrayList<String> traits = new ArrayList<>(0);

            //Initialize Classes
            //Rancher
            traits.add("Frontier");
            characterClass = new CharacterClass("Rancher", 2, 2, 3, 3, 4, 1, 14, 10, 4, 4, 4, 4, 2, 3, 2, traits);
            mCharacterClassDao.insert(characterClass);
            //Drifter
            traits = new ArrayList<>(0);
            traits.add("Traveler");
            traits.add("Frintier");
            traits.add("Strange");
            characterClass = new CharacterClass("Drifter",2,3,3,2,4,1,10,12,4,3,3,4,2,5,3,traits);
            mCharacterClassDao.insert(characterClass);
            //Outlaw
            traits = new ArrayList<>(0);
            traits.add("Outlaw");
            traits.add("Showman");
            characterClass = new CharacterClass("Outlaw", 3,2,1,2,3,4,12,12,4,4,5,4,2,4,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Jargono Native
            traits = new ArrayList<>(0);
            traits.add("OtherWorld");
            traits.add("Jargono");
            traits.add("Tribal");
            characterClass = new CharacterClass("Jargono Native", 4,2,3,3,1,2,11,11,4,4,5,4,2,4,2,traits);
            mCharacterClassDao.insert(characterClass);
            //U.S. Marshal
            traits = new ArrayList<>(0);
            traits.add("Law");
            traits.add("Traveler");
            characterClass = new CharacterClass("U.s. Marshal",3,4,2,2,1,3,10,10,3,4,4,4,2,4,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Preacher//Nun
            traits = new ArrayList<>(0);
            traits.add("Holy");
            characterClass = new CharacterClass("Preacher", 1,2,4,3,3,2,12,10,5,3,5,4,2,2,2,traits);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName("Nun");
            mCharacterClassDao.insert(characterClass);
            //Lawman
            traits = new ArrayList<>(0);
            traits.add("Law");
            traits.add("Frontier");
            characterClass = new CharacterClass("Lawman", 2,4,1,3,2,3,12,12,4,4,4,4,2,4,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Gunslinger
            traits = new ArrayList<>(0);
            traits.add("Showman");
            characterClass = new CharacterClass("Gunslinger", 3,3,2,2,2,4,10,12,5,4,3,5,1,6,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Gambler
            traits = new ArrayList<>(0);
            traits.add("Performer");
            traits.add("Showman");
            characterClass = new CharacterClass("Gambler", 3,4,1,2,2,3,10,10,4,4,4,5,2,5,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Dark Stone Shaman
            traits = new ArrayList<>(0);
            traits.add("Tribal");
            traits.add("Magik");
            characterClass = new CharacterClass("Dark Stone Shaman", 2,1,4,2,4,1,10,12,4,3,5,4,2,3,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Bandido//Bandida
            traits = new ArrayList<>(0);
            traits.add("Outlaw");
            characterClass = new CharacterClass("Bandido", 2,1,3,4,3,2,16,8,4,5,5,4,2,3,2,traits);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName("Bandida");
            mCharacterClassDao.insert(characterClass);
            //Frontier Doc
            traits = new ArrayList<>(0);
            traits.add("Frontier");
            traits.add("Medical");
            characterClass = new CharacterClass("Frontier Doc", 2,4,2,2,3,1,12,12,5,3,5,4,2,4,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Saloon Girl//Piano Player
            traits = new ArrayList<>(0);
            traits.add("Performer");
            characterClass = new CharacterClass("Saloon Girl", 4,3,3,1,2,3,8,14,3,4,4,4,2,5,2,traits);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName("Piano Player");
            mCharacterClassDao.insert(characterClass);
            //Wandering Samarai
            traits = new ArrayList<>(0);
            traits.add("Traveler");
            traits.add("Showman");
            traits.add("Samarai");
            characterClass = new CharacterClass("Wandering Samarai", 3,3,2,3,2,2,10,10,3,4,4,3,2,5,2,traits);
            mCharacterClassDao.insert(characterClass);
            //Indian Scout
            traits = new ArrayList<>(0);
            traits.add("Scout");
            traits.add("Tribal");
            characterClass = new CharacterClass("Indian Scout", 3,2,3,2,3,2,10,10,4,4,4,4,2,5,2,traits);
            mCharacterClassDao.insert(characterClass);

            //Initialize Starting Gear
            //Cowboy
            gearBase = new GearBase("Lasso");
            gearBase.addRestriction("Cowboy");
            gearBase.setSet("HCC");
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Bandana");
            clothing.setSell(50);
            clothing.setFace(true);
            mClothingDao.insert(clothing);

            //Initialize Personal Items
            gearBase = new GearBase("Ancient Coin");
            gearBase.setPersonal(true);
            gearBase.addModifier("Lore");
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Personal Journal");
            gearBase.addModifier("Spirit");
            gearBase.addModifier("Lore");
            gearBase.setPersonal(true);
            mGearBaseDao.insert(gearBase);


            //Initialize Gear by Set
            //Base
            gearBase = new GearBase("Accentuator Belt");
            gearBase.setWeight(-2);
            gearBase.setSell(350);
            gearBase.setArtifact(true);
            gearBase.setUpgrades(2);
            mGearBaseDao.insert(gearBase);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            mRangedWeaponDao.insert(rangedWeapon);
            gearBase = new GearBase("Mark of the Hunter");
            gearBase.setUpgrades(1);
            gearBase.setSell(100);
            gearBase.setDarkStone(1);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Deflection Field");
            gearBase.setArtifact(true);
            gearBase.setSell(525);
            gearBase.setDarkStone(1);
            gearBase.setWeight(1);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Teleportation Bracelet");
            gearBase.setWeight(1);
            gearBase.setSell(725);
            gearBase.setArtifact(true);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Old Map");
            gearBase.setSell(25);
            mGearBaseDao.insert(gearBase);
            rangedWeapon = new RangedWeapon("Plasma Arc", 8, 1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(675);
            mRangedWeaponDao.insert(rangedWeapon);
            gearBase = new GearBase("Void Crystals");
            gearBase.setSell(100);
            mGearBaseDao.insert(gearBase);

            //DD
            gearBase = new GearBase("Traveling Boots");
            gearBase.addModifier("Agility");
            gearBase.setWeight(1);
            gearBase.setUpgrades(1);
            gearBase.setSell(650);
            gearBase.setSet("DD");
            mGearBaseDao.insert(gearBase);

            //Jargono Native
            meleeWeapon = new MeleeWeapon("Dark Stone Daggers");
            meleeWeapon.setSet("HCJN");
            meleeWeapon.setCombat(2);
            meleeWeapon.addModifier("Inititative");
            meleeWeapon.addRestriction("Tribal");
            meleeWeapon.setTwoHanded(true);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setSell(625);
            mMeleeWeaponDao.insert(meleeWeapon);
            gearBase = new GearBase("Raptor Tooth Necklace");
            gearBase.addModifier("Cunning");
            gearBase.setPersonal(true);
            gearBase.setSet("HCJN");
            mGearBaseDao.insert(gearBase);

            //Shops
            gearBase = new GearBase("Tomb Chest");
            gearBase.setWeight(1);
            gearBase.setSell(500);
            mGearBaseDao.insert(gearBase);

            //Caverns of Cynder


            //Crimson Hand
            rangedWeapon = new RangedWeapon("Cult Rifle", 8, 2);
            rangedWeapon.setDamageBonus(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSet("CH");
            rangedWeapon.setSell(400);
            rangedWeapon.setUpgrades(2);
            mRangedWeaponDao.insert(rangedWeapon);

            //Lost Army
            gearBase = new GearBase("Flag of the Fallen");
            gearBase.setWeight(2);
            gearBase.setArtifact(true);
            gearBase.setSell(1250);
            gearBase.setSet("LA");
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Defender's Hat");
            gearBase.setWeight(1);
            gearBase.setUpgrades(2);
            gearBase.setSell(400);
            gearBase.setSet("LA");
            mGearBaseDao.insert(gearBase);
            rangedWeapon = new RangedWeapon("Lost Army Pistol", 7,1 );
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(700);
            rangedWeapon.setSet("LA");
            mRangedWeaponDao.insert(rangedWeapon);


            //Promo
            rangedWeapon = new RangedWeapon("The Grave Digger", 3, 2);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSet("P");
            rangedWeapon.setSell(400);
            mRangedWeaponDao.insert(rangedWeapon);
            clothing = new Clothing("Badlands Adventure Gear");
            clothing.setWeight(2);
            clothing.setCoat(true);
            clothing.setSell(1250);
            clothing.setSet("P");
            mClothingDao.insert(clothing);
            rangedWeapon = new RangedWeapon("Sawed-Off Shotgun", 3, 1);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.addRestriction("Outlaw");
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Outlaw Pistol", 5, 3);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(3);
            rangedWeapon.setSell(150);
            rangedWeapon.setSet("P");
            rangedWeapon.addRestriction("Outlaw");
            mRangedWeaponDao.insert(rangedWeapon);
            gearBase = new GearBase("Red Sash");
            gearBase.setWeight(1);
            gearBase.addModifier("Move");
            gearBase.addModifier("Agility");
            gearBase.addRestriction("Performer");
            gearBase.addRestriction("Showman");
            gearBase.setSell(750);
            gearBase.setSet("P");
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Telescope");
            gearBase.addModifier("Cunning");
            gearBase.setWeight(1);
            gearBase.setSell(225);
            gearBase.setSet("P");
            mGearBaseDao.insert(gearBase);
            meleeWeapon = new MeleeWeapon("Trun Gladius");
            meleeWeapon.setWeight(2);
            meleeWeapon.setTwoHanded(true);
            meleeWeapon.setDamageBonus(2);
            meleeWeapon.setSet("P");
            mMeleeWeaponDao.insert(meleeWeapon);

            Skill skill = new Skill("Guns Blazing", "Outlaw", "Shootin'");
            skill.setLevel(1);
            mSkillDao.insert(skill);
            skill.setName("Gunfighter");
            skill.setLevel(2);
            skill.addModifier("Max Grit");
            mSkillDao.insert(skill);
            skill = new Skill("Wild at Heart", "Outlaw", "Shootin'");
            skill.setLevel(3);
            skill.addModifier("Health");
            skill.addModifier("Health");
            skill.addModifier("Health");
            mSkillDao.insert(skill);
            skill.setName("Yeee-Haww!");
            skill.setLevel(4);
            skill.addModifier("Health");
            skill.addModifier("Health");
            mSkillDao.insert(skill);
            skill = new Skill("Outlaw Charm", "Outlaw", "Starting");
            mSkillDao.insert(skill);
            skill.setName("Reckless");
            mSkillDao.insert(skill);
            //Test Character
            mCharacterDao.deleteCharacterByName("Testes");
            SobCharacter sobCharacter = new SobCharacter("Testes", mCharacterClassDao.getCharacterClassByName("Rancher").getValue());
            mCharacterDao.insert(sobCharacter);
            return null;
        }
    }
}

