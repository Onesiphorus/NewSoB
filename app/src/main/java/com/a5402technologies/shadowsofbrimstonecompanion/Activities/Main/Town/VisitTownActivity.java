package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Town;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ShopEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class VisitTownActivity extends AppCompatActivity {
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_town);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        String text;
        TextView tv;
        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_darkstone);
        text = sobCharacter.getDarkStoneShards().toString() + "(" + sobCharacter.getDarkStoneCount() + ")";
        tv.setText(text);

        tv = findViewById(R.id.sob_level);
        tv.setText(String.format(sobCharacter.getLevel().toString()));
        tv = findViewById(R.id.sob_grit);
        Integer value = sobCharacter.getCharacterClass().getMaxGrit() + sobCharacter.getMaxGritBonus();
        tv.setText(value.toString());

        findViewById(R.id.btn_church).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.CHURCH.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_blacksmith).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.BLACKSMITH.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_frontier_outpost).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.FRONTIER_OUTPOST.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_docs_office).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.DOCS_OFFICE.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_saloon).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.SALOON.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_general_store).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.GENERAL_STORE.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_sheriffs_office).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.SHERIFFS_OFFICE.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_smugglers_den).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.SMUGGLERS_DEN.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_mutant_quarter).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.MUTANT_QUARTER.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_gambling_hall).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.GAMBLNG_HALL.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_street_market).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.STREET_MARKET.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_indian_trading_outpost).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, VisitShopActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", ShopEnum.INDIAN_TRADING_POST.label());
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ManagementMenuActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
