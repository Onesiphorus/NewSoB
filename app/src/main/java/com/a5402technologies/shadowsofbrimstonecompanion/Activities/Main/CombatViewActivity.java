package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.ChangeLoadoutActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipLeftHandRangedActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipLeftMeleeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipRightHandRangedActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipRightMeleeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.RuleExceptionEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.TraitsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static java.lang.Boolean.TRUE;

public class CombatViewActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    TextView tv;
    String text;
    Integer val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_view);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        sobCharacter.setBonuses();
        setQuickStats();
        setHealthSanityStats();
        setWeapons();
        setQuickClothes();

        findViewById(R.id.layout_quick_stats).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
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
    }
    private void setQuickStats() {
        tv = findViewById(R.id.quick_agi);
        val = sobCharacter.getAgilityBonus() + sobCharacter.getCharacterClass().getAgility();
        text = "AGI: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_cun);
        val = sobCharacter.getCunningBonus() + sobCharacter.getCharacterClass().getCunning();
        text = "CUN: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_spi);
        val = sobCharacter.getSpiritBonus() + sobCharacter.getCharacterClass().getSpirit();
        text = "SPR: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_str);
        val = sobCharacter.getStrengthBonus() + sobCharacter.getCharacterClass().getStrength();
        text = "STR: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_lor);
        val = sobCharacter.getLoreBonus() + sobCharacter.getCharacterClass().getLore();
        text = "LOR: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_lck);
        val = sobCharacter.getLuckBonus() + sobCharacter.getCharacterClass().getLuck();
        text = "LCK: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_mv);
        val = sobCharacter.getMoveBonus();
        text = "MV: +" + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.quick_ini);
        val = sobCharacter.getInitiativeBonus() + sobCharacter.getCharacterClass().getInitiative();
        text = "INI: " + val.toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_darkstone);
        tv.setText(String.format(sobCharacter.getDarkStoneShards().toString()));
    }

    private void setWeapons() {
        Button rightRanged = findViewById(R.id.right_hand_ranged_weapon);
        rightRanged.setHint("empty");
        Button leftRanged = findViewById(R.id.left_hand_ranged_weapon);
        leftRanged.setHint("empty");
        Button rightMelee = findViewById(R.id.btn_right_melee);
        rightMelee.setHint("empty");
        Button leftMelee = findViewById(R.id.btn_left_melee);
        leftMelee.setHint("empty");

        if (sobCharacter.getRightHand() != null) {
            rightRanged.setText(sobCharacter.getRightHand().getName());
            rightMelee.setHint(sobCharacter.getRightHand().getName());
            if (sobCharacter.getRightHand().getTwoHanded().equals(TRUE)) {
                leftRanged.setHint(sobCharacter.getRightHand().getName());
                leftMelee.setHint(sobCharacter.getRightHand().getName());
            }
            tv = findViewById(R.id.right_hand_range);
            Integer range = sobCharacter.getRightHand().getRange();
            Integer shots = sobCharacter.getRightHand().getName().equals(RuleExceptionEnum.TRUSTY_PISTOL.label())
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getRightHand().getName().equals(RuleExceptionEnum.SPIRIT_BOW.label())
                    ? sobCharacter.getCharacterClass().getSpirit() + sobCharacter.getSpiritBonus()
                    : sobCharacter.getRightHand().getShots();
            for(String s : sobCharacter.getRightHand().getTraits()) {
                if(s.equals(TraitsEnum.PISTOL.label())) {
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
            tv = findViewById(R.id.right_hand_shots);

            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.right_hand_damage);
            text = "d" + sobCharacter.getRightHand().getDamageDie().toString();
            if(sobCharacter.getRightHand().getDamageBonus() > 0) {
                text += "+" + sobCharacter.getRightHand().getDamageBonus().toString();
            }
            tv.setText(text);
            tv = findViewById(R.id.right_hand_to_hit);
            text =  "d"
                    + sobCharacter.getRightHand().getToHitDie().toString()
                    + " : "
                    + sobCharacter.getCharacterClass().getRangedToHit().toString()
                    + "+ ("
                    + sobCharacter.getRightHand().getCritChance()
                    + "+)";
            tv.setText(text);
        }
        rightRanged.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipRightHandRangedActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        if (sobCharacter.getLeftHand() != null) {
            leftRanged = findViewById(R.id.left_hand_ranged_weapon);
            leftRanged.setText(sobCharacter.getLeftHand().getName());
            leftMelee.setHint(sobCharacter.getLeftHand().getName());
            if (sobCharacter.getLeftHand().getTwoHanded().equals(TRUE)) {
                rightRanged.setHint(sobCharacter.getLeftHand().getName());
                rightMelee.setHint(sobCharacter.getLeftHand().getName());
            }
            tv = findViewById(R.id.left_hand_range);
            tv.setText(String.format(sobCharacter.getLeftHand().getRange().toString()));
            tv = findViewById(R.id.left_hand_shots);
            Integer shots = (sobCharacter.getLeftHand().getName().equals("Trusty Pistol"))
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getLeftHand().getName().equals(RuleExceptionEnum.SPIRIT_BOW.label())
                    ? sobCharacter.getCharacterClass().getSpirit() + sobCharacter.getSpiritBonus()
                    : sobCharacter.getLeftHand().getShots();
            for(String s : sobCharacter.getLeftHand().getTraits()) {
                if(s.equals(TraitsEnum.PISTOL.label())) {
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
                }
            }
            for (Attachment attachment : sobCharacter.getLeftHand().getAttachments()) {
                if (attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                    shots++;
                }
            }
            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.left_hand_damage);
            String text = "d" + sobCharacter.getLeftHand().getDamageDie().toString();
            if (sobCharacter.getLeftHand().getDamageBonus() > 0) {
                text += "+" + sobCharacter.getLeftHand().getDamageBonus().toString();
            }
            tv.setText(text);
            tv = findViewById(R.id.left_hand_to_hit);
            text = "d"
                    + sobCharacter.getLeftHand().getToHitDie().toString()
                    + " : "
                    + sobCharacter.getCharacterClass().getRangedToHit().toString()
                    + "+ ("
                    + sobCharacter.getLeftHand().getCritChance()
                    + "+)";
            tv.setText(text);
        }
        leftRanged.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipLeftHandRangedActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        if (null != sobCharacter.getLeftMelee()) {
            leftMelee.setText(sobCharacter.getLeftMelee().getName());
            leftRanged.setHint(sobCharacter.getLeftMelee().getName());
            if (sobCharacter.getLeftMelee().getTwoHanded().equals(TRUE)) {
                rightMelee.setHint(sobCharacter.getLeftMelee().getName());
                rightRanged.setHint(sobCharacter.getLeftMelee().getName());
            }
        }
        leftMelee.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipLeftMeleeActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        if (null != sobCharacter.getRightMelee()) {
            rightMelee.setText(sobCharacter.getRightMelee().getName());
            rightRanged.setHint(sobCharacter.getRightMelee().getName());
            if (sobCharacter.getRightMelee().getTwoHanded().equals(TRUE)) {
                leftMelee.setHint(sobCharacter.getRightMelee().getName());
                leftRanged.setHint(sobCharacter.getRightMelee().getName());
            }
        }
        rightMelee.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipRightMeleeActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        tv = findViewById(R.id.melee_combat);
        Integer combat = calculateMeleeCombat();
        tv.setText(String.format(combat.toString()));

        tv = findViewById(R.id.melee_to_hit);
        text = calculateMeleeHitDie();
        tv.setText(text);

        tv = findViewById(R.id.melee_damage);
        text = calculateMeleeDamageDie();
        tv.setText(text);
    }

    private void setHealthSanityStats() {
        Button health;
        health = findViewById(R.id.valHealth);
        health.setText(String.format(sobCharacter.getCurrentHealth().toString()));
        Button sanity;
        sanity = findViewById(R.id.valSanity);
        sanity.setText(String.format(sobCharacter.getCurrentSanity().toString()));

        findViewById(R.id.negHealth).setOnClickListener((View view) -> {
            sobCharacter.setCurrentHealth(sobCharacter.getCurrentHealth() - 1);
            health.setText(String.format(sobCharacter.getCurrentHealth().toString()));
        });
        findViewById(R.id.addHealth).setOnClickListener((View view) -> {
            if (sobCharacter.getCurrentHealth() < sobCharacter.getCharacterClass().getHealth() + sobCharacter.getHealthBonus()) {
                sobCharacter.setCurrentHealth(sobCharacter.getCurrentHealth() + 1);
                health.setText(String.format(sobCharacter.getCurrentHealth().toString()));
            } else {
                Toast.makeText(this, "Health at full!", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.negSanity).setOnClickListener((View view) -> {
            sobCharacter.setCurrentSanity(sobCharacter.getCurrentSanity() - 1);
            sanity.setText(String.format(sobCharacter.getCurrentSanity().toString()));
        });
        findViewById(R.id.addSanity).setOnClickListener((View view) -> {
            if (sobCharacter.getCurrentSanity() < sobCharacter.getCharacterClass().getSanity() + sobCharacter.getSanityBonus()) {
                sobCharacter.setCurrentSanity(sobCharacter.getCurrentSanity() + 1);
                sanity.setText(String.format(sobCharacter.getCurrentSanity().toString()));
            } else {
                Toast.makeText(this, "Sanity at full!", Toast.LENGTH_LONG).show();
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
