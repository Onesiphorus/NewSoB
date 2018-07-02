package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipLeftHandRangedActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipLeftMeleeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipRightHandRangedActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.EquipRightMeleeActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.SpoilsActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu.CharacterActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.CharacterViewModel;

import static java.lang.Boolean.TRUE;


public class ShadowsOfBrimstoneActivity extends AppCompatActivity {

    CharacterViewModel mCharacterViewModel;
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    private SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCharacterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        setContentView(R.layout.activity_shadows_of_brimstone);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        mCharacterViewModel.update(sobCharacter);
        sobCharacter.setBonuses();


        TextView tv = findViewById(R.id.sob_fullscreen);
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
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.defense_value);
        value = (sobCharacter.getCharacterClass().getDefense());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.sanity_value);
        value = (sobCharacter.getCharacterClass().getSanity())
                + (sobCharacter.getSanityBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.willpower_value);
        value = (sobCharacter.getCharacterClass().getWillpower());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.armor_value);
        tv.setText(String.format(sobCharacter.getArmor().toString()));
        tv = findViewById(R.id.spirit_armor_value);
        tv.setText(String.format(sobCharacter.getSpiritArmor().toString()));
        tv = findViewById(R.id.sob_character_name);
        tv.setText(sobCharacter.getCharacterName());

        String text;

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

        tv = findViewById(R.id.sob_level);
        tv.setText(String.format(sobCharacter.getLevel().toString()));

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

        findViewById(R.id.combat_view).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, CombatViewActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }



    @Override
    public void onBackPressed() {
        //TODO overwrite existing character
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
        finish();
    }
}
