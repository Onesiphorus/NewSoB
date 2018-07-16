package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Conditions.ConditionTypeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.ChangeLoadoutActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.SpoilsActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu.CharacterActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.CharacterClassEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.RuleExceptionEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.TraitsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.CharacterViewModel;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static java.lang.Boolean.TRUE;


public class ShadowsOfBrimstoneActivity extends AppCompatActivity {

    private SobCharacter sobCharacter;
    private CharacterViewModel mCharacterViewModel;
    private TextView tv;
    private String text;
    private Integer val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCharacterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        setContentView(R.layout.activity_shadows_of_brimstone);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        mCharacterViewModel.update(sobCharacter);
        sobCharacter.setBonuses();
        tv = findViewById(R.id.right_hand_weapon);
        if (sobCharacter.getRightHand() != null) {
            tv.setText(sobCharacter.getRightHand().getName());
        } else if (sobCharacter.getRightMelee() != null) {
            tv.setText(sobCharacter.getRightMelee().getName());
        } else tv.setText("Empty");
        tv = findViewById(R.id.left_hand_weapon);
        if (sobCharacter.getLeftHand() != null) {
            tv.setText(sobCharacter.getLeftHand().getName());
        } else if (sobCharacter.getLeftMelee() != null) {
            tv.setText(sobCharacter.getLeftMelee().getName());
        } else tv.setText("Empty");

        tv = findViewById(R.id.traits_wrapper);
        String text = " - ";
        for (String string : sobCharacter.getTraits()) {
            text += string + " - ";
        }
        tv.setText(text);

