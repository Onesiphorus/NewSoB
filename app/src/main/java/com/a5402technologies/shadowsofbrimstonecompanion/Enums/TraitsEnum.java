package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum TraitsEnum {
    LAW("Law", "LAW"),
    HOLY("Holy", "HOLY"),
    FRONTIER("Frontier", "FRONTIER"),
    STRANGE("Strange", "STRANGE"),
    TRAVELER("Traveler", "TRAVELER"),
    OUTLAW("Outlaw", "OUTLAW"),
    SHOWMAN("Showman", "SHOWMAN"),
    TRIBAL("Tribal", "TRIBAL"),
    JARGONO("Jargono", "JARGONO"),
    OTHERWORLD("OtherWorld", "OTHERWORLD"),
    PERFORMER("Performer", "PERFORMER"),
    MAGIK("Magik", "MAGIK"),
    MEDICAL("Medical", "MEDICAL"),
    SAMARAI("Samaria", "SAMARAI"),
    SCOUT("Scout", "SCOUT"),
    GUN("Gun", "GUN"),
    MELEE("Melee", "MELEE"),
    TRANSPORT("Transport", "TRANSPORT"),
    MUSIC("Music", "MUSIC"),
    MILITARY("Military", "MILITARY"),
    SURVIVAL("Survival", "SURVIVAl"),
    FACE("Face", "FACE"),
    HAT("Hat", "HAT"),
    SHOULDERS("Shoulders", "SHOULDERS"),
    COAT("Coat", "COAT"),
    TORSO("Torso", "TORSO"),
    GLOVES("Gloves", "GLOVES"),
    BELT("Belt", "BELT"),
    PANTS("Pants", "PANTS"),
    BOOTS("Boots", "BOOTS"),
    CLOTHING("Clothing", "CLOTHING"),
    CULT("Cult", "CULT"),
    HAND_WEAPON("Hand Weapon", "HAND WEAPON"),
    BLADE("Blade", "BLADE"),
    POISON("Poison", "POISON"),
    RIFLE("Rifle", "RIFLE"),
    CHARM("Charm", "CHARM"),
    HERBS("Herbs", "HERBS"),
    SHOTGUN("Shotgun", "SHOTGUN"),
    PISTOL("Pistol", "PISTOL"),
    ROPE("Rope", "ROPE"),
    GLASS("Glass", "GLASS"),
    FIRE("Fire", "FIRE"),
    LIGHT("Light", "LIGHT"),
    DARK_STONE("Dark Stone", "DARK STONE"),
    STATUE("Statue", "STATUE"),
    BOOK("Book", "BOOK"),
    OCCULT("Occult", "OCCULT"),
    ARTIFACT("Artifact", "ARTIFACT"),
    RING("Ring", "RING"),
    VOID("Void", "VOID"),
    CREATURE("Creature", "CREATURE"),
    AMULET("Amulet", "AMULET"),
    SKULL("Skull", "SKULL"),
    DEMONIC("Demonic", "DEMONIC"),
    CURSE("Curse", "CURSE"),
    CONTAINER("Container", "CONTAINER"),
    LIGHT_SOURCE("Light Source", "LIGHT SOURCE"),
    EXPLOSIVE("Explosive", "EXPLOSIVE"),
    DICE("Dice", "DICE"),
    GOLD("Gold", "GOLD"),
    ICON("Icon", "ICON"),
    DARKNESS("Darkness", "DARKNESS"),
    LOST_ARMY("Lost Army", "LOST ARMY"),
    RELIC("Relic", "RELIC"),
    ANCIENT("Ancient", "ANCIENT"),
    BLOOD("Blood", "BLOOD"),
    MOON("Moon", "MOON"),
    DANCE("Dance", "DANCE"),
    VAMPIRE("Vampire", "VAMPIRE"),
    TREDERRAN("Trederran", "TREDERRAN"),
    UNDEAD("Undead", "UNDEAD"),
    TARGA("Targa", "TARGA"),
    TECH("Tech", "TECH"),
    FLAG("Flag", "FLAG"),
    MAP("Map", "MAP"),
    PERSONAL("Personal", "PERSONAL"),
    TATOO("Tatoo", "TATOO"),
    TIME("Time", "TIME"),
    FLASK("Flask", "FLASK"),
    ALCOHOL("Alcohol", "ALCOHOL"),
    SHIELD("Shield", "SHIELD"),
    SCIENCE("Science", "SCIENCE"),
    BOW("Bow", "BOW"),
    NECK("Neck", "NECK"),
    HELL("Hell", "HELL"),
    CYNDER("Cynder", "CYNDER"),
    PLANT("Plant", "PLANT"),
    SHIP("Ship", "SHIP"),
    CONTROL("Control", "CONTROL"),
    ROBOT("Robot", "ROBOT");


    private static Map<String, TraitsEnum> lookupByCode = new HashMap<>();
    private static Map<String, TraitsEnum> lookupByLabel = new HashMap<>();

    static {
        for (TraitsEnum e : TraitsEnum.values()) {
            lookupByCode.put(e.code, e);
            lookupByLabel.put(e.label, e);
        }
    }

    private String code;
    private String label;

    TraitsEnum(String label, String code) {
        this.label = label;
        this.code = code;
    }

    public String label() {
        return this.label;
    }

    public String code() {
        return this.code;
    }

    public TraitsEnum getByCode(String code) {
        return lookupByCode.get(code);
    }

    public TraitsEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }

    public String getLabelByCode(String code) {
        return getByCode(code).label;
    }

    public String getCodeByLabel(String label) {
        return getByLabel(label).code;
    }

}
