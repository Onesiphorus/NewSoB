package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Adapters.StringListAdapter;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.CharacterViewModel;

import java.util.ArrayList;

import static java.lang.Boolean.TRUE;


public class FinishCharacterActivity extends AppCompatActivity {

    CharacterViewModel mCharacterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCharacterViewModel = ViewModelProviders.of(this).get(CharacterViewModel.class);
        setContentView(R.layout.activity_finish_character);

        SobCharacter sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        TextView tv;
        Integer value;
        tv = findViewById(R.id.strength_value);
        value = (sobCharacter.getCharacterClass().getStrength()) + (sobCharacter.getStrengthBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.agility_value);
        value = (sobCharacter.getCharacterClass().getAgility()) + (sobCharacter.getAgilityBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.cunning_value);
        value = (sobCharacter.getCharacterClass().getCunning()) + (sobCharacter.getCunningBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.spirit_value);
        value = (sobCharacter.getCharacterClass().getSpirit()) + (sobCharacter.getSpiritBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.lore_value);
        value = (sobCharacter.getCharacterClass().getLore()) + (sobCharacter.getLoreBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.luck_value);
        value = (sobCharacter.getCharacterClass().getLuck()) + (sobCharacter.getLuckBonus());
        tv.setText(String.format(value.toString()));
        tv = findViewById(R.id.tv_character_name);
        tv.setText(sobCharacter.getCharacterName());

        ArrayList<String> startingGear = new ArrayList<>(0);
        for (GearBase gear : sobCharacter.getCharacterClass().getStartingGear()) {
            startingGear.add(gear.getName());
        }
        for (MeleeWeapon meleeWeapon : sobCharacter.getCharacterClass().getStartingMelee()) {
            startingGear.add(meleeWeapon.getName());
        }
        for (RangedWeapon rangedWeapon : sobCharacter.getCharacterClass().getStartingRanged()) {
            startingGear.add(rangedWeapon.getName());
        }
        for (Clothing clothing : sobCharacter.getCharacterClass().getStartingClothing()) {
            startingGear.add(clothing.getName());
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StringListAdapter adapter = new StringListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setString(startingGear);

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {

            mCharacterViewModel.insert(sobCharacter);
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            for (Clothing clothing : sobCharacter.getCharacterClass().getStartingClothing()) {
                clothing.setStarting(TRUE);
                clothing.setSell(0);
                sobCharacter.addClothing(clothing);
            }
            for (RangedWeapon rangedWeapon : sobCharacter.getCharacterClass().getStartingRanged()) {
                rangedWeapon.setStarting(TRUE);
                rangedWeapon.setSell(0);
                sobCharacter.addRangedWeapon(rangedWeapon);
            }
            for (MeleeWeapon meleeWeapon : sobCharacter.getCharacterClass().getStartingMelee()) {
                meleeWeapon.setStarting(TRUE);
                meleeWeapon.setSell(0);
                sobCharacter.addMeleeWeapon(meleeWeapon);
            }
            for (GearBase gearBase : sobCharacter.getCharacterClass().getStartingGear()) {
                gearBase.setStarting(TRUE);
                gearBase.setSell(0);
                sobCharacter.addGear(gearBase);
            }
            sobCharacter.setTraits(sobCharacter.getCharacterClass().getTraits());
            mCharacterViewModel.update(sobCharacter);
            sobCharacter.setBonuses();
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();


        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
