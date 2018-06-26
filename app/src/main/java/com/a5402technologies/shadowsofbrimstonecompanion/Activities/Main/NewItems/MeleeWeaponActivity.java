package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.NewItems;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.a5402technologies.shadowsofbrimstonecompanion.Adapters.MeleeWeaponListAdapter;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.MeleeWeaponViewModel;

import java.util.List;

public class MeleeWeaponActivity extends AppCompatActivity {

    public static final int NEW_CHARACTER_CLASS_ACTIVITY_REQUEST_CODE = 1;
    private MeleeWeaponViewModel mMeleeWeaponViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMeleeWeaponViewModel = ViewModelProviders.of(this).get(MeleeWeaponViewModel.class);

        mMeleeWeaponViewModel.getAllMeleeWeapons().observe(this, new Observer<List<MeleeWeapon>>() {
            @Override
            public void onChanged(@Nullable List<MeleeWeapon> characterClasses) {
                adapter.setMeleeWeapons(characterClasses);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

