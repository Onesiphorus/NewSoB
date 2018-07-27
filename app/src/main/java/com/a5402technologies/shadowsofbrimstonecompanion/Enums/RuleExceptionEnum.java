package com.a5402technologies.shadowsofbrimstonecompanion.Enums;

import java.util.HashMap;
import java.util.Map;

public enum RuleExceptionEnum {

    DARK_STONE_GRIP("Dark Stone Grip"),
    DARK_STONE_BARREL("Dark Stone Barrel"),
    TRUSTY_PISTOL("Trusty Pistol"),
    TOOLS_OF_SCIENCE("Tools of Science"),
    DUELISTS_GUNBELT("Duelist's Gunbelt"),
    BOUNTY_HUNTERS_BADGE("Bounty Hunter's Badge"),
    SPIRIT_BOW("Spirit Bow"),
    HARTHBONE_NECKLACE("Harthbone Necklace"),
    MARK_OF_THE_FAITHFUL("Mark of the Faithful"),
    DARK_STONE_CLUB("Dark Stone Club"),
    PERSONAL_TOUCH("Personal Touch"),
    TOMB_CHEST("Tomb Chest"),
    SPINNING_SLASH("Spinning Slash"),
    SHIELD_BASH("Shield Bash"),
    QUICK_SHOT("Quick Shot"),
    RIDERS_HAT("Rider's Hat"),
    SCOUTS_HAT("Scout's Hat"),
    EXTRA_HAND_AUGMENT("Extra Hand Augment"),
    PREHENSILE_TAIL("Prehensile Tail"),
    HORNS("Horns"),
    ARM_GROWTH("Arm Growth"),
    LEG_GROWTH("Leg Growth"),
    HAND_GROWTH("Hand Growth"),
    FUSED_FINGERS("Fused Fingers"),
    STORY_TO_TELL("A Story to Tell"),
    NO_NONSENSE("No Nonsense"),
    THAT_DOES_IT("That Does It!"),
    SCALPED("Scalped");
    private static Map<String, RuleExceptionEnum> lookupByLabel = new HashMap<>();

    static {
        for (RuleExceptionEnum e : RuleExceptionEnum.values()) {
            lookupByLabel.put(e.label, e);
        }
    }

    private String label;

    RuleExceptionEnum(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }

    public RuleExceptionEnum getByLabel(String label) {
        return lookupByLabel.get(label);
    }
}
