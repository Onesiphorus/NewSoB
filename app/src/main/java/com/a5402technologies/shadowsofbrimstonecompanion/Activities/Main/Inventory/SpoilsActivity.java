package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import org.w3c.dom.Text;

public class SpoilsActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spoils);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        TextView tvExp = findViewById(R.id.spoils_xp);

        tvExp.setText(String.format(Integer.valueOf(0).toString()));

        findViewById(R.id.neg10xp).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvExp.getText().toString()) - 10;
            tvExp.setText(exp.toString());
        });
        findViewById(R.id.neg5xp).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvExp.getText().toString()) - 5;
            tvExp.setText(exp.toString());
        });
        findViewById(R.id.plus5xp).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvExp.getText().toString()) + 5;
            tvExp.setText(exp.toString());
        });
        findViewById(R.id.plus10xp).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvExp.getText().toString()) + 10;
            tvExp.setText(exp.toString());
        });
        findViewById(R.id.plus25xp).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvExp.getText().toString()) + 25;
            tvExp.setText(exp.toString());
        });
        findViewById(R.id.plus50xp).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvExp.getText().toString()) + 50;
            tvExp.setText(exp.toString());
        });

        TextView tvMoney = findViewById(R.id.spoils_money);
        Integer money = 0;
        tvMoney.setText(Integer.valueOf(0).toString());

        findViewById(R.id.neg100).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvMoney.getText().toString()) - 100;
            tvMoney.setText(exp.toString());
        });
        findViewById(R.id.neg25).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvMoney.getText().toString()) - 25;
            tvMoney.setText(exp.toString());
        });
        findViewById(R.id.neg5).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvMoney.getText().toString()) - 5;
            tvMoney.setText(exp.toString());
        });
        findViewById(R.id.plus5).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvMoney.getText().toString()) + 5;
            tvMoney.setText(exp.toString());
        });
        findViewById(R.id.plus25).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvMoney.getText().toString()) + 25;
            tvMoney.setText(exp.toString());
        });
        findViewById(R.id.plus100).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvMoney.getText().toString()) + 100;
            tvMoney.setText(exp.toString());
        });

        TextView tvDarkstone = findViewById(R.id.spoils_ds);
        Integer darkstone = 0;
        tvDarkstone.setText(Integer.valueOf(0).toString());

        findViewById(R.id.neg1ds).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvDarkstone.getText().toString()) - 1;
            tvDarkstone.setText(exp.toString());
        });
        findViewById(R.id.pus1ds).setOnClickListener((View view) -> {
            Integer exp = Integer.parseInt(tvDarkstone.getText().toString()) + 1;
            tvDarkstone.setText(exp.toString());
        });

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            sobCharacter.addDarkstoneShards(Integer.parseInt(tvDarkstone.getText().toString()));
            sobCharacter.addGold(Integer.parseInt(tvMoney.getText().toString()));
            sobCharacter.addExp(Integer.parseInt(tvExp.getText().toString()));
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
