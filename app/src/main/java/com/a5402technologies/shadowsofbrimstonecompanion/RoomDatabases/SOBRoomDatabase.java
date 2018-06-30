package com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.telephony.mbms.MbmsDownloadReceiver;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.AttachmentDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterClassDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.ClothingDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.GearBaseDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.MeleeWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.RangedWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.SkillDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.CharacterClassEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.SetListEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ShopEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.SkillTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.TraitsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

import java.util.ArrayList;
import java.util.logging.SocketHandler;

import static java.lang.Boolean.TRUE;

@Database(entities = {SobCharacter.class, CharacterClass.class, GearBase.class, MeleeWeapon.class,
        RangedWeapon.class, Clothing.class, Skill.class, Attachment.class}, version = 20)
@TypeConverters({GithubTypeConverters.class})
public abstract class SOBRoomDatabase extends RoomDatabase {
    private static SOBRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

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

    public abstract CharacterDao characterDao();

    public abstract CharacterClassDao characterClassDao();

    public abstract GearBaseDao gearBaseDao();

    public abstract MeleeWeaponDao meleeWeaponDao();

    public abstract RangedWeaponDao rangedWeaponDao();

    public abstract ClothingDao clothingDao();

    public abstract SkillDao skillDao();

    public abstract AttachmentDao attachmentDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final CharacterDao mCharacterDao;
        private final CharacterClassDao mCharacterClassDao;
        private final GearBaseDao mGearBaseDao;
        private final MeleeWeaponDao mMeleeWeaponDao;
        private final RangedWeaponDao mRangedWeaponDao;
        private final ClothingDao mClothingDao;
        private final SkillDao mSkillDao;
        private final AttachmentDao mAttacmentDao;

