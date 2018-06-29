package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.SkillViewModel;

import java.util.ArrayList;
import java.util.List;

public class CreateCharacterActivity extends AppCompatActivity {

    private SkillViewModel mSkillViewModel;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
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
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };
    private Skill skill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        CharacterClass characterClass = (CharacterClass) getIntent().getSerializableExtra("serializable_object");



        RecyclerView recyclerView = findViewById(R.id.recyclerview_skills);
        final SkillListAdapter adapter = new SkillListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSkillViewModel = ViewModelProviders.of(this).get(SkillViewModel.class);

        mSkillViewModel.getAllSkill().observe(this, new Observer<List<Skill>>() {
            @Override
            public void onChanged(@Nullable List<Skill> skills) {
                ArrayList<Skill> newList = new ArrayList<>(0);
                for(Skill skill : skills) {
                    if(skill.getType().equals(characterClass.getClassName())
                            && skill.getLevel() == 0) {
                        newList.add(skill);
                    }
                }
                adapter.setSkill(newList);
            }
        });

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.create_button).setOnTouchListener(mDelayHideTouchListener);



        TextView tv = findViewById(R.id.fullscreen_content);
        tv.setText(characterClass.getClassName());
        tv = findViewById(R.id.strength_value);
        tv.setText(String.format(characterClass.getStrength().toString()));
        tv = findViewById(R.id.agility_value);
        tv.setText(String.format(characterClass.getAgility().toString()));
        tv = findViewById(R.id.cunning_value);
        tv.setText(String.format(characterClass.getCunning().toString()));
        tv = findViewById(R.id.spirit_value);
        tv.setText(String.format(characterClass.getSpirit().toString()));
        tv = findViewById(R.id.lore_value);
        tv.setText(String.format(characterClass.getLore().toString()));
        tv = findViewById(R.id.luck_value);
        tv.setText(String.format(characterClass.getLuck().toString()));
        tv = findViewById(R.id.health_value);
        tv.setText(String.format(characterClass.getHealth().toString()));
        tv = findViewById(R.id.defense_value);
        tv.setText(String.format(characterClass.getDefense().toString()));
        tv = findViewById(R.id.sanity_value);
        tv.setText(String.format(characterClass.getSanity().toString()));
        tv = findViewById(R.id.willpower_value);
        tv.setText(String.format(characterClass.getWillpower().toString()));
        tv = findViewById(R.id.armor_value);
        tv.setText("0");
        tv = findViewById(R.id.spirit_armor_value);
        tv.setText("0");

        EditText name = findViewById(R.id.character_name_value);

        //TODO Modify Starting Gear according to starting upgrade

        Button btn = findViewById(R.id.create_button);
        btn.setOnClickListener((View view) -> {
            SobCharacter sobCharacter = new SobCharacter(name.getText().toString(), characterClass);
            sobCharacter.addUpgrade(skill);
            Intent intent = new Intent(this, FinishCharacterActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
        });
    }

    class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillViewHolder> {

        class SkillViewHolder extends RecyclerView.ViewHolder {
            private final RadioButton skillItemView;

            private SkillViewHolder(View itemView) {
                super(itemView);
                skillItemView = itemView.findViewById(R.id.radioButton);
            }
        }

        private final LayoutInflater mInflater;
        private List<Skill> mSkill;

        public SkillListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_character, parent, false);
            return new SkillViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SkillViewHolder holder, int position) {
            if (null != mSkill) {
                Skill current = mSkill.get(position);
                holder.skillItemView.setText(current.getName());
            } else {
                holder.skillItemView.setText("No Skill");
            }
            holder.skillItemView.setOnClickListener((View view) -> {
                skill = mSkill.get(position);
            });
        }

        public void setSkill(List<Skill> skill) {
            mSkill = skill;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mSkill) return mSkill.size();
            else return 0;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
