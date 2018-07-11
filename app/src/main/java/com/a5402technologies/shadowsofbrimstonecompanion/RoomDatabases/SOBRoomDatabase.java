package com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.AttachmentDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterClassDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.ClothingDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.GearBaseDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.MeleeWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.PermanentConditionDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.RangedWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.SkillDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.CharacterClassEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ConditionEnum;
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
import com.a5402technologies.shadowsofbrimstonecompanion.Models.PermanentCondition;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

import java.util.ArrayList;

import static java.lang.Boolean.TRUE;

@Database(entities = {SobCharacter.class, CharacterClass.class, GearBase.class, MeleeWeapon.class,
        RangedWeapon.class, Clothing.class, Skill.class, Attachment.class, PermanentCondition.class}, version = 37)
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

    public abstract PermanentConditionDao permanentConditionDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final CharacterDao mCharacterDao;
        private final CharacterClassDao mCharacterClassDao;
        private final GearBaseDao mGearBaseDao;
        private final MeleeWeaponDao mMeleeWeaponDao;
        private final RangedWeaponDao mRangedWeaponDao;
        private final ClothingDao mClothingDao;
        private final SkillDao mSkillDao;
        private final AttachmentDao mAttacmentDao;
        private final PermanentConditionDao mPermanentConditionDao;

        PopulateDbAsync(SOBRoomDatabase db) {
            mCharacterDao = db.characterDao();
            mCharacterClassDao = db.characterClassDao();
            mGearBaseDao = db.gearBaseDao();
            mMeleeWeaponDao = db.meleeWeaponDao();
            mRangedWeaponDao = db.rangedWeaponDao();
            mClothingDao = db.clothingDao();
            mSkillDao = db.skillDao();
            mAttacmentDao = db.attachmentDao();
            mPermanentConditionDao = db.permanentConditionDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mCharacterClassDao.deleteAllCharacterClasses();
            mGearBaseDao.deleteAllGearBase();
            mMeleeWeaponDao.deleteAllMeleeWeapons();
            mRangedWeaponDao.deleteAllRangedWeapons();
            mClothingDao.deleteAllClothing();
            mSkillDao.deleteAllSkill();
            mAttacmentDao.deleteAllAttachment();
            mPermanentConditionDao.deleteAllPermanentCondition();
            mCharacterDao.deleteCharacterByName("Kristal");
            mCharacterDao.deleteCharacterByName("Paul");
            PermanentCondition permanentCondition;
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



            permanentCondition = new PermanentCondition("Eviscerated", ConditionEnum.INJURY.label());
            //TODO something with death
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Foreign Object", ConditionEnum.INJURY.label());
            //TODO code Foreign Object
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Spinal Cord Injury", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.AGILITY.label());
            permanentCondition.addPenalty(ModifiersEnum.STRENGTH.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Brain Injury", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.CUNNING.label());
            permanentCondition.addPenalty(ModifiersEnum.LORE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Butchered Genitals", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.SPIRIT.label());
            permanentCondition.addPenalty(ModifiersEnum.LUCK.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fractured Hip", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Mangled Hand", ConditionEnum.INJURY.label());
            //TODO code Mangled Hand to reduce weapons carried
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Gouged Eye", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fractured Ribs", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_WEIGHT.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Broken Leg", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.MOVE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Abdominal Trauma", ConditionEnum.INJURY.label());
            //TODO Defense Roll -1 (Defense +1?)
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Concussion", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.INITIATIVE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Internal Bleeding", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Broken Arm", ConditionEnum.INJURY.label());
            //TODO code no melee crits with Broken Arm
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Cracked Knee", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Crushed Foot", ConditionEnum.INJURY.label());
            //TODO escape rolls?
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Scalped", ConditionEnum.INJURY.label());
            //TODO disable hat when Scalped
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Slashed Face", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Broken Teeth", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Broken Collar Bone", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_GRIT.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Chest Wound", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.INITIATIVE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Severed Finger", ConditionEnum.INJURY.label());
            //TODO code -1 shot on ranged weapons when Severed Finger
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Severed Ear", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Swollen Eye", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Pulled Muscle", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Twisted Ankle", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Sprained Wrist", ConditionEnum.INJURY.label());
            //TODO add Ranged to Hit to Hit modifiers (global)
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Dislocated Shoulder", ConditionEnum.INJURY.label());
            //TODO add Melee to Hit modifiers (global)
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Rattled", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Photophobia", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Breathing Difficulties", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Puncture Wound", ConditionEnum.INJURY.label());
            permanentCondition.addPenalty(ModifiersEnum.COMBAT.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Busted Jaw", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Wind Knocked Out", ConditionEnum.INJURY.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Scarring", ConditionEnum.INJURY.label());
            permanentCondition.addModifier(ModifiersEnum.MAX_GRIT.label());
            mPermanentConditionDao.insert(permanentCondition);
        //Mutations
            permanentCondition = new PermanentCondition("Chest Portal", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tentacle Fingers", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tentacle Arm", ConditionEnum.MUTATION.label());
            permanentCondition.addModifier(ModifiersEnum.COMBAT.label());
            //TODO code weapons for tentacle arm
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tentacle Leg", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.MOVE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tentacle Tongue", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tentacle Mustache", ConditionEnum.MUTATION.label());
            //TODO code $10 discount with tentalce mustache
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Glowing Skin", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Rock Skin", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.MOVE.label());
            permanentCondition.addModifier(ModifiersEnum.MAX_HEALTH.label());
            permanentCondition.addModifier(ModifiersEnum.MAX_HEALTH.label());
            permanentCondition.addModifier(ModifiersEnum.MAX_HEALTH.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Slippery Skin", ConditionEnum.MUTATION.label());
            //TODO Escape tests
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Melty Skin", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Void Boils", ConditionEnum.MUTATION.label());
            permanentCondition.addModifier(ModifiersEnum.MAX_GRIT.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_HEALTH.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_HEALTH.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Void Infection", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Barbed Tail", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_CORRUPTION.label());
            permanentCondition.addModifier(ModifiersEnum.COMBAT.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Prehensile Tail", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_CORRUPTION.label());
            //TODO code prehensile tail
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tail with a Face", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tail with a Mouth", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Tentacle Tail", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_CORRUPTION.label());
            permanentCondition.addModifier(ModifiersEnum.MOVE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Void Plague", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Horns", ConditionEnum.MUTATION.label());
            //TODO code no hats with Horns
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Eye Grown Over", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.CRITICAL_DAMAGE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Third Eye", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Mouth Grown Over", ConditionEnum.MUTATION.label());
            //TODO Code +$10 in town with Mouth Grown Over
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fangs", ConditionEnum.MUTATION.label());
            //TODO code Fangs free attack
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Second Head", ConditionEnum.MUTATION.label());
            permanentCondition.addModifier(ModifiersEnum.INITIATIVE.label());
            //TODO code additional Hat slot with Second Head
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Arm Growth", ConditionEnum.MUTATION.label());
            //TODO code no coat with Arm Growth
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Leg Growth", ConditionEnum.MUTATION.label());
            //TODO code no boots with leg growth
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Hand Growth", ConditionEnum.MUTATION.label());
            //TODO code no gloves with hand growth
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fused with Item", ConditionEnum.MUTATION.label());
            //todo code fused item on weapon
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fused with Rock", ConditionEnum.MUTATION.label());
            permanentCondition.setArmor(4);
            permanentCondition.addPenalty(ModifiersEnum.MOVE.label());
            permanentCondition.addPenalty(ModifiersEnum.MOVE.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fused with Dark Stone", ConditionEnum.MUTATION.label());
            //TODO Fuse Dark Stone
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Dark Stone Allergy", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Nose Fallen Off", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Fused Fingers", ConditionEnum.MUTATION.label());
            //TODO code no non-artifact guns with Fused Fingers
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Eye Stalks", ConditionEnum.MUTATION.label());
            permanentCondition.addModifier(ModifiersEnum.CRITICAL_DAMAGE.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_CORRUPTION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Void Speech", ConditionEnum.MUTATION.label());
            mPermanentConditionDao.insert(permanentCondition);
            permanentCondition = new PermanentCondition("Child of the Void", ConditionEnum.MUTATION.label());
            permanentCondition.addPenalty(ModifiersEnum.MAX_CORRUPTION.label());
            permanentCondition.addModifier(ModifiersEnum.LORE.label());
            mPermanentConditionDao.insert(permanentCondition);




            return null;
        }
    }
}