        PopulateDbAsync(SOBRoomDatabase db) {
            mCharacterDao = db.characterDao();
            mCharacterClassDao = db.characterClassDao();
            mGearBaseDao = db.gearBaseDao();
            mMeleeWeaponDao = db.meleeWeaponDao();
            mRangedWeaponDao = db.rangedWeaponDao();
            mClothingDao = db.clothingDao();
            mSkillDao = db.skillDao();
            mAttacmentDao = db.attachmentDao();
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
            mAttacmentDao.deleteAllAttachment();
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
            Attachment attachment;
            ArrayList<String> traits = new ArrayList<>(0);

            //Initialize Classes
            //Cowboy
            traits.add(TraitsEnum.FRONTIER.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.COWBOY.male(), 2, 3, 1, 4, 3, 2, 14, 12, 4, 4, 4, 4, 2, 3, 2, traits);
            Cowboy = new SobCharacter("Paul", characterClass);
            gearBase = new GearBase("Lasso");
            gearBase.addRestriction("Cowboy");
            gearBase.setSet(SetListEnum.COWBOY.code());
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            clothing = new Clothing("Bandana");
            clothing.setSell(50);
            clothing.setFace(TRUE);
            mClothingDao.insert(clothing);
            characterClass.addStartingClothing(clothing);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            characterClass.addStartingRanged(rangedWeapon);
            skill = new Skill("Trick Shooting", CharacterClassEnum.COWBOY.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Daredevil", CharacterClassEnum.COWBOY.male());
            skill.addModifier(ModifiersEnum.MOVE.label());
            skill.addModifier(ModifiersEnum.MOVE.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Watchman", CharacterClassEnum.COWBOY.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);


            //Rancher
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.FRONTIER.label());
            characterClass = new CharacterClass(CharacterClassEnum.RANCHER.male(), 2, 2, 3, 3, 4, 1, 14, 10, 4, 4, 4, 4, 2, 3, 2, traits);
            rangedWeapon = new RangedWeapon("Hunting Rifle", 12, 1);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setDamageBonus(2);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setUpgrades(3);
            rangedWeapon.setCost(1500);
            rangedWeapon.setSell(350);
            characterClass.addStartingRanged(rangedWeapon);
            mRangedWeaponDao.insert(rangedWeapon);
            skill = new Skill("Farmstead Defender", CharacterClassEnum.RANCHER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Swinging Rifle", CharacterClassEnum.RANCHER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Home Remedies", CharacterClassEnum.RANCHER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Drifter
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.TRAVELER.label());
            traits.add(TraitsEnum.FRONTIER.label());
            traits.add(TraitsEnum.STRANGE.label());
            characterClass = new CharacterClass(CharacterClassEnum.DRIFTER.male(), 2, 3, 3, 2, 4, 1, 10, 12, 4, 3, 3, 4, 2, 5, 3, traits);
            rangedWeapon = new RangedWeapon("Trusty Pistol", 6, 0);
            rangedWeapon.setSell(600);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.addRestriction(TraitsEnum.FRONTIER.label());
            characterClass.addStartingRanged(rangedWeapon);
            mRangedWeaponDao.insert(rangedWeapon);
            skill = new Skill("Gunfighter(Drifter)", CharacterClassEnum.DRIFTER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("No Name", CharacterClassEnum.DRIFTER.male());
            skill.addModifier(ModifiersEnum.AGILITY.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Resourceful", CharacterClassEnum.DRIFTER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Drifter's Secret", CharacterClassEnum.DRIFTER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Outlaw
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.OUTLAW.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.OUTLAW.male(), 3, 2, 1, 2, 3, 4, 12, 12, 4, 4, 4, 5, 2, 4, 2, traits);
            rangedWeapon = new RangedWeapon("Outlaw Pistol", 5, 3);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(3);
            rangedWeapon.setSell(150);
            rangedWeapon.setSet(SetListEnum.PROMO.code());
            rangedWeapon.addRestriction(TraitsEnum.OUTLAW.label());
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            clothing = new Clothing("Bandana");
            clothing.setSell(50);
            clothing.setFace(TRUE);
            characterClass.addStartingClothing(clothing);
            //OUTLAW STARTING UPGRADES
            skill = new Skill("Outlaw Charm", CharacterClassEnum.OUTLAW.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill.setName("Reckless");
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill.setName("Hitman");
            skill.addModifier(ModifiersEnum.MOVE.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            characterClass.addStartingSkill(skill);

            mCharacterClassDao.insert(characterClass);
            //Jargono Native
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.OTHERWORLD.label());
            traits.add(TraitsEnum.JARGONO.label());
            traits.add(TraitsEnum.TRIBAL.label());
            characterClass = new CharacterClass(CharacterClassEnum.JARGONO_NATIVE.male(), 4, 2, 3, 3, 1, 2, 11, 11, 4, 4, 5, 4, 2, 4, 2, traits);
            meleeWeapon = new MeleeWeapon("Dark Stone Blade");
            meleeWeapon.setWeight(1);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setSell(475);
            meleeWeapon.setCritChance(5);
            meleeWeapon.setJargonoArtifact(TRUE);
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
            //Jargono Native Starting Skills
            skill = new Skill("Serpent Slayer", CharacterClassEnum.JARGONO_NATIVE.male());
            skill.addModifier(ModifiersEnum.MOVE.label());
            mSkillDao.insert(skill);
            characterClass.addStartingSkill(skill);
            //TODO method for changing starting gear
            skill = new Skill( "Pit Fighter", CharacterClassEnum.JARGONO_NATIVE.male());
            mSkillDao.insert(skill);
            skill = new Skill("Treetop Hunter", CharacterClassEnum.JARGONO_NATIVE.male());
            skill.setRangedToHit(4);
            mSkillDao.insert(skill);
            characterClass.addStartingSkill(skill);
            mCharacterClassDao.insert(characterClass);
            //Test
            characterClass.removeStartingMelee(testRemove);
            characterClass.removeStartingMelee(meleeWeapon);
            //End
            meleeWeapon = new MeleeWeapon("Dark Stone Daggers");
            meleeWeapon.setSet(SetListEnum.JARGONO_NATIVE.code());
            meleeWeapon.setCombat(2);
            meleeWeapon.addModifier(ModifiersEnum.INITIATIVE.label());
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setSell(625);
            mMeleeWeaponDao.insert(meleeWeapon);
            //Test
            characterClass.addStartingMelee(meleeWeapon);
            sobCharacter = new SobCharacter("Kristal", characterClass);
            sobCharacter.addMeleeWeapon(meleeWeapon);
            meleeWeapon.setEquipped(TRUE);
            sobCharacter.setRightMelee(meleeWeapon);
            //End
            gearBase = new GearBase("Raptor Tooth Necklace");
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            gearBase.setPersonal(TRUE);
            gearBase.setSet(SetListEnum.JARGONO_NATIVE.code());
            mGearBaseDao.insert(gearBase);
            //Test
            sobCharacter.addGear(gearBase);
            //End
            rangedWeapon = new RangedWeapon("Jargono Bow", 7, 1);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setSell(600);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCritChance(5);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setSet(SetListEnum.JARGONO_NATIVE.code());
            mRangedWeaponDao.insert(rangedWeapon);
            //U.S. Marshal
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.LAW.label());
            traits.add(TraitsEnum.TRAVELER.label());
            characterClass = new CharacterClass(CharacterClassEnum.US_MARSHAL.male(), 3, 4, 2, 2, 1, 3, 10, 10, 3, 4, 4, 4, 2, 4, 2, traits);
            rangedWeapon = new RangedWeapon("Shotgun", 5, 1);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setToHitDie(8);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setSell(300);
            rangedWeapon.setCost(1200);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            gearBase = new GearBase("Marshal Badge");
            gearBase.addRestriction(TraitsEnum.LAW.label());
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            skill = new Skill("Hardened Resolve", CharacterClassEnum.US_MARSHAL.male());
            skill.addModifier(ModifiersEnum.MAX_SANITY.label());
            skill.addModifier(ModifiersEnum.MAX_SANITY.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Cleaning up the West", CharacterClassEnum.US_MARSHAL.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Rolling Thunder", CharacterClassEnum.US_MARSHAL.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Preacher//Nun
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.HOLY.label());
            characterClass = new CharacterClass(CharacterClassEnum.PREACHER.male(), 1, 2, 4, 3, 3, 2, 12, 10, 5, 3, 5, 4, 2, 2, 2, traits);
            meleeWeapon = new MeleeWeapon("Holy Book");
            meleeWeapon.setShop(ShopEnum.CHURCH.label());
            meleeWeapon.addModifier(ModifiersEnum.FAITH.label());
            meleeWeapon.setCombat(1);
            meleeWeapon.addRestriction(TraitsEnum.HOLY.label());
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(300);
            meleeWeapon.setCost(800);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            skill = new Skill("Firebrand", CharacterClassEnum.PREACHER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Redemptionist", CharacterClassEnum.PREACHER.male());
            //TODO hard code no guns for preacher without this skill
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Missionary", CharacterClassEnum.PREACHER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName(CharacterClassEnum.PREACHER.female());
            mCharacterClassDao.insert(characterClass);
            //Lawman
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.LAW.label());
            traits.add(TraitsEnum.FRONTIER.label());
            characterClass = new CharacterClass(CharacterClassEnum.LAWMAN.male(), 2, 4, 1, 3, 2, 3, 12, 12, 4, 4, 4, 4, 2, 4, 2, traits);
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
            skill = new Skill("Frontier Justice", CharacterClassEnum.LAWMAN.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Strong Leadership", CharacterClassEnum.LAWMAN.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Iron Will", CharacterClassEnum.LAWMAN.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Gunslinger
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.GUNSLINGER.male(), 3, 3, 2, 2, 2, 4, 10, 12, 5, 4, 3, 5, 1, 6, 2, traits);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            skill = new Skill("Quickdraw", CharacterClassEnum.GUNSLINGER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Pistol Fanning", CharacterClassEnum.GUNSLINGER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Reload", CharacterClassEnum.GUNSLINGER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Gambler
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.PERFORMER.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            characterClass = new CharacterClass(CharacterClassEnum.GAMBLER.male(), 3, 4, 1, 2, 2, 3, 10, 10, 4, 4, 4, 5, 2, 5, 2, traits);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            characterClass.addStartingRanged(rangedWeapon);
            skill = new Skill("High Roller", CharacterClassEnum.GAMBLER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Side Bet", CharacterClassEnum.GAMBLER.male());
            skill.addModifier(ModifiersEnum.LUCK.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Fancy Footwork", CharacterClassEnum.GAMBLER.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Dark Stone Shaman
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.TRIBAL.label());
            traits.add(TraitsEnum.MAGIK.label());
            characterClass = new CharacterClass(CharacterClassEnum.DARK_STONE_SHAMAN.male(), 2, 1, 4, 2, 4, 1, 10, 12, 4, 3, 5, 4, 2, 3, 2, traits);
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
            skill = new Skill("Spirit Shaper", CharacterClassEnum.DARK_STONE_SHAMAN.male());
            skill.addModifier(ModifiersEnum.MAX_GRIT.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Spirit Guardian", CharacterClassEnum.DARK_STONE_SHAMAN.male());
            skill.addModifier(ModifiersEnum.STRENGTH.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Spirit Hunter", CharacterClassEnum.DARK_STONE_SHAMAN.male());
            skill.addModifier(ModifiersEnum.INITIATIVE.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            //Bandido//Bandida
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.OUTLAW.label());
            characterClass = new CharacterClass(CharacterClassEnum.BANDIDO.male(), 2, 1, 3, 4, 3, 2, 16, 8, 4, 5, 5, 4, 2, 3, 2, traits);
            rangedWeapon = new RangedWeapon("Pistol", 6, 2);
            rangedWeapon.setSell(150);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setCost(500);
            characterClass.addStartingRanged(rangedWeapon);
            skill = new Skill("Twin Guns", CharacterClassEnum.BANDIDO.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Explosives Expert", CharacterClassEnum.BANDIDO.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Swindler", CharacterClassEnum.BANDIDO.male());
            skill.addModifier(ModifiersEnum.COMBAT.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
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
            characterClass = new CharacterClass(CharacterClassEnum.FRONTIER_DOC.male(), 2, 4, 2, 2, 3, 1, 12, 12, 5, 3, 5, 4, 2, 4, 2, traits);
            gearBase = new GearBase("Doctor's Bag");
            gearBase.addRestriction(TraitsEnum.MEDICAL.label());
            gearBase.setWeight(1);
            gearBase.setSell(800);
            mGearBaseDao.insert(gearBase);
            characterClass.addStartingGear(gearBase);
            skill = new Skill("Expert Surgeon", CharacterClassEnum.FRONTIER_DOC.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Battlefield Experience", CharacterClassEnum.FRONTIER_DOC.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Field Research", CharacterClassEnum.FRONTIER_DOC.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
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
            characterClass = new CharacterClass(CharacterClassEnum.SALOON_GIRL.female(), 4, 3, 3, 1, 2, 3, 8, 14, 3, 4, 4, 4, 2, 5, 2, traits);
            rangedWeapon = new RangedWeapon("Hold-Out Pistol", 3, 1);
            rangedWeapon.setCritChance(5);
            rangedWeapon.addRestriction(TraitsEnum.PERFORMER.label());
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(200);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setCost(800);
            rangedWeapon.setFree(TRUE);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            skill = new Skill("Acrobatic Dodge", CharacterClassEnum.SALOON_GIRL.female());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Knockout Punch", CharacterClassEnum.SALOON_GIRL.female());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Dirty Fightin'", CharacterClassEnum.SALOON_GIRL.female());
            skill.addModifier(ModifiersEnum.MELEE_DAMAGE.label());
            skill.addModifier(ModifiersEnum.RANGED_DAMAGE.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            characterClass.setClassName(CharacterClassEnum.SALOON_GIRL.male());
            mCharacterClassDao.insert(characterClass);
            //Wandering Samarai
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.SAMARAI.label());
            traits.add(TraitsEnum.SHOWMAN.label());
            traits.add(TraitsEnum.TRAVELER.label());
            characterClass = new CharacterClass(CharacterClassEnum.WANDERING_SAMARAI.male(), 3, 3, 2, 3, 2, 2, 10, 10, 3, 4, 4, 3, 2, 5, 2, traits);
            meleeWeapon = new MeleeWeapon("Wanderer's Katana");
            meleeWeapon.addModifier(ModifiersEnum.MAX_FURY.label());
            meleeWeapon.addModifier(ModifiersEnum.MAX_FURY.label());
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(400);
            meleeWeapon.setUpgrades(2);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            skill = new Skill("Ronin", CharacterClassEnum.WANDERING_SAMARAI.male());
            skill.addModifier(ModifiersEnum.MAX_FURY.label());
            skill.addModifier(ModifiersEnum.STRENGTH.label());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Quiet Traveler", CharacterClassEnum.WANDERING_SAMARAI.male());
            //TODO Hard Code this Skill for TwoHanded melee
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Sword Master", CharacterClassEnum.WANDERING_SAMARAI.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);
            clothing = new Clothing("Ronin's Helmet");
            clothing.addRestriction(TraitsEnum.SAMARAI.label());
            clothing.setFace(TRUE);
            clothing.setHat(TRUE);
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.setSell(600);
            mClothingDao.insert(clothing);
            clothing = new Clothing("Samarai Armor");
            clothing.addRestriction(TraitsEnum.SAMARAI.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addPenalty(ModifiersEnum.INITIATIVE.label());
            clothing.setTorso(TRUE);
            clothing.setWeight(1);
            clothing.setArmor(5);
            clothing.setUpgrades(2);
            clothing.setSell(500);
            mClothingDao.insert(clothing);
            //Indian Scout
            traits = new ArrayList<>(0);
            traits.add(TraitsEnum.SCOUT.label());
            traits.add(TraitsEnum.TRIBAL.label());
            characterClass = new CharacterClass(CharacterClassEnum.INDIAN_SCOUT.male(), 3, 2, 3, 2, 3, 2, 10, 10, 4, 4, 4, 4, 2, 5, 2, traits);
            rangedWeapon = new RangedWeapon("Carbine", 8, 3);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setSell(400);
            mRangedWeaponDao.insert(rangedWeapon);
            characterClass.addStartingRanged(rangedWeapon);
            meleeWeapon = new MeleeWeapon("Indian Hatchet");
            meleeWeapon.setDamageBonus(1);
            meleeWeapon.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            meleeWeapon.setCost(750);
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            meleeWeapon.addRestriction(TraitsEnum.TRAVELER.label());
            meleeWeapon.addRestriction(TraitsEnum.FRONTIER.label());
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setSell(250);
            mMeleeWeaponDao.insert(meleeWeapon);
            characterClass.addStartingMelee(meleeWeapon);
            skill = new Skill("Cavalry Scout", CharacterClassEnum.INDIAN_SCOUT.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Savage Attack", CharacterClassEnum.INDIAN_SCOUT.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            skill = new Skill("Heightened Senses", CharacterClassEnum.INDIAN_SCOUT.male());
            characterClass.addStartingSkill(skill);
            mSkillDao.insert(skill);
            mCharacterClassDao.insert(characterClass);

            //Initialize Personal Items
            gearBase = new GearBase("Ancient Coin");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier("Lore");
            Cowboy.addGear(gearBase);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Personal Journal");
            gearBase.addModifier("Spirit");
            gearBase.addModifier("Lore");
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Silver Dice");
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Weathered Hat");
            clothing.setPersonal(TRUE);
            clothing.setHat(TRUE);
            mClothingDao.insert(clothing);
            gearBase = new GearBase("Shackles");
            gearBase.addModifier(ModifiersEnum.STRENGTH.label());
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Locket");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.SPIRIT.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Concealed Flask");
            gearBase.addModifier(ModifiersEnum.SPIRIT.label());
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Worn Eye Patch");
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Adventure Boots");
            clothing.setBoots(TRUE);
            clothing.setPersonal(TRUE);
            clothing.addModifier(ModifiersEnum.AGILITY.label());
            clothing.addModifier(ModifiersEnum.MOVE.label());
            mClothingDao.insert(clothing);
            clothing = new Clothing("Roughskin Gloves");
            clothing.setPersonal(TRUE);
            clothing.setGloves(TRUE);
            clothing.addModifier(ModifiersEnum.STRENGTH.label());
            mClothingDao.insert(clothing);
            gearBase = new GearBase("Lucky Charm");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.LUCK.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Bear Claw");
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            rangedWeapon = new RangedWeapon("Boot Knife", 4, 1);
            rangedWeapon.setFree(TRUE);
            rangedWeapon.setPersonal(TRUE);
            rangedWeapon.setDamageBonus(2);
            mRangedWeaponDao.insert(rangedWeapon);
            gearBase = new GearBase("Ribbon");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.AGILITY.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Pocket Watch");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.INITIATIVE.label());
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Weathered Poncho");
            clothing.setPersonal(TRUE);
            clothing.setShoulders(TRUE);
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            mClothingDao.insert(clothing);
            gearBase = new GearBase("Cigarette");
            gearBase.setPersonal(TRUE);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Ace of Spades");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.LUCK.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Spectacles");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Hand Mirror");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.MAX_SANITY.label());
            gearBase.addModifier(ModifiersEnum.MAX_SANITY.label());
            gearBase.addModifier(ModifiersEnum.MAX_SANITY.label());
            gearBase.addModifier(ModifiersEnum.MAX_SANITY.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Heritage Charm");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.LUCK.label());
            gearBase.addModifier(ModifiersEnum.SPIRIT.label());
            gearBase.setSet(SetListEnum.JARGONO_NATIVE.code());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Tribal Tatoos");
            gearBase.setPersonal(TRUE);
            gearBase.addModifier(ModifiersEnum.STRENGTH.label());
            gearBase.setSet(SetListEnum.JARGONO_NATIVE.code());
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Serpent Skin Loin Cloth");
            clothing.setPersonal(TRUE);
            clothing.setSet(SetListEnum.JARGONO_NATIVE.code());
            clothing.addModifier(ModifiersEnum.AGILITY.label());
            mClothingDao.insert(clothing);
            gearBase = new GearBase("Relic Stone");
            gearBase.setPersonal(TRUE);
            gearBase.setSet(SetListEnum.JARGONO_NATIVE.code());
            gearBase.addModifier(ModifiersEnum.LORE.label());
            mGearBaseDao.insert(gearBase);



            //Initialize Gear by Set
            //BASE GEAR
            //TODO add toggles for loading database. One for if user has set, one for if set is already loaded
            gearBase = new GearBase("Accentuator Belt");
            gearBase.setWeight(-2);
            gearBase.setSell(350);
            gearBase.setArtifact(TRUE);
            gearBase.setUpgrades(2);
            gearBase.setTargaArtifact(TRUE);
            mGearBaseDao.insert(gearBase);
            Cowboy.addGear(gearBase);
            gearBase = new GearBase("Deflection Field");
            gearBase.setTargaArtifact(TRUE);
            gearBase.setSell(525);
            gearBase.setDarkStone(1);
            gearBase.setWeight(1);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Teleportation Bracelet");
            gearBase.setWeight(1);
            gearBase.setSell(725);
            gearBase.setTargaArtifact(TRUE);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Old Map");
            gearBase.setSell(25);
            mGearBaseDao.insert(gearBase);
            //Test
            sobCharacter.addGear(gearBase);
            //End
            gearBase = new GearBase("Void Crystals");
            gearBase.setSell(100);
            gearBase.setTargaArtifact(TRUE);
            mGearBaseDao.insert(gearBase);
            //Test
            sobCharacter.addGear(gearBase);
            //End
            gearBase = new GearBase("Cigar");
            gearBase.setSell(175);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Looking Glass");
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            gearBase.setSell(75);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Bandolier Strap");
            gearBase.setWeight(1);
            gearBase.setUpgrades(2);
            gearBase.setSell(375);
            gearBase.addRestriction(TraitsEnum.FRONTIER.label());
            gearBase.addRestriction(TraitsEnum.OUTLAW.label());
            gearBase.addRestriction(TraitsEnum.TRAVELER.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Rope");
            gearBase.setSell(125);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Pipe");
            gearBase.addModifier(ModifiersEnum.LORE.label());
            gearBase.setSell(75);
            //Artifact
            gearBase = new GearBase("Three-Eyed Skull");
            gearBase.setArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(275);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Healing Stone");
            gearBase.setArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(150);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Amulet of Heinghal");
            gearBase.setArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(225);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Amulet of Kotak");
            gearBase.setArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(250);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Tome of Vontarro");
            gearBase.setArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(300);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Soul Parasite");
            gearBase.setArtifact(TRUE);
            gearBase.addModifier(ModifiersEnum.INITIATIVE.label());
            gearBase.addModifier(ModifiersEnum.LORE.label());
            gearBase.addModifier(ModifiersEnum.LORE.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Book of the Occult");
            gearBase.setArtifact(TRUE);
            gearBase.addModifier(ModifiersEnum.LORE.label());
            gearBase.setWeight(1);
            gearBase.setUpgrades(1);
            gearBase.setSell(125);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Ring of Tar'Kul");
            gearBase.setArtifact(TRUE);
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            gearBase.setSell(200);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Book of the Mad King");
            gearBase.setArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(725);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Horrific Statue");
            gearBase.setArtifact(TRUE);
            gearBase.setDarkStone(1);
            gearBase.setSell(275);
            gearBase.addModifier(ModifiersEnum.LORE.label());
            mGearBaseDao.insert(gearBase);
            //Jargono
            gearBase = new GearBase("Tribal Necklace");
            gearBase.setJargonoArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(225);
            gearBase.addModifier(ModifiersEnum.LORE.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Gar'Dhuli Swamp Salve");
            gearBase.setWeight(1);
            gearBase.setSell(125);
            gearBase.setJargonoArtifact(TRUE);
            mGearBaseDao.insert(gearBase);
            //Targa
            gearBase = new GearBase("Frozen Isopod");
            gearBase.setTargaArtifact(TRUE);
            gearBase.setWeight(1);
            gearBase.setSell(300);
            gearBase.addModifier(ModifiersEnum.LORE.label());
            gearBase.addModifier(ModifiersEnum.MAX_GRIT.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Authority of O'Tar");
            gearBase.setTargaArtifact(TRUE);
            gearBase.setSell(425);
            mGearBaseDao.insert(gearBase);

            //BASE RANGED WEAPONS
            rangedWeapon = new RangedWeapon("Plasma Arc", 8, 1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(675);
            rangedWeapon.setTargaArtifact(TRUE);
            mRangedWeaponDao.insert(rangedWeapon);
            //Test
            sobCharacter.addRangedWeapon(rangedWeapon);
            //End
            rangedWeapon = new RangedWeapon("Repeating Rifle", 10, 2);
            rangedWeapon.setDamageBonus(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(550);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Repeating Hold-Out Pistol", 3, 2);
            rangedWeapon.addRestriction(TraitsEnum.PERFORMER.label());
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(2);
            rangedWeapon.setSell(350);
            rangedWeapon.setFree(TRUE);
            rangedWeapon.setCritChance(5);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Executioner Pistol", 6, 2);
            rangedWeapon.setDamageBonus(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(300);
            mRangedWeaponDao.insert(rangedWeapon);
            //Artifact
            rangedWeapon = new RangedWeapon("Void Pistol", 8, 2);
            rangedWeapon.setArtifact(TRUE);
            rangedWeapon.setDarkStone(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(675);
            rangedWeapon.setDamageDie(1);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Dead Man's Iron", 6, 3);
            rangedWeapon.setArtifact(TRUE);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(350);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Hell Pistol", 6, 2);
            rangedWeapon.setArtifact(TRUE);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(650);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("The Judge", 6, 3);
            rangedWeapon.setArtifact(TRUE);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(625);
            rangedWeapon.setToHitDie(8);
            mRangedWeaponDao.insert(rangedWeapon);
            //Targa
            rangedWeapon = new RangedWeapon("Trun Disintegrator", 8, 1);
            rangedWeapon.setTargaArtifact(TRUE);
            rangedWeapon.setWeight(2);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setSell(800);
            rangedWeapon.setDamageDie(18);
            mRangedWeaponDao.insert(rangedWeapon);


            //BASE MELEE WEAPONS
            meleeWeapon = new MeleeWeapon("Cavalry Sabre");
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setSell(600);
            meleeWeapon.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            meleeWeapon.setCost(1800);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Pick Axe");
            meleeWeapon.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            meleeWeapon.setCost(900);
            meleeWeapon.setCombat(1);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(300);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Knuckle Duster");
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(225);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Axe");
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setSell(200);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Hunting Knife");
            meleeWeapon.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            meleeWeapon.setCost(400);
            meleeWeapon.setCombat(1);
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(100);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Vicious Axe");
            meleeWeapon.addModifier(ModifiersEnum.INITIATIVE.label());
            meleeWeapon.setDamageBonus(1);
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(500);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Trusty Axe");
            meleeWeapon.setDamageBonus(1);
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(350);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Iron Cross");
            meleeWeapon.setCombat(1);
            meleeWeapon.addRestriction(TraitsEnum.HOLY.label());
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(325);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Straight Razor");
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(250);
            mMeleeWeaponDao.insert(meleeWeapon);
            //Artifact
            meleeWeapon = new MeleeWeapon("Hell Sword");
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(550);
            meleeWeapon.setArtifact(TRUE);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Void Sword");
            meleeWeapon.setArtifact(TRUE);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setWeight(1);
            meleeWeapon.setCombat(1);
            meleeWeapon.setSell(800);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Black Fang Hatchet");
            meleeWeapon.setArtifact(TRUE);
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setSell(425);
            meleeWeapon.setCombat(1);
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            meleeWeapon.addRestriction(TraitsEnum.TRAVELER.label());
            meleeWeapon.addRestriction(TraitsEnum.FRONTIER.label());
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Orb of Ro'kal");
            meleeWeapon.setArtifact(TRUE);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(425);
            mMeleeWeaponDao.insert(meleeWeapon);
            //Jargono
            meleeWeapon = new MeleeWeapon("Dark Stone Axe");
            meleeWeapon.setWeight(1);
            meleeWeapon.setDarkStone(1);
            meleeWeapon.setJargonoArtifact(TRUE);
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setSell(375);
            meleeWeapon.setDamageBonus(2);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Swamp Raptor Claw");
            meleeWeapon.setFree(TRUE);
            meleeWeapon.setJargonoArtifact(TRUE);
            meleeWeapon.setCritChance(4);
            meleeWeapon.setWeight(1);
            meleeWeapon.setSell(300);
            meleeWeapon.setCombat(1);
            mMeleeWeaponDao.insert(meleeWeapon);


            //BASE CLOTHING
            clothing = new Clothing("Silver Buckle");
            clothing.setBelt(TRUE);
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.setSell(325);
            clothing.addRestriction(TraitsEnum.LAW.label());
            clothing.addRestriction(TraitsEnum.SHOWMAN.label());
            mClothingDao.insert(clothing);
            clothing = new Clothing("Lucky Hat");
            clothing.addModifier(ModifiersEnum.LUCK.label());
            clothing.setHat(TRUE);
            clothing.setSell(150);
            mClothingDao.insert(clothing);
            clothing = new Clothing("Rawhide Chaps");
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.setSell(250);
            clothing.setPants(TRUE);
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MOVE.label());
            clothing.addRestriction(TraitsEnum.FRONTIER.label());
            mClothingDao.insert(clothing);
            clothing = new Clothing("Dusty Poncho");
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_SANITY.label());
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.setSell(350);
            clothing.setShoulders(TRUE);
            mClothingDao.insert(clothing);
            //Artifact
            clothing = new Clothing("Dark Stone Gloves");
            clothing.setArtifact(TRUE);
            clothing.setDarkStone(1);
            clothing.setWeight(1);
            clothing.setSell(575);
            clothing.setGloves(TRUE);
            clothing.addModifier(ModifiersEnum.STRENGTH.label());
            clothing.addModifier(ModifiersEnum.MELEE_DAMAGE.label());
            mClothingDao.insert(clothing);
            clothing = new Clothing("Gloves of Chardrin");
            clothing.setArtifact(TRUE);
            clothing.setWeight(1);
            clothing.setSell(175);
            clothing.addModifier(ModifiersEnum.AGILITY.label());
            clothing.setGloves(TRUE);
            mClothingDao.insert(clothing);
            //Jargono
            clothing = new Clothing("Serpent Skin Gloves");
            clothing.setJargonoArtifact(TRUE);
            clothing.setWeight(1);
            clothing.setSell(725);
            clothing.setGloves(TRUE);
            clothing.addModifier(ModifiersEnum.STRENGTH.label());
            mClothingDao.insert(clothing);
            clothing = new Clothing("Serpent Skull Helmet");
            clothing.setJargonoArtifact(TRUE);
            clothing.setArmor(5);
            clothing.setWeight(1);
            clothing.setSell(850);
            clothing.setFace(TRUE);
            clothing.setHat(TRUE);
            mClothingDao.insert(clothing);

            //BASE ATTACHMENTS
            attachment = new Attachment("Rune of Darkness", 1);
            attachment.setDarkStone(1);
            attachment.setSell(225);
            attachment.setRequiredDarkStoneToAttach(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Mark of the Damned", 1);
            attachment.setDarkStone(1);
            attachment.setSell(150);
            attachment.setRequiredDarkStoneToAttach(2);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rune of the Void", 1);
            attachment.setRequiredDarkStoneToAttach(2);
            attachment.setDarkStone(1);
            attachment.setSell(150);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rune of Shadows", 1);
            attachment.setDarkStone(1);
            attachment.setRequiredDarkStoneToAttach(1);
            attachment.setSell(200);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Mark of the Hunter", 1);
            attachment.setSell(100);
            attachment.setDarkStone(1);
            attachment.setRequiredDarkStoneToAttach(2);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Mark of the Dead", 1);
            attachment.setDarkStone(1);
            attachment.setRequiredDarkStoneToAttach(2);
            attachment.setSell(50);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rune of the Traveler", 1);
            attachment.setDarkStone(1);
            attachment.setRequiredDarkStoneToAttach(2);
            attachment.addModifier(ModifiersEnum.MOVE.label());
            attachment.setSell(175);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Eagle Feather", 1);
            attachment.setSell(250);
            attachment.addModifier(ModifiersEnum.AGILITY.label());
            attachment.addModifier(ModifiersEnum.MAX_HEALTH.label());
            attachment.addModifier(ModifiersEnum.MAX_HEALTH.label());
            attachment.addRestriction(TraitsEnum.TRIBAL.label());
            attachment.addRestriction(TraitsEnum.TRAVELER.label());
            attachment.addRestriction(TraitsEnum.FRONTIER.label());
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Mark of the Void", 1);
            attachment.setRequiredDarkStoneToAttach(2);
            attachment.setDarkStone(1);
            attachment.setSell(200);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rattlesnake Fangs", 1);
            attachment.setSell(225);
            attachment.addRestriction(TraitsEnum.LAW.label());
            attachment.addRestriction(TraitsEnum.OUTLAW.label());
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Crisp Rose", 1);
            attachment.addRestriction(TraitsEnum.PERFORMER.label());
            attachment.addRestriction(TraitsEnum.SHOWMAN.label());
            attachment.setSell(325);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Hangeing Cross", 1);
            attachment.setSell(200);
            attachment.addRestriction(TraitsEnum.HOLY.label());
            attachment.addRestriction(TraitsEnum.FRONTIER.label());
            attachment.addRestriction(TraitsEnum.OUTLAW.label());
            attachment.addModifier(ModifiersEnum.SPIRIT.label());
            attachment.addModifier(ModifiersEnum.MAX_SANITY.label());
            mAttacmentDao.insert(attachment);




            //DD
            clothing = new Clothing("Traveling Boots");
            gearBase.addModifier("Agility");
            gearBase.setWeight(1);
            gearBase.setUpgrades(1);
            gearBase.setSell(650);
            gearBase.setSet("DD");
            clothing.setBoots(TRUE);
            mClothingDao.insert(clothing);
            //Test
            clothing.setEquipped(TRUE);
            sobCharacter.addClothing(clothing);

            //Jargono Native


            //Base Shops
            //Blacksmith
            gearBase = new GearBase("Tomb Chest");
            gearBase.setWeight(1);
            gearBase.setSell(500);
            gearBase.setCost(600);
            gearBase.setShop(ShopEnum.BLACKSMITH.label());
            mGearBaseDao.insert(gearBase);
            //Test
            Cowboy.addGear(gearBase);
            //End
            clothing = new Clothing("DArk Stone Buckle");
            clothing.setShop(ShopEnum.BLACKSMITH.label());
            clothing.setCost(450);
            clothing.setDarkstoneCost(3);
            clothing.setBelt(TRUE);
            clothing.setDarkStone(1);
            mClothingDao.insert(clothing);
            clothing = new Clothing("Void Plate");
            clothing.setShop(ShopEnum.BLACKSMITH.label());
            clothing.setCost(3800);
            clothing.setDarkstoneCost(12);
            clothing.setWeight(2);
            clothing.setDarkStone(1);
            clothing.addPenalty(ModifiersEnum.AGILITY.label());
            clothing.setArmor(4);
            clothing.setTorso(TRUE);
            mClothingDao.insert(clothing);
            meleeWeapon = new MeleeWeapon("Void Hammer");
            meleeWeapon.setShop(ShopEnum.BLACKSMITH.label());
            meleeWeapon.setCost(4800);
            meleeWeapon.setDarkstoneCost(24);
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setDarkStone(2);
            meleeWeapon.setWeight(2);
            meleeWeapon.setDamageBonus(3);
            mClothingDao.insert(clothing);
            attachment = new Attachment("Dark Stone Inlay", 1);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.addRestriction(TraitsEnum.GUN.label());
            attachment.addRestriction(TraitsEnum.MELEE.label());
            attachment.setCost(700);
            attachment.setDarkstoneCost(3);
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Dark Stone Grip", 2);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.addRestriction(TraitsEnum.GUN.label());
            attachment.addRestriction(TraitsEnum.MELEE.label());
            attachment.setCost(1200);
            attachment.setDarkstoneCost(8);
            //TODO hardcode dark stone grip bonus
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Dark Stone Barrel", 1);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.addRestriction(TraitsEnum.GUN.label());
            attachment.setCost(225);
            attachment.setDarkstoneCost(6);
            attachment.setDarkStone(1);
            attachment.addModifier(ModifiersEnum.RANGE.label());
            attachment.addModifier(ModifiersEnum.RANGE.label());
            attachment.addModifier(ModifiersEnum.RANGE.label());
            attachment.addModifier(ModifiersEnum.RANGE.label());
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Void Assembly", 15);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.addRestriction(TraitsEnum.GUN.label());
            attachment.setCost(2200);
            attachment.setDarkstoneCost(15);
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Dark Stone Horse Shoes", 0);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.addRestriction(TraitsEnum.TRANSPORT.label());
            attachment.setCost(800);
            attachment.setDarkstoneCost(4);
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rune of Protections", 1);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.setCost(600);
            attachment.setDarkstoneCost(10);
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rune of Fortitude", 1);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.setCost(600);
            attachment.setDarkstoneCost(10);
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);
            attachment = new Attachment("Rune of Regeneration", 2);
            attachment.setShop(ShopEnum.BLACKSMITH.label());
            attachment.setCost(1800);
            attachment.setDarkstoneCost(20);
            attachment.setDarkStone(1);
            mAttacmentDao.insert(attachment);

            //Doc's Office
            gearBase = new GearBase("Specimen Jar");
            gearBase.setShop(ShopEnum.DOCS_OFFICE.label());
            gearBase.setCost(150);
            mGearBaseDao.insert(gearBase);
            meleeWeapon = new MeleeWeapon("Bone Saw");
            meleeWeapon.setShop(ShopEnum.DOCS_OFFICE.label());
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setWeight(1);
            meleeWeapon.setCost(1100);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Tools of Science");
            meleeWeapon.setShop(ShopEnum.DOCS_OFFICE.label());
            meleeWeapon.setCost(4800);
            meleeWeapon.setUpgrades(2);
            meleeWeapon.setWeight(1);
            //Todo hardcode bonus for Tools of Science : combat = cunning
            mMeleeWeaponDao.insert(meleeWeapon);
            clothing = new Clothing("Field Surgeon's Apron");
            clothing.setCost(925);
            clothing.setTorso(TRUE);
            clothing.setWeight(1);
            mClothingDao.insert(clothing);
            //General Store
            rangedWeapon = new RangedWeapon("Double-Barrel Shotgun", 4, 2);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setCost(2800);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setWeight(2);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setToHitDie(8);
            rangedWeapon.setUpgrades(1);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Light-Pistol", 6, 2);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setCost(250);
            rangedWeapon.setCritChance(0);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Quickdraw Pistol", 6, 2);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setCost(1050);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            mRangedWeaponDao.insert(rangedWeapon);
            rangedWeapon = new RangedWeapon("Gunfighter's Pistol", 7, 3);
            rangedWeapon.setShop(ShopEnum.GENERAL_STORE.label());
            rangedWeapon.setCost(2450);
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            mRangedWeaponDao.insert(rangedWeapon);
            //Church
            clothing = new Clothing("Holy Robes");
            clothing.setShop(ShopEnum.CHURCH.label());
            clothing.addRestriction(TraitsEnum.HOLY.label());
            clothing.setCoat(TRUE);
            clothing.setCost(600);
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            clothing.addModifier(ModifiersEnum.MAX_HEALTH.label());
            mClothingDao.insert(clothing);
            meleeWeapon = new MeleeWeapon("Book of Armageddon");
            meleeWeapon.setShop(ShopEnum.CHURCH.label());
            meleeWeapon.addRestriction(TraitsEnum.HOLY.label());
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setWeight(2);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setCombat(2);
            meleeWeapon.setCost(5600);
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Cross of Vengeance");
            meleeWeapon.setShop(ShopEnum.CHURCH.label());
            meleeWeapon.addRestriction(TraitsEnum.HOLY.label());
            meleeWeapon.setCost(2350);
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setDamageBonus(1);
            mMeleeWeaponDao.insert(meleeWeapon);
            gearBase = new GearBase("Icon of Resistance");
            gearBase.setWeight(1);
            gearBase.setShop(ShopEnum.CHURCH.label());
            gearBase.addRestriction(TraitsEnum.HOLY.label());
            gearBase.setCost(1400);
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Censer of Illumination");
            gearBase.setShop(ShopEnum.CHURCH.label());
            gearBase.addRestriction(TraitsEnum.HOLY.label());
            gearBase.setCost(3200);
            gearBase.setUpgrades(1);
            gearBase.setWeight(1);
            mGearBaseDao.insert(gearBase);
            //Frontier Outpost
            clothing = new Clothing("Military Gloves");
            clothing.setUpgrades(1);
            clothing.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            clothing.setCost(1650);
            clothing.setGloves(TRUE);
            clothing.setWeight(1);
            mClothingDao.insert(clothing);
            clothing = new Clothing("Desert Boots");
            clothing.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            //TODO hard code Desert Boots free attack
            clothing.setWeight(1);
            clothing.setBoots(TRUE);
            clothing.setCost(2100);
            mClothingDao.insert(clothing);
            meleeWeapon = new MeleeWeapon("Tribal Knife");
            meleeWeapon.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            meleeWeapon.setCost(1200);
            meleeWeapon.setWeight(1);
            meleeWeapon.setUpgrades(1);
            meleeWeapon.setCombat(1);
            meleeWeapon.addRestriction(TraitsEnum.FRONTIER.label());
            meleeWeapon.addRestriction(TraitsEnum.TRIBAL.label());
            meleeWeapon.addRestriction(TraitsEnum.TRAVELER.label());
            mMeleeWeaponDao.insert(meleeWeapon);
            meleeWeapon = new MeleeWeapon("Splitter");
            meleeWeapon.setShop(ShopEnum.FRONTIER_OUTPOST.label());
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setCombat(2);
            meleeWeapon.setDamageDie(8);
            meleeWeapon.setUpgrades(3);
            meleeWeapon.setCost(5650);
            meleeWeapon.setWeight(2);
            mMeleeWeaponDao.insert(meleeWeapon);
            //Saloon
            gearBase = new GearBase("Gambler's Deck of Cards");
            gearBase.setShop(ShopEnum.SALOON.label());
            gearBase.setCost(850);
            gearBase.setWeight(1);
            gearBase.addModifier(ModifiersEnum.CUNNING.label());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Lucky Dice");
            gearBase.setShop(ShopEnum.SALOON.label());
            gearBase.setWeight(1);
            gearBase.setUpgrades(1);
            gearBase.addModifier(ModifiersEnum.LUCK.label());
            gearBase.setCost(1000);
            mGearBaseDao.insert(gearBase);
            clothing = new Clothing("Boned Corset");
            clothing.setShop(ShopEnum.SALOON.label());
            clothing.setCost(1200);
            clothing.setTorso(TRUE);
            clothing.setArmor(6);
            clothing.setWeight(1);
            clothing.setUpgrades(1);
            clothing.addRestriction(CharacterClassEnum.SALOON_GIRL.female());
            mClothingDao.insert(clothing);
            clothing = new Clothing("Dark Stone Corset");
            clothing.setShop(ShopEnum.SALOON.label());
            clothing.setCost(3200);
            clothing.setDarkstoneCost(8);
            clothing.addRestriction(CharacterClassEnum.SALOON_GIRL.female());
            clothing.setDarkStone(1);
            clothing.setArmor(5);
            clothing.setWeight(1);
            clothing.addModifier(ModifiersEnum.MAX_GRIT.label());
            clothing.setTorso(TRUE);
            mClothingDao.insert(clothing);
            clothing = new Clothing("Tiny Hat");
            clothing.setShop(ShopEnum.SALOON.label());
            clothing.addRestriction(CharacterClassEnum.SALOON_GIRL.female());
            clothing.setCost(2450);
            clothing.setUpgrades(1);
            mClothingDao.insert(clothing);
            //Caverns of Cynder


            //Crimson Hand
            rangedWeapon = new RangedWeapon("Cult Rifle", 8, 2);
            rangedWeapon.setDamageBonus(1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setSet(SetListEnum.CRIMSON_HAND.code());
            rangedWeapon.setSell(400);
            rangedWeapon.setUpgrades(2);
            mRangedWeaponDao.insert(rangedWeapon);
            Cowboy.addRangedWeapon(rangedWeapon);
            //Lost Army
            gearBase = new GearBase("Flag of the Fallen");
            gearBase.setWeight(2);
            gearBase.setArtifact(TRUE);
            gearBase.setSell(1250);
            gearBase.setSet(SetListEnum.LOST_ARMY.code());
            mGearBaseDao.insert(gearBase);
            Cowboy.addGear(gearBase);
            gearBase = new GearBase("Defender's Hat");
            gearBase.setWeight(1);
            gearBase.setUpgrades(2);
            gearBase.setSell(400);
            gearBase.setSet(SetListEnum.LOST_ARMY.code());
            mGearBaseDao.insert(gearBase);
            rangedWeapon = new RangedWeapon("Lost Army Pistol", 7, 1);
            rangedWeapon.setWeight(1);
            rangedWeapon.setSell(700);
            rangedWeapon.setSet(SetListEnum.LOST_ARMY.code());
            rangedWeapon.setArtifact(TRUE);
            mRangedWeaponDao.insert(rangedWeapon);
            //Test
            sobCharacter.addRangedWeapon(rangedWeapon);
            //End


            //Promo
            rangedWeapon = new RangedWeapon("The Grave Digger", 3, 2);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setToHitDie(8);
            rangedWeapon.setWeight(1);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.setSet(SetListEnum.PROMO.code());
            rangedWeapon.setSell(400);
            mRangedWeaponDao.insert(rangedWeapon);
            Cowboy.addRangedWeapon(rangedWeapon);
            clothing = new Clothing("Badlands Adventure Gear");
            clothing.setWeight(2);
            clothing.setCoat(TRUE);
            clothing.setSell(1250);
            clothing.setSet(SetListEnum.PROMO.code());
            mClothingDao.insert(clothing);
            Cowboy.addClothing(clothing);
            rangedWeapon = new RangedWeapon("Sawed-Off Shotgun", 3, 1);
            rangedWeapon.setDamageDie(8);
            rangedWeapon.setTwoHanded(TRUE);
            rangedWeapon.addRestriction("Outlaw");
            rangedWeapon.setWeight(1);
            rangedWeapon.setUpgrades(1);
            rangedWeapon.setSell(425);
            mRangedWeaponDao.insert(rangedWeapon);
            Cowboy.addRangedWeapon(rangedWeapon);
            gearBase = new GearBase("Red Sash");
            gearBase.setWeight(1);
            gearBase.addModifier("Move");
            gearBase.addModifier("Agility");
            gearBase.addRestriction("Performer");
            gearBase.addRestriction("Showman");
            gearBase.setSell(750);
            gearBase.setSet(SetListEnum.PROMO.code());
            mGearBaseDao.insert(gearBase);
            gearBase = new GearBase("Telescope");
            gearBase.addModifier("Cunning");
            gearBase.setWeight(1);
            gearBase.setSell(225);
            gearBase.setSet(SetListEnum.PROMO.code());
            mGearBaseDao.insert(gearBase);
            //Test
            sobCharacter.addGear(gearBase);
            //End
            meleeWeapon = new MeleeWeapon("Trun Gladius");
            meleeWeapon.setWeight(2);
            meleeWeapon.setTwoHanded(TRUE);
            meleeWeapon.setDamageBonus(2);
            meleeWeapon.setSet(SetListEnum.PROMO.code());
            meleeWeapon.setTargaArtifact(TRUE);
            meleeWeapon.setSell(600);
            mMeleeWeaponDao.insert(meleeWeapon);
            //Test
            sobCharacter.addMeleeWeapon(meleeWeapon);
            //End
            gearBase = new GearBase("Outlaw's Gun Belt");
            gearBase.addModifier(ModifiersEnum.MAX_HEALTH.label());
            gearBase.addModifier(ModifiersEnum.MAX_HEALTH.label());
            gearBase.addRestriction(TraitsEnum.OUTLAW.label());
            gearBase.addRestriction(TraitsEnum.SHOWMAN.label());
            gearBase.addRestriction(TraitsEnum.FRONTIER.label());
            gearBase.setSell(625);
            gearBase.setSet(SetListEnum.PROMO.code());

            //OUTLAW SHOOTIN' UPGRADES
            skill = new Skill("Guns Blazing", SkillTypeEnum.SHOOTIN.label());
            skill.setLevel(1);
            mSkillDao.insert(skill);
            skill.setName("Gunfighter(Outlaw)");
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



            for (GearBase gearBase1 : Cowboy.getCharacterClass().getStartingGear()) {
                Cowboy.addGear(gearBase1);
            }
            for (Clothing clothing1 : Cowboy.getCharacterClass().getStartingClothing()) {
                Cowboy.addClothing(clothing1);
            }
            for (RangedWeapon rangedWeapon1 : Cowboy.getCharacterClass().getStartingRanged()) {
                Cowboy.addRangedWeapon(rangedWeapon1);
            }
            for (MeleeWeapon meleeWeapon1 : Cowboy.getCharacterClass().getStartingMelee()) {
                Cowboy.addMeleeWeapon(meleeWeapon1);
            }
            mCharacterDao.insert(sobCharacter);
            mCharacterDao.insert(Cowboy);
            return null;
        }
    }
}

