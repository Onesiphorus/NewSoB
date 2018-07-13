package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Town;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.GearTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class VisitShopActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    String shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_shop);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        shop = getIntent().getStringExtra("shop_type");

        TextView tv = findViewById(R.id.tv_shop_name);
        tv.setText(shop);
        String text;
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

        findViewById(R.id.btn_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", shop);
            intent.putExtra("gear_type", GearTypeEnum.GEAR.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_melee).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", shop);
            intent.putExtra("gear_type", GearTypeEnum.HAND_WEAPONS.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_ranged).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", shop);
            intent.putExtra("gear_type", GearTypeEnum.RANGED_WEAPONS.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_clothing).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", shop);
            intent.putExtra("gear_type", GearTypeEnum.CLOTHING.label());
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_attachments).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type", shop);
            intent.putExtra("gear_type", GearTypeEnum.GEAR_UPGRADES.label());
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, VisitTownActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
