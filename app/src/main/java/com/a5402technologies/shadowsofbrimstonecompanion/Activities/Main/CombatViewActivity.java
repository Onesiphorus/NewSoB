package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipLeftHandRangedActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipLeftMeleeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipRightHandRangedActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipRightMeleeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.RuleExceptionEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import static java.lang.Boolean.TRUE;

public class CombatViewActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    TextView tv;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat_view);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        sobCharacter.setBonuses();

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
            if(sobCharacter.getCurrentHealth() < sobCharacter.getCharacterClass().getHealth() + sobCharacter.getHealthBonus()) {
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
            if(sobCharacter.getCurrentSanity() < sobCharacter.getCharacterClass().getSanity() + sobCharacter.getSanityBonus()) {
                sobCharacter.setCurrentSanity(sobCharacter.getCurrentSanity() + 1);
                sanity.setText(String.format(sobCharacter.getCurrentSanity().toString()));
            } else {
                Toast.makeText(this, "Sanity at full!", Toast.LENGTH_LONG).show();
            }
        });

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
            Integer shots = (sobCharacter.getRightHand().getName().equals(RuleExceptionEnum.TRUSTY_PISTOL.label()))
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getRightHand().getShots();
            for(Attachment attachment : sobCharacter.getRightHand().getAttachments()) {
                if(attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                    shots++;
                } else if(attachment.getName().equals(RuleExceptionEnum.DARK_STONE_BARREL.label())){
                    range += 4;
                }
            }
            tv.setText(String.format(range.toString()));
            tv = findViewById(R.id.right_hand_shots);

            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.right_hand_damage);
            text =
                    "D"
                            + sobCharacter.getRightHand().getDamageDie().toString()
                            + "+"
                            + sobCharacter.getRightHand().getDamageBonus().toString();
            tv.setText(text);
            tv = findViewById(R.id.right_hand_to_hit);
            text =
                    "D" + sobCharacter.getRightHand().getToHitDie().toString()
                            + ":"
                            + sobCharacter.getCharacterClass().getRangedToHit().toString()
                            + "+("
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
                    : sobCharacter.getLeftHand().getShots();
            for(Attachment attachment : sobCharacter.getLeftHand().getAttachments()) {
                if(attachment.getName().equals(RuleExceptionEnum.DARK_STONE_GRIP.label())) {
                    shots++;
                }
            }
            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.left_hand_damage);
            String text =
                    "D" + sobCharacter.getLeftHand().getDamageDie().toString()
                            + "+"
                            + sobCharacter.getLeftHand().getDamageBonus().toString();
            tv.setText(text);
            tv = findViewById(R.id.left_hand_to_hit);
            text =
                    "D"
                            + sobCharacter.getLeftHand().getToHitDie().toString()
                            + ":"
                            + sobCharacter.getCharacterClass().getRangedToHit().toString()
                            + "+("
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

    private String calculateMeleeHitDie() {
        return "D"
                + sobCharacter.getMeleeToHitDie()
                + " : "
                + sobCharacter.getCharacterClass().getMeleeToHit().toString()
                + "+("
                + sobCharacter.getMeleeCritChance()
                + "+)";
    }

    private String calculateMeleeDamageDie() {
        return sobCharacter.getMeleeDamageDie()
                + "+"
                + sobCharacter.getMeleeDamageBonus();
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
