package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.NewItems.DebugActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

public class ManagementMenuActivity extends Activity {

    private View mContentView;

    private View mControlsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_management_menu);
        SobCharacter sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        findViewById(R.id.btn_new_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, DebugActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}
