package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.ChangeLoadoutActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.SpoilsActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu.CharacterActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCharacterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        setContentView(R.layout.activity_shadows_of_brimstone);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        mCharacterViewModel.update(sobCharacter);
        sobCharacter.setBonuses();

        TextView tv = findViewById(R.id.right_hand_weapon);
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

        setStats();
        setQuickClothes();
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

        findViewById(R.id.btn_spoils).setOnClickListener((View view) -> {
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
        if(sobCharacter.getMaxWeight() >= sobCharacter.getWeight()) tv.setHintTextColor(GREEN);
        else tv.setHintTextColor(RED);
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
        tv = findViewById(R.id.health_value);
        value = (sobCharacter.getCharacterClass().getHealth())
                + (sobCharacter.getHealthBonus());
        text = sobCharacter.getCurrentHealth().toString() + "/" + value.toString();
        tv.setText(text);
        tv = findViewById(R.id.defense_value);
        value = (sobCharacter.getCharacterClass().getDefense());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.sanity_value);
        value = (sobCharacter.getCharacterClass().getSanity())
                + (sobCharacter.getSanityBonus());
        text = sobCharacter.getCurrentSanity().toString() + "/" + value.toString();
        tv.setText(text);
        tv = findViewById(R.id.willpower_value);
        value = (sobCharacter.getCharacterClass().getWillpower());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.armor_value);
        tv.setText(String.format(sobCharacter.getArmor().toString()));
        tv = findViewById(R.id.spirit_armor_value);
        tv.setText(String.format(sobCharacter.getSpiritArmor().toString()));
        tv = findViewById(R.id.sob_character_name);
        tv.setText(sobCharacter.getCharacterName());

        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_darkstone);
        tv.setText(String.format(sobCharacter.getDarkStoneShards().toString()));

        tv = findViewById(R.id.init_value);
        value = sobCharacter.getCharacterClass().getInitiative() + sobCharacter.getInitiativeBonus();
        tv.setText(String.format(value.toString()));

        tv = findViewById(R.id.move_value);
        text = "d6";
        if (sobCharacter.getMoveBonus() > 0) {
            text += " + " + sobCharacter.getMoveBonus().toString();
        }
        tv.setText(text);

        tv = findViewById(R.id.max_grit_value);
        value = sobCharacter.getCharacterClass().getMaxGrit() + sobCharacter.getMaxGritBonus();
        tv.setText(String.format(String.format(value.toString())));

        tv = findViewById(R.id.sob_level);
        tv.setText(String.format(sobCharacter.getLevel().toString()));

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
