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
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu.CharacterActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.CharacterViewModel;

import static java.lang.Boolean.TRUE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ShadowsOfBrimstoneActivity extends AppCompatActivity {
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
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
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

        Button rightRanged = findViewById(R.id.right_hand_ranged_weapon);
        rightRanged.setHint("empty");
        Button leftRanged = findViewById(R.id.left_hand_ranged_weapon);
        leftRanged.setHint("empty");
        Button rightMelee = findViewById(R.id.btn_right_melee);
        rightMelee.setHint("empty");
        Button leftMelee = findViewById(R.id.btn_left_melee);
        leftMelee.setHint("empty");

        if (sobCharacter.getRightHand() != null) {
            //TODO logic for two handed
            rightRanged.setText(sobCharacter.getRightHand().getName());
            rightMelee.setHint(sobCharacter.getRightHand().getName());
            if (sobCharacter.getRightHand().getTwoHanded().equals(TRUE)) {
                leftRanged.setHint(sobCharacter.getRightHand().getName());
                leftMelee.setHint(sobCharacter.getRightHand().getName());
            }
            tv = findViewById(R.id.right_hand_range);
            tv.setText(String.format(sobCharacter.getRightHand().getRange().toString()));
            tv = findViewById(R.id.right_hand_shots);
            Integer shots = (sobCharacter.getRightHand().getName().equals("Trusty Pistol"))
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getRightHand().getShots();
            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.right_hand_damage);
            String text =
                    "D"
                            + sobCharacter.getRightHand().getDamageDie().toString()
                            + "+"
                            + sobCharacter.getRightHand().getDamageBonus().toString();
            tv.setText(text);
            tv = findViewById(R.id.right_hand_to_hit);
            text =
                    "D" + sobCharacter.getRightHand().getToHitDie().toString()
                            + ":"
                            + sobCharacter.getCharacterClass().getRangedToHit().toString()
                            + "+("
                            + sobCharacter.getRightHand().getCritChance()
                            + "+)";
            tv.setText(text);
        }
        rightRanged.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipRightHandRangedActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        if (sobCharacter.getLeftHand() != null) {
            leftRanged = findViewById(R.id.left_hand_ranged_weapon);
            leftRanged.setText(sobCharacter.getLeftHand().getName());
            leftMelee.setHint(sobCharacter.getLeftHand().getName());
            if (sobCharacter.getLeftHand().getTwoHanded().equals(TRUE)) {
                rightRanged.setHint(sobCharacter.getLeftHand().getName());
                rightMelee.setHint(sobCharacter.getLeftHand().getName());
            }
            tv = findViewById(R.id.left_hand_range);
            tv.setText(String.format(sobCharacter.getLeftHand().getRange().toString()));
            tv = findViewById(R.id.left_hand_shots);
            Integer shots = (sobCharacter.getLeftHand().getName().equals("Trusty Pistol"))
                    ? sobCharacter.getCharacterClass().getAgility() + sobCharacter.getAgilityBonus()
                    : sobCharacter.getLeftHand().getShots();
            tv.setText(String.format(shots.toString()));
            tv = findViewById(R.id.left_hand_damage);
            String text =
                    "D" + sobCharacter.getLeftHand().getDamageDie().toString()
                            + "+"
                            + sobCharacter.getLeftHand().getDamageBonus().toString();
            tv.setText(text);
            tv = findViewById(R.id.left_hand_to_hit);
            text =
                    "D"
                            + sobCharacter.getLeftHand().getToHitDie().toString()
                            + ":"
                            + sobCharacter.getCharacterClass().getRangedToHit().toString()
                            + "+("
                            + sobCharacter.getLeftHand().getCritChance()
                            + "+)";
            tv.setText(text);
            //TODO make invisible when not populated
        }
        leftRanged.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipLeftHandRangedActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });


        if (null != sobCharacter.getLeftMelee()) {
            leftMelee.setText(sobCharacter.getLeftMelee().getName());
            leftRanged.setHint(sobCharacter.getLeftMelee().getName());
            if (sobCharacter.getLeftMelee().getTwoHanded().equals(TRUE)) {
                rightMelee.setHint(sobCharacter.getLeftMelee().getName());
                rightRanged.setHint(sobCharacter.getLeftMelee().getName());
            }
        }
        leftMelee.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipLeftMeleeActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        if (null != sobCharacter.getRightMelee()) {
            rightMelee.setText(sobCharacter.getRightMelee().getName());
            rightRanged.setHint(sobCharacter.getRightMelee().getName());
            if (sobCharacter.getRightMelee().getTwoHanded().equals(TRUE)) {
                leftMelee.setHint(sobCharacter.getRightMelee().getName());
                leftRanged.setHint(sobCharacter.getRightMelee().getName());
            }
        }
        rightMelee.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, EquipRightMeleeActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
        tv = findViewById(R.id.melee_combat);
        Integer combat = sobCharacter.getCharacterClass().getCombat() + sobCharacter.getCombatBonus();
        tv.setText(String.format(combat.toString()));
        tv = findViewById(R.id.melee_to_hit);
        String text =
                "D"
                        + sobCharacter.getMeleeToHitDie()
                        + " : "
                        + sobCharacter.getCharacterClass().getMeleeToHit().toString()
                        + "+("
                        + sobCharacter.getMeleeCritChance()
                        + "+)";
        tv.setText(text);
        tv = findViewById(R.id.melee_damage);
        text =
                sobCharacter.getMeleeDamageDie()
                        + "+"
                        + sobCharacter.getMeleeDamageBonus();
        tv.setText(text);

        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);

        findViewById(R.id.btn_sob_gear).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
        });

        findViewById(R.id.btn_spoils).setOnClickListener((View view) -> {

        });
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.sob_fullscreen);


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
        findViewById(R.id.btn_sob_gear).setOnTouchListener(mDelayHideTouchListener);
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

    @Override
    public void onBackPressed() {
        //TODO overwrite existing character
        Intent intent = new Intent(this, CharacterActivity.class);
        startActivity(intent);
        finish();
    }
}