        setStats();
        setQuickClothes();
        setWeapons();
        setHealthSanityStats();
        findViewById(R.id.layout_quick_combat).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, CombatViewActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.quick_clothes_layout).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChangeLoadoutActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });


        findViewById(R.id.btn_sob_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.quick_resources_layout).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, SpoilsActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.layout_quick_combat).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, CombatViewActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_levelup).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, LevelUpActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_sob_conditions).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ConditionTypeActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    protected void setQuickClothes() {
        tv = findViewById(R.id.quick_hat);
        if (sobCharacter.getHat().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_face);
        if (sobCharacter.getFace().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_torso);
        if (sobCharacter.getTorso().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_gloves);
        if (sobCharacter.getGloves().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_belt);
        if (sobCharacter.getBelt().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_pants);
        if (sobCharacter.getPants().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_shoulders);
        if (sobCharacter.getShoulders().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_boots);
        if (sobCharacter.getBoots().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_coat);
        if (sobCharacter.getCoat().equals(TRUE)) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_weight);
        String text = "Weight: " + sobCharacter.getWeight().toString() + "/" + sobCharacter.getMaxWeight();
        tv.setHint(text);
        if (sobCharacter.getMaxWeight() >= sobCharacter.getWeight()) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv = findViewById(R.id.quick_side_bag);
        text = "Side Bag: " + sobCharacter.getSideBag().size() + "/" + sobCharacter.getSideBagSize();
        if (sobCharacter.getSideBagSize() >= sobCharacter.getSideBag().size())
            tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv.setHint(text);
        tv = findViewById(R.id.quick_corruption);
        text = "Corruption: " + sobCharacter.getCurrentCorruption() + "/" + sobCharacter.getMaxCorruption();
        if (sobCharacter.getMaxCorruption() >= sobCharacter.getCurrentCorruption())
            tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
        tv.setHint(text);
    }
    private void setWeapons() {
        if (sobCharacter.getRightHand() != null) {
            tv = findViewById(R.id.qc_r1_range);
            Integer range = sobCharacter.getRightHand().getRange();
            Integer shots = sobCharacter.getRightHand().getName().equals(RuleExceptionEnum.TRUSTY_PISTOL.label())
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getRightHand().getName().equals(RuleExceptionEnum.SPIRIT_BOW.label())
                    ? sobCharacter.getCharacterClass().getSpirit() + sobCharacter.getSpiritBonus()
                    : sobCharacter.getRightHand().getShots();
            for (String s : sobCharacter.getRightHand().getTraits()) {
                if (s.equals(TraitsEnum.PISTOL.label())) {
                    for (Clothing clothing : sobCharacter.getClothing()) {
                        if (clothing.getName().equals(RuleExceptionEnum.DUELISTS_GUNBELT.label())
                                && clothing.getEquipped().equals(TRUE)) {
                            if (null != sobCharacter.getLeftHand()) {
                                for (String string : sobCharacter.getLeftHand().getTraits()) {
                                    if (string.equals(TraitsEnum.PISTOL.label())) {
                                        shots++;
                                    }
                                }
                            }
                        }
                    }
                } else if (s.equals(TraitsEnum.BOW.label())) {
                    if (sobCharacter.getCharacterClass().getClassName().equals(CharacterClassEnum.JARGONO_NATIVE.male())) {
                        for (Skill skill : sobCharacter.getUpgrades()) {
                            if (skill.getName().equals(RuleExceptionEnum.QUICK_SHOT.label())) {
                                shots++;
                            }
                        }
                    }
                }
            }
            for (Attachment attachment : sobCharacter.getRightHand().getAttachments()) {
                if (attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                    shots++;
                } else if (attachment.getName().equals(RuleExceptionEnum.DARK_STONE_BARREL.label())) {
                    range += 4;
                }
            }
            tv.setText(String.format(range.toString()));
            tv = findViewById(R.id.qc_r1_shots);

            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.qc_r1_damgage);
            text = "d" + sobCharacter.getRightHand().getDamageDie().toString();
            if (sobCharacter.getRightHand().getDamageBonus() > 0) {
                text += "+" + sobCharacter.getRightHand().getDamageBonus().toString();
            }
            tv.setText(text);
            tv = findViewById(R.id.qc_r1_tohit);
            text = "d"
                    + sobCharacter.getRightHand().getToHitDie().toString()
                    + " : "
                    + sobCharacter.getCharacterClass().getRangedToHit().toString()
                    + "+ ("
                    + sobCharacter.getRightHand().getCritChance()
                    + "+)";
            tv.setText(text);
        }

        if (sobCharacter.getLeftHand() != null) {
            tv = findViewById(R.id.qc_r2_range);
            tv.setText(String.format(sobCharacter.getLeftHand().getRange().toString()));
            tv = findViewById(R.id.qc_r2_shots);
            Integer shots = (sobCharacter.getLeftHand().getName().equals("Trusty Pistol"))
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getLeftHand().getName().equals(RuleExceptionEnum.SPIRIT_BOW.label())
                    ? sobCharacter.getCharacterClass().getSpirit() + sobCharacter.getSpiritBonus()
                    : sobCharacter.getLeftHand().getShots();
            for (String s : sobCharacter.getLeftHand().getTraits()) {
                if (s.equals(TraitsEnum.PISTOL.label())) {
                    for (Clothing clothing : sobCharacter.getClothing()) {
                        if (clothing.getName().equals(RuleExceptionEnum.DUELISTS_GUNBELT.label())) {
                            if (null != sobCharacter.getRightHand()) {
                                for (String string : sobCharacter.getRightHand().getTraits()) {
                                    if (string.equals(TraitsEnum.PISTOL.label())) {
                                        shots++;
                                    }
                                }
                            }
                        }
                    }
                } else if (s.equals(TraitsEnum.BOW.label())) {
                    if (sobCharacter.getCharacterClass().getClassName().equals(CharacterClassEnum.JARGONO_NATIVE.male())) {
                        for (Skill skill : sobCharacter.getUpgrades()) {
                            if (skill.getName().equals(RuleExceptionEnum.QUICK_SHOT.label())) {
                                shots++;
                            }
                        }
                    }
                }
            }
            for (Attachment attachment : sobCharacter.getLeftHand().getAttachments()) {
                if (attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                    shots++;
                }
            }
            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.qc_r2_damgage);
            String text = "d" + sobCharacter.getLeftHand().getDamageDie().toString();
            if (sobCharacter.getLeftHand().getDamageBonus() > 0) {
                text += "+" + sobCharacter.getLeftHand().getDamageBonus().toString();
            }
            tv.setText(text);
            tv = findViewById(R.id.qc_r2_tohit);
            text = "d"
                    + sobCharacter.getLeftHand().getToHitDie().toString()
                    + " : "
                    + sobCharacter.getCharacterClass().getRangedToHit().toString()
                    + "+ ("
                    + sobCharacter.getLeftHand().getCritChance()
                    + "+)";
            tv.setText(text);
        }

        tv = findViewById(R.id.qc_combat);
        Integer combat = calculateMeleeCombat();
        tv.setText(String.format(combat.toString()));

        tv = findViewById(R.id.qc_tohit);
        text = calculateMeleeHitDie();
        tv.setText(text);

        tv = findViewById(R.id.qc_damage);
        text = calculateMeleeDamageDie();
        tv.setText(text);

        if (sobCharacter.getTailRanged() != null) {
            tv = findViewById(R.id.qc_tail_range);
            tv.setText(String.format(sobCharacter.getTailRanged().getRange().toString()));
            tv = findViewById(R.id.qc_tail_shots);
            Integer shots = (sobCharacter.getTailRanged().getName().equals("Trusty Pistol"))
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getTailRanged().getName().equals(RuleExceptionEnum.SPIRIT_BOW.label())
                    ? sobCharacter.getCharacterClass().getSpirit() + sobCharacter.getSpiritBonus()
                    : sobCharacter.getTailRanged().getShots();
            for (String s : sobCharacter.getTailRanged().getTraits()) {
                if (s.equals(TraitsEnum.PISTOL.label())) {
                    for (Clothing clothing : sobCharacter.getClothing()) {
                        if (clothing.getName().equals(RuleExceptionEnum.DUELISTS_GUNBELT.label())) {
                            if (null != sobCharacter.getRightHand() || null != sobCharacter.getLeftHand()) {
                                for (String string : sobCharacter.getRightHand().getTraits()) {
                                    if (string.equals(TraitsEnum.PISTOL.label())) {
                                        shots++;
                                    }
                                }
                            }
                        }
                    }
                } else if (s.equals(TraitsEnum.BOW.label())) {
                    if (sobCharacter.getCharacterClass().getClassName().equals(CharacterClassEnum.JARGONO_NATIVE.male())) {
                        for (Skill skill : sobCharacter.getUpgrades()) {
                            if (skill.getName().equals(RuleExceptionEnum.QUICK_SHOT.label())) {
                                shots++;
                            }
                        }
                    }
                }
            }
            for (Attachment attachment : sobCharacter.getTailRanged().getAttachments()) {
                if (attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                    shots++;
                }
            }
            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.qc_tail_damgage);
            String text = "d" + sobCharacter.getTailRanged().getDamageDie().toString();
            if (sobCharacter.getTailRanged().getDamageBonus() > 0) {
                text += "+" + sobCharacter.getTailRanged().getDamageBonus().toString();
            }
            tv.setText(text);
            tv = findViewById(R.id.qc_tail_tohit);
            text = "d"
                    + sobCharacter.getTailRanged().getToHitDie().toString()
                    + " : "
                    + sobCharacter.getCharacterClass().getRangedToHit().toString()
                    + "+ ("
                    + sobCharacter.getTailRanged().getCritChance()
                    + "+)";
            tv.setText(text);
        }
    }
    private String calculateMeleeHitDie() {
        return "d"
                + sobCharacter.getMeleeToHitDie()
                + " : "
                + sobCharacter.getCharacterClass().getMeleeToHit().toString()
                + "+("
                + sobCharacter.getMeleeCritChance()
                + "+)";
    }

    private String calculateMeleeDamageDie() {
        String dmg = "d" + sobCharacter.getMeleeDamageDie().toString();
        if (sobCharacter.getMeleeDamageBonus() > 0) {
            dmg += "+" + sobCharacter.getMeleeDamageBonus();
        }
        return dmg;

    }

    private Integer calculateMeleeCombat() {
        Integer combat;
        combat = sobCharacter.getRightMelee() != null ?
                sobCharacter.getRightMelee().getName().equals(RuleExceptionEnum.TOOLS_OF_SCIENCE.label()) ?
                        sobCharacter.getCharacterClass().getCunning() + sobCharacter.getCunningBonus()
                        : sobCharacter.getCharacterClass().getCombat()
                : sobCharacter.getLeftMelee() != null ?
                sobCharacter.getLeftMelee().getName().equals(RuleExceptionEnum.TOOLS_OF_SCIENCE.label()) ?
                        sobCharacter.getCharacterClass().getCunning() + sobCharacter.getCunningBonus()
                        : sobCharacter.getCharacterClass().getCombat()
                : sobCharacter.getCharacterClass().getCombat();

        combat += sobCharacter.getCombatBonus();
        return combat;
    }

    protected void setStats() {
        String text;
        tv = findViewById(R.id.sob_fullscreen);
        Integer value;
        tv.setText(sobCharacter.getCharacterClass().getClassName());
        tv = findViewById(R.id.strength_value);
        value = (sobCharacter.getCharacterClass().getStrength())
                + (sobCharacter.getStrengthBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.agility_value);
        value = (sobCharacter.getCharacterClass().getAgility())
                + (sobCharacter.getAgilityBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.cunning_value);
        value = (sobCharacter.getCharacterClass().getCunning())
                + (sobCharacter.getCunningBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.spirit_value);
        value = (sobCharacter.getCharacterClass().getSpirit())
                + (sobCharacter.getSpiritBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.lore_value);
        value = (sobCharacter.getCharacterClass().getLore())
                + (sobCharacter.getLoreBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.luck_value);
        value = (sobCharacter.getCharacterClass().getLuck())
                + (sobCharacter.getLuckBonus());
        tv.setText(String.format(value.toString()));

        tv = findViewById(R.id.defense_value);
        value = (sobCharacter.getCharacterClass().getDefense());
        text = value.toString() + "+";
        tv.setText(text);

        tv = findViewById(R.id.willpower_value);
        value = (sobCharacter.getCharacterClass().getWillpower());
        text = value.toString() + "+";
        tv.setText(text);
        tv = findViewById(R.id.armor_value);
        if (sobCharacter.getArmor() < 7) {
            value = sobCharacter.getArmor();
            text = value.toString() + "+";
        } else {
            text = "-";
        }
        tv.setText(text);
        tv = findViewById(R.id.spirit_armor_value);
        if (sobCharacter.getSpiritArmor() < 7) {
            value = sobCharacter.getSpiritArmor();
            text = value.toString() + "+";
        } else {
            text = "-";
        }
        tv.setText(text);
        tv = findViewById(R.id.sob_character_name);
        tv.setText(sobCharacter.getCharacterName());

        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_darkstone);
        text = sobCharacter.getDarkStoneShards().toString() + "(" + sobCharacter.getDarkStoneCount() + ")";
        tv.setText(text);

        tv = findViewById(R.id.init_value);
        value = sobCharacter.getCharacterClass().getInitiative() + sobCharacter.getInitiativeBonus();
        tv.setText(String.format(value.toString()));

        tv = findViewById(R.id.move_value);
        text = "d6";
        if (sobCharacter.getMoveBonus() > 0) {
            text += " + " + sobCharacter.getMoveBonus().toString();
        }
        tv.setText(text);

        tv = findViewById(R.id.sob_level);
        tv.setText(String.format(sobCharacter.getLevel().toString()));

    }
    private void setHealthSanityStats() {
        Button health;
        health = findViewById(R.id.valHealth);
        Integer maxHealth = sobCharacter.getCharacterClass().getHealth() + sobCharacter.getHealthBonus();
        text = sobCharacter.getCurrentHealth().toString() + "/" + maxHealth.toString();
        health.setText(text);
        Button sanity;
        sanity = findViewById(R.id.valSanity);
        Integer maxSanity = sobCharacter.getCharacterClass().getSanity() + sobCharacter.getSanityBonus();
        text = sobCharacter.getCurrentSanity().toString() + "/" + maxSanity;
        sanity.setText(text);

        findViewById(R.id.negHealth).setOnClickListener((View view) -> {
            sobCharacter.setCurrentHealth(sobCharacter.getCurrentHealth() - 1);
            text = sobCharacter.getCurrentHealth().toString() + "/" + maxHealth.toString();
            health.setText(text);
        });
        findViewById(R.id.addHealth).setOnClickListener((View view) -> {
            if (sobCharacter.getCurrentHealth() < sobCharacter.getCharacterClass().getHealth() + sobCharacter.getHealthBonus()) {
                sobCharacter.setCurrentHealth(sobCharacter.getCurrentHealth() + 1);
                text = sobCharacter.getCurrentHealth().toString() + "/" + maxHealth.toString();
                health.setText(text);
            } else {
                Toast.makeText(this, "Health at full!", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.negSanity).setOnClickListener((View view) -> {
            sobCharacter.setCurrentSanity(sobCharacter.getCurrentSanity() - 1);
            text = sobCharacter.getCurrentSanity().toString() + "/" + maxSanity;
            sanity.setText(text);
        });
        findViewById(R.id.addSanity).setOnClickListener((View view) -> {
            if (sobCharacter.getCurrentSanity() < sobCharacter.getCharacterClass().getSanity() + sobCharacter.getSanityBonus()) {
                sobCharacter.setCurrentSanity(sobCharacter.getCurrentSanity() + 1);
                text = sobCharacter.getCurrentSanity().toString() + "/" + maxSanity;
                sanity.setText(text);
            } else {
                Toast.makeText(this, "Sanity at full!", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    @Override
    public void onBackPressed() {
        mCharacterViewModel.update(sobCharacter);
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
        finish();
    }
}
