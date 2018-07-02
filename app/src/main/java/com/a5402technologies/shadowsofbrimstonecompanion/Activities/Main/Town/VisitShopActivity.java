package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Town;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems.AddGearBaseActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ShopEnum;
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

        findViewById(R.id.btn_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type",shop);
            intent.putExtra("gear_type", "gear");
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_melee).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type",shop);
            intent.putExtra("gear_type", "melee");
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_ranged).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type",shop);
            intent.putExtra("gear_type", "ranged");
            startActivity(intent);
            finish();
        });
        findViewById(R.id.btn_clothing).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("shop_type",shop);
            intent.putExtra("gear_type", "clothing");
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
