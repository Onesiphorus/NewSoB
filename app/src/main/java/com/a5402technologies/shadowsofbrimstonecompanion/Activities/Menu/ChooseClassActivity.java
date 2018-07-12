package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.a5402technologies.shadowsofbrimstonecompanion.Adapters.CharacterClassListAdapter;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.CharacterClassViewModel;

import java.util.List;

public class ChooseClassActivity extends AppCompatActivity {

    private CharacterClassViewModel mCharacterClassViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CharacterClassListAdapter adapter = new CharacterClassListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCharacterClassViewModel = ViewModelProviders.of(this).get(CharacterClassViewModel.class);

        mCharacterClassViewModel.getAllCharacterClasses().observe(this, new Observer<List<CharacterClass>>() {
            @Override
            public void onChanged(@Nullable List<CharacterClass> characterClasses) {
                adapter.setCharactersClasses(characterClasses);
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