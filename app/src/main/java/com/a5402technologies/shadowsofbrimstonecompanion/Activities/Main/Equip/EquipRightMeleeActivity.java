package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Equip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

public class EquipLeftMeleeActivity extends AppCompatActivity {

    MeleeWeapon meleeWeapon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        SobCharacter sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        ArrayList<MeleeWeapon> MeleeWeaponOptions = new ArrayList<>(0);
        for(MeleeWeapon meleeWeapon : sobCharacter.getMeleeWeapons()) {
            if(meleeWeapon.getEquipped().equals(Boolean.FALSE)) MeleeWeaponOptions.add(meleeWeapon);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final EquipLeftMeleeActivity.MeleeWeaponListAdapter adapter = new EquipLeftMeleeActivity.MeleeWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setMeleeWeapon(MeleeWeaponOptions);

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if(null != meleeWeapon) {
                if(null != sobCharacter.getLeftMelee()) {
                    sobCharacter.unequipMelee(sobCharacter.findMeleeWeaponByName(sobCharacter.getLeftMelee().getName()));
                }
                if(meleeWeapon.getTwoHanded().equals(Boolean.TRUE)) {
                    if(null != sobCharacter.getRightMelee()) {
                        sobCharacter.unequipMelee(sobCharacter.findMeleeWeaponByName(sobCharacter.getRightMelee().getName()));
                        sobCharacter.setRightMelee(null);
                        //TODO change null for unequipped hands to 'MeleeWeapon emptyHand' and 'MeleeWeapon twoHanded' placeholders
                    }
                }
                if(null != sobCharacter.getRightMelee() && sobCharacter.getRightMelee().getTwoHanded().equals(Boolean.TRUE)) {
                    sobCharacter.unequipMelee(sobCharacter.findMeleeWeaponByName(sobCharacter.getRightMelee().getName()));
                    sobCharacter.setRightMelee(null);
                }
                sobCharacter.equipMelee(meleeWeapon);
                sobCharacter.setLeftMelee(meleeWeapon);

                Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "No weapon selected, click cancel to return.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            intent.putExtra("serializable_extra", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    class MeleeWeaponListAdapter extends RecyclerView.Adapter<EquipLeftMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

        class MeleeWeaponViewHolder extends RecyclerView.ViewHolder {
            private final Button meleeWeaponItemView;

            private MeleeWeaponViewHolder(View itemView) {
                super(itemView);
                meleeWeaponItemView = itemView.findViewById(R.id.textView);
            }
        }

        private final LayoutInflater mInflater;
        private List<MeleeWeapon> mMeleeWeapon;

        public MeleeWeaponListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public EquipLeftMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new EquipLeftMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(EquipLeftMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder holder, int position) {
            if (null != mMeleeWeapon) {
                MeleeWeapon current = mMeleeWeapon.get(position);
                holder.meleeWeaponItemView.setText(current.getName());
            } else {
                holder.meleeWeaponItemView.setText("No MeleeWeapon");
            }

            holder.meleeWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meleeWeapon = mMeleeWeapon.get(position);
                }
            });
        }

        public void setMeleeWeapon(List<MeleeWeapon> meleeWeapon) {
            mMeleeWeapon = meleeWeapon;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mMeleeWeapon) return mMeleeWeapon.size();
            else return 0;
        }
    }
}