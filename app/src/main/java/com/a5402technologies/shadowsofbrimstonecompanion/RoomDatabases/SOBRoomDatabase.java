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

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.CharacterClassEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterClassDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.ClothingDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.GearBaseDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.MeleeWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.RangedWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.SkillDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.SetListEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.SkillTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.TraitsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;

@Database(entities = {SobCharacter.class, CharacterClass.class, GearBase.class, MeleeWeapon.class,
        RangedWeapon.class, Clothing.class, Skill.class}, version = 16)
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

        //TODO Move database population and finish

        @Override
        protected Void doInBackground(final Void... params) {
            mCharacterClassDao.deleteAllCharacterClasses();
            mGearBaseDao.deleteAllGearBase();
            mMeleeWeaponDao.deleteAllMeleeWeapons();
            mRangedWeaponDao.deleteAllRangedWeapons();
            mClothingDao.deleteAllClothing();
            mSkillDao.deleteAllSkill();
            mCharacterDao.deleteCharacterByName("Kristal");
            mCharacterDao.deleteCharacterByName("Paul");
            SobCharacter sobCharacter;
            SobCharacter Cowboy;
            CharacterClass characterClass;
            Clothing clothing;
            GearBase gearBase;
            MeleeWeapon meleeWeapon;
            RangedWeapon rangedWeapon;
            Skill skill;
            ArrayList<String> traits = new ArrayList<>(0);

            //Initialize Classes
            //Cowboy
            traits.add(TraitsEnum.FRONTIER.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.COWBOY.male(), 2,3,1,4,3,2,14,12,4,4,4,4,2,3,2,traits);
            Cowboy = new SobCharacter("Paul", characterClass);
            gearBase = new GearBase("Lasso");
            gearBase.addRestriction("Cowboy");
            gearBase.setSet("HCC");
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Bandana");
            clothing.setSell(50);
            clothing.setFace(true);
            mClothingDao.insert(clothing);
            characterClass.addStartingGear(gearBase);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            characterClass.addStartingRanged(rangedWeapon);


            //Rancher
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.FRONTIER.label());
            characterClass = new CharacterClass(CharacterClassEnum.RANCHER.male(), 2, 2, 3, 3, 4, 1, 14, 10, 4, 4, 4, 4, 2, 3, 2, traits);
            rangedWeapon = new RangedWeapon("Hunting Rifle", 12, 1);
            rangedWeapon.setDamageBonus(2);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setUpgrades(3);
            rangedWeapon.setSell(350);
            characterClass.addStartingRanged(rangedWeapon);
            mRangedWeaponDao.insert(rangedWeapon);
            mCharacterClassDao.insert(characterClass);
            //Drifter
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.TRAVELER.label());
            traits.add(TraitsEnum.FRONTIER.label());
            traits.add(TraitsEnum.STRANGE.label());
            characterClass = new CharacterClass(CharacterClassEnum.DRIFTER.male(),2,3,3,2,4,1,10,12,4,3,3,4,2,5,3,traits);
            rangedWeapon = new RangedWeapon("Trusty Pistol", 6, 0);
            rangedWeapon.setSell(600);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.addRestriction(TraitsEnum.FRONTIER.label());
            characterClass.addStartingRanged(rangedWeapon);
            mRangedWeaponDao.insert(rangedWeapon);
            mCharacterClassDao.insert(characterClass);
            //Outlaw
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.OUTLAW.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.OUTLAW.male(), 3,2,1,2,3,4,12,12,4,4,4,5,2,4,2,traits);
            rangedWeapon = new RangedWeapon("Outlaw Pistol", 5, 3);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(3);
            rangedWeapon.setSell(150);
            rangedWeapon.setSet(SetListEnum.PROMO.label());
            rangedWeapon.addRestriction(TraitsEnum.OUTLAW.label());
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            clothing = new Clothing("Bandana");
            clothing.setSell(50);
            clothing.setFace(true);
            characterClass.addStartingClothing(clothing);
            mCharacterClassDao.insert(characterClass);
            //Jargono Native
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.OTHERWORLD.label());
            traits.add(TraitsEnum.JARGONO.label());
            traits.add(TraitsEnum.TRIBAL.label());
            characterClass = new CharacterClass(CharacterClassEnum.JARGONO_NATIVE.male(), 4,2,3,3,1,2,11,11,4,4,5,4,2,4,2,traits);
            meleeWeapon = new MeleeWeapon("Dark Stone Blade");
            meleeWeapon.setWeight(1);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setSell(475);
            meleeWeapon.setCritChance(5);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            MeleeWeapon testRemove = meleeWeapon;
            meleeWeapon = new MeleeWeapon("Tribal Shield");
            meleeWeapon.setArmor(5);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(650);
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            mCharacterClassDao.insert(characterClass);
            characterClass.removeStartingMelee(testRemove);
            characterClass.removeStartingMelee(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Dark Stone Daggers");
            meleeWeapon.setSet("HCJN");
            meleeWeapon.setCombat(2);
            meleeWeapon.addModifier(ModifiersEnum.INITIATIVE.label());
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            meleeWeapon.setTwoHanded(true);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setSell(625);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            //Test Character Start
            sobCharacter = new SobCharacter("Kristal", characterClass);
            sobCharacter.addMeleeWeapon(meleeWeapon);
            meleeWeapon.setEquipped(Boolean.TRUE);
            sobCharacter.setRightMelee(meleeWeapon);
            //End
            gearBase = new GearBase("Raptor Tooth Necklace");
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            gearBase.setPersonal(true);
            gearBase.setSet("HCJN");
            mGearBaseDao.insert(gearBase);
            //Test
            sobCharacter.addGear(gearBase);
            //End
            rangedWeapon = new RangedWeapon("Jargono Bow", 7, 1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSell(600);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCritChance(5);
            rangedWeapon.setDamageDie(8);
            mRangedWeaponDao.insert(rangedWeapon);
            //U.S. Marshal
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.LAW.label());
            traits.add(TraitsEnum.TRAVELER.label());
            characterClass = new CharacterClass(CharacterClassEnum.US_MARSHAL.male(),3,4,2,2,1,3,10,10,3,4,4,4,2,4,2,traits);
            rangedWeapon = new RangedWeapon("Shotgun", 5, 1);
            rangedWeapon.setToHitDie(8);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSell(300);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            gearBase = new GearBase("Marshal Badge");
            gearBase.addRestriction(TraitsEnum.LAW.label());
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            mCharacterClassDao.insert(characterClass);
            //Preacher//Nun
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.HOLY.label());
            characterClass = new CharacterClass(CharacterClassEnum.PREACHER.male(), 1,2,4,3,3,2,12,10,5,3,5,4,2,2,2,traits);
            meleeWeapon = new MeleeWeapon("Holy Book");
            meleeWeapon.addModifier(ModifiersEnum.FAITH.label());
            meleeWeapon.setCombat(1);
            meleeWeapon.addRestriction(TraitsEnum.HOLY.label());
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(300);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName(CharacterClassEnum.PREACHER.female());
            mCharacterClassDao.insert(characterClass);
            //Lawman
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.LAW.label());
            traits.add(TraitsEnum.FRONTIER.label());
            characterClass = new CharacterClass(CharacterClassEnum.LAWMAN.male(), 2,4,1,3,2,3,12,12,4,4,4,4,2,4,2,traits);
            rangedWeapon = new RangedWeapon("Peacekeeper Pistol", 6, 3);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(250);
            rangedWeapon.addRestriction(TraitsEnum.LAW.label());
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            gearBase = new GearBase("Sheriff Badge");
            gearBase.addRestriction(TraitsEnum.LAW.label());
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            mCharacterClassDao.insert(characterClass);
            //Gunslinger
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.GUNSLINGER.male(), 3,3,2,2,2,4,10,12,5,4,3,5,1,6,2,traits);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            mCharacterClassDao.insert(characterClass);
            //Gambler
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.PERFORMER.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.GAMBLER.male(), 3,4,1,2,2,3,10,10,4,4,4,5,2,5,2,traits);
            rangedWeapon = mRangedWeaponDao.getByName("Pistol").getValue();
            characterClass.addStartingRanged(rangedWeapon);
            mCharacterClassDao.insert(characterClass);
            //Dark Stone Shaman
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.TRIBAL.label());
            traits.add(TraitsEnum.MAGIK.label());
            characterClass = new CharacterClass(CharacterClassEnum.DARK_STONE_SHAMAN.male(), 2,1,4,2,4,1,10,12,4,3,5,4,2,3,2,traits);
            meleeWeapon = new MeleeWeapon("Shaman Staff");
            meleeWeapon.addModifier(ModifiersEnum.MAGIK.label());
            meleeWeapon.addRestriction(TraitsEnum.MAGIK.label());
            meleeWeapon.setWeight(1);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setSell(450);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            gearBase = new GearBase("Dark Stone Satchel");
            gearBase.addRestriction(TraitsEnum.MAGIK.label());
            gearBase.setSell(850);
            gearBase.setWeight(1);
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            mCharacterClassDao.insert(characterClass);
            //Bandido//Bandida
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.OUTLAW.label());
            characterClass = new CharacterClass(CharacterClassEnum.BANDIDO.male(), 2,1,3,4,3,2,16,8,4,5,5,4,2,3,2,traits);
            rangedWeapon = mRangedWeaponDao.getByName("Pistol").getValue();
            characterClass.addStartingRanged(rangedWeapon);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName(CharacterClassEnum.BANDIDO.female());
            mCharacterClassDao.insert(characterClass);
            gearBase = new GearBase("Dynamite Satchel");
            gearBase.addRestriction(TraitsEnum.OUTLAW.label());
            gearBase.setWeight(1);
            gearBase.setSell(400);
            //Frontier Doc
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.FRONTIER.label());
            traits.add(TraitsEnum.MEDICAL.label());
            characterClass = new CharacterClass(CharacterClassEnum.FRONTIER_DOC.male(), 2,4,2,2,3,1,12,12,5,3,5,4,2,4,2,traits);
            gearBase = new GearBase("Doctor's Bag");
            gearBase.addRestriction(TraitsEnum.MEDICAL.label());
            gearBase.setWeight(1);
            gearBase.setSell(800);
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            mCharacterClassDao.insert(characterClass);
            meleeWeapon = new MeleeWeapon("Surgeon's Saw");
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setSell(450);
            meleeWeapon.addRestriction(TraitsEnum.MEDICAL.label());
            mMeleeWeaponDao.insert(meleeWeapon);
            gearBase = new GearBase("Collection Jar");
            gearBase.setSell(800);
            mGearBaseDao.insert(gearBase);
            //Saloon Girl//Piano Player
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.PERFORMER.label());
            characterClass = new CharacterClass(CharacterClassEnum.SALOON_GIRL.female(), 4,3,3,1,2,3,8,14,3,4,4,4,2,5,2,traits);
            rangedWeapon = new RangedWeapon("Hold-Out Pistol", 3, 1);
            rangedWeapon.setCritChance(5);
            rangedWeapon.addRestriction(TraitsEnum.PERFORMER.label());
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(200);
            rangedWeapon.setFree(true);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName(CharacterClassEnum.SALOON_GIRL.male());
            mCharacterClassDao.insert(characterClass);
            //Wandering Samarai
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.SAMARAI.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            traits.add(TraitsEnum.TRAVELER.label());
            characterClass = new CharacterClass(CharacterClassEnum.WANDERING_SAMARAI.male(), 3,3,2,3,2,2,10,10,3,4,4,3,2,5,2,traits);
            meleeWeapon = new MeleeWeapon("Wanderer's Katana");
            meleeWeapon.addModifier(ModifiersEnum.MAX_FURY.label());
            meleeWeapon.addModifier(ModifiersEnum.MAX_FURY.label());
            meleeWeapon.setTwoHanded(true);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(400);
            meleeWeapon.setUpgrades(2);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            mCharacterClassDao.insert(characterClass);
            clothing = new Clothing("Ronin's Helmet");
            clothing.addRestriction(TraitsEnum.SAMARAI.label());
            clothing.setFace(true);
            clothing.setHat(true);
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.setSell(600);
            mClothingDao.insert(clothing);
            clothing = new Clothing("Samarai Armor");
            clothing.addRestriction(TraitsEnum.SAMARAI.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addPenalty(ModifiersEnum.INITIATIVE.label());
            clothing.setWeight(1);
            clothing.setArmor(5);
            clothing.setUpgrades(2);
            clothing.setSell(500);
            mClothingDao.insert(clothing);
            //Indian Scout
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.SCOUT.label());
            traits.add(TraitsEnum.TRIBAL.label());
            characterClass = new CharacterClass(CharacterClassEnum.INDIAN_SCOUT.male(), 3,2,3,2,3,2,10,10,4,4,4,4,2,5,2,traits);
            rangedWeapon = new RangedWeapon("Carbine", 8, 3);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSell(400);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            meleeWeapon = new MeleeWeapon("Indian Hatchet");
            meleeWeapon.setDamageBonus(1);
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            meleeWeapon.addRestriction(TraitsEnum.TRAVELER.label());
            meleeWeapon.addRestriction(TraitsEnum.FRONTIER.label());
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setSell(250);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            mCharacterClassDao.insert(characterClass);

            //Initialize Starting Gear
            //Cowboy


            //Initialize Personal Items
            gearBase = new GearBase("Ancient Coin");
            gearBase.setPersonal(true);
            gearBase.addModifier("Lore");
            Cowboy.addGear(gearBase);
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
            Cowboy.addGear(gearBase);
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
            //Test
            sobCharacter.addGear(gearBase);
            //End
            rangedWeapon = new RangedWeapon("Plasma Arc", 8, 1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(675);
            mRangedWeaponDao.insert(rangedWeapon);
            //Test
            sobCharacter.addRangedWeapon(rangedWeapon);
            //End
            gearBase = new GearBase("Void Crystals");
            gearBase.setSell(100);
            mGearBaseDao.insert(gearBase);
            //Test
            sobCharacter.addGear(gearBase);
            //End

            //DD
            clothing = new Clothing("Traveling Boots");
            gearBase.addModifier("Agility");
            gearBase.setWeight(1);
            gearBase.setUpgrades(1);
            gearBase.setSell(650);
            gearBase.setSet("DD");
            clothing.setBoots(Boolean.TRUE);
            mClothingDao.insert(clothing);
            //Test
            clothing.setEquipped(Boolean.TRUE);
            sobCharacter.addClothing(clothing);

            //Jargono Native


            //Shops
            gearBase = new GearBase("Tomb Chest");
            gearBase.setWeight(1);
            gearBase.setSell(500);
            mGearBaseDao.insert(gearBase);
            Cowboy.addGear(gearBase);

            //Caverns of Cynder


            //Crimson Hand
            rangedWeapon = new RangedWeapon("Cult Rifle", 8, 2);
            rangedWeapon.setDamageBonus(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSet(SetListEnum.CRIMSON_HAND.code());
            rangedWeapon.setSell(400);
            rangedWeapon.setUpgrades(2);
            mRangedWeaponDao.insert(rangedWeapon);
            Cowboy.addRangedWeapon(rangedWeapon);
            //Lost Army
            gearBase = new GearBase("Flag of the Fallen");
            gearBase.setWeight(2);
            gearBase.setArtifact(true);
            gearBase.setSell(1250);
            gearBase.setSet("LA");
            mGearBaseDao.insert(gearBase);
            Cowboy.addGear(gearBase);
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
            //Test
            sobCharacter.addRangedWeapon(rangedWeapon);
            //End


            //Promo
            rangedWeapon = new RangedWeapon("The Grave Digger", 3, 2);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.setSet("P");
            rangedWeapon.setSell(400);
            mRangedWeaponDao.insert(rangedWeapon);
            Cowboy.addRangedWeapon(rangedWeapon);
            clothing = new Clothing("Badlands Adventure Gear");
            clothing.setWeight(2);
            clothing.setCoat(true);
            clothing.setSell(1250);
            clothing.setSet("P");
            mClothingDao.insert(clothing);
            Cowboy.addClothing(clothing);
            rangedWeapon = new RangedWeapon("Sawed-Off Shotgun", 3, 1);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setTwoHanded(true);
            rangedWeapon.addRestriction("Outlaw");
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            mRangedWeaponDao.insert(rangedWeapon);
            Cowboy.addRangedWeapon(rangedWeapon);
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
            //Test
            sobCharacter.addGear(gearBase);
            //End
            meleeWeapon = new MeleeWeapon("Trun Gladius");
            meleeWeapon.setWeight(2);
            meleeWeapon.setTwoHanded(true);
            meleeWeapon.setDamageBonus(2);
            meleeWeapon.setSet("P");
            mMeleeWeaponDao.insert(meleeWeapon);
            //Test
            sobCharacter.addMeleeWeapon(meleeWeapon);
            //End

            //OUTLAW SHOOTIN' UPGRADES
            skill = new Skill("Guns Blazing", SkillTypeEnum.SHOOTIN.label());
            skill.setLevel(1);
            mSkillDao.insert(skill);
            skill.setName("Gunfighter");
            skill.setLevel(2);
            skill.addModifier("Max Grit");
            mSkillDao.insert(skill);
            skill = new Skill("Wild at Heart", SkillTypeEnum.SHOOTIN.label());
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
            //OUTLAW STARTING UPGRADES
            skill = new Skill("Outlaw Charm", CharacterClassEnum.OUTLAW.male());
            mSkillDao.insert(skill);
            skill.setName("Reckless");
            mSkillDao.insert(skill);
            skill.setName("Hitman");
            skill.addModifier(ModifiersEnum.MOVE.label());
            mSkillDao.insert(skill);
            //Jargono Native Starting Skills
            skill = new Skill("Serpent Slayer", CharacterClassEnum.JARGONO_NATIVE.male());
            skill.addModifier(ModifiersEnum.MOVE.label());

            for(GearBase gearBase1 : Cowboy.getCharacterClass().getStartingGear()) {
                Cowboy.addGear(gearBase1);
            }
            for(Clothing clothing1 : Cowboy.getCharacterClass().getStartingClothing()) {
                Cowboy.addClothing(clothing1);
            }
            for(RangedWeapon rangedWeapon1 : Cowboy.getCharacterClass().getStartingRanged()) {
                Cowboy.addRangedWeapon(rangedWeapon1);
            }
            for(MeleeWeapon meleeWeapon1 : Cowboy.getCharacterClass().getStartingMelee()) {
                Cowboy.addMeleeWeapon(meleeWeapon1);
            }
            mCharacterDao.insert(sobCharacter);
            mCharacterDao.insert(Cowboy);
            return null;
        }
    }
}

