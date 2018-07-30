package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems.AddSkillActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ModifiersEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class LevelUpActivity extends AppCompatActivity {
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_up);
        ArrayList<String> tempMods = new ArrayList<>(0);
        Button btn = findViewById(R.id.btn_unequip);
        btn.setText("Increase By");
        btn = findViewById(R.id.btn_cancel);
        btn.setText("Decrease By");
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");


        findViewById(R.id.health_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.health_value);
            tempMods.add(ModifiersEnum.MAX_HEALTH.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.MAX_HEALTH.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.sanity_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.sanity_value);
            tempMods.add(ModifiersEnum.MAX_SANITY.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.MAX_SANITY.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.agility_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.agility_value);
            tempMods.add(ModifiersEnum.AGILITY.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.AGILITY.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.cunning_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.cunning_value);
            tempMods.add(ModifiersEnum.CUNNING.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.CUNNING.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.spirit_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.spirit_value);
            tempMods.add(ModifiersEnum.SPIRIT.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.SPIRIT.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.strength_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.strength_value);
            tempMods.add(ModifiersEnum.STRENGTH.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.STRENGTH.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.lore_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.lore_value);
            tempMods.add(ModifiersEnum.LORE.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.LORE.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.luck_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.luck_value);
            tempMods.add(ModifiersEnum.LUCK.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.LUCK.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.init_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.init_value);
            tempMods.add(ModifiersEnum.INITIATIVE.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.INITIATIVE.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.move_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.move_value);
            tempMods.add(ModifiersEnum.MOVE.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.MOVE.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.max_grit_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.max_grit_value);
            tempMods.add(ModifiersEnum.MAX_GRIT.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.MAX_GRIT.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.max_side_bag_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.max_side_bag_value);
            tempMods.add(ModifiersEnum.SIDE_BAG_CAPACITY.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.SIDE_BAG_CAPACITY.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        findViewById(R.id.max_corruption_label).setOnClickListener((View view) -> {
            TextView tv = findViewById(R.id.max_corruption_value);
            tempMods.add(ModifiersEnum.MAX_CORRUPTION.label());
            Integer i = 0;
            for (String string : tempMods) {
                if (string.equals(ModifiersEnum.MAX_CORRUPTION.label())) i++;
            }
            tv.setText(String.format(i.toString()));
        });
        btn = findViewById(R.id.btn_equip);
        String text = "Accept and select new Upgrade";
        btn.setText(text);
        btn.setOnClickListener((View view) -> {
            Integer level = sobCharacter.getLevel();
            Boolean levelUp = FALSE;
            Integer cost = 0;
            switch (level) {
                case 1:
                    levelUp = sobCharacter.getExperience() > 500 ? TRUE : FALSE;
                    cost = 500;
                    break;
                case 2:
                    levelUp = sobCharacter.getExperience() > 1000 ? TRUE : FALSE;
                    cost = 1000;
                    break;
                case 3:
                    levelUp = sobCharacter.getExperience() > 2000 ? TRUE : FALSE;
                    cost = 2000;
                    break;
                case 4:
                    levelUp = sobCharacter.getExperience() > 3000 ? TRUE : FALSE;
                    cost = 3000;
                    break;
                case 5:
                    levelUp = sobCharacter.getExperience() > 4500 ? TRUE : FALSE;
                    cost = 4500;
                    break;
                case 6:
                    levelUp = sobCharacter.getExperience() > 6000 ? TRUE : FALSE;
                    cost = 6000;
                    break;
                case 7:
                    levelUp = sobCharacter.getExperience() > 8000 ? TRUE : FALSE;
                    cost = 8000;
                    break;
            }
            if (levelUp.equals(TRUE)) {
                sobCharacter.setLevel(sobCharacter.getLevel() + 1);
                sobCharacter.setExperience(sobCharacter.getExperience() - cost);
                for (String modifier : tempMods) {
                    sobCharacter.addModifier(modifier);
                }
                sobCharacter.setBonuses();
                sobCharacter.setCurrentSanity(sobCharacter.getSanityBonus() + sobCharacter.getCharacterClass().getSanity());
                sobCharacter.setCurrentHealth(sobCharacter.getHealthBonus() + sobCharacter.getCharacterClass().getHealth());
                Intent intent = new Intent(this, AddSkillActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Integer diff = cost - sobCharacter.getExperience();
                Toast.makeText(this, "You need " + diff.toString() + "xp to level up!", Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.btn_unequip).setOnClickListener((View view) -> {
            for (String modifier : tempMods) {
                sobCharacter.addModifier(modifier);
            }
            sobCharacter.setBonuses();
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            for (String modifier : tempMods) {
                sobCharacter.removeModifier(modifier);
            }
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

}
