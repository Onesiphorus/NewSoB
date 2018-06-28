package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Equip;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

public class EquipRightHandRangedActivity extends AppCompatActivity {
    RangedWeapon rangedWeapon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip_right_hand_ranged);
        SobCharacter sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        ArrayList<RangedWeapon> RangedWeaponOptions = new ArrayList<>(0);
        for(RangedWeapon rangedWeapon : sobCharacter.getRangedWeapons()) {
            if(rangedWeapon.getEquipped().equals(Boolean.FALSE)) RangedWeaponOptions.add(rangedWeapon);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setRangedWeapon(RangedWeaponOptions);

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if(null != rangedWeapon) {
                if(null != sobCharacter.getRightHand()) {
                    sobCharacter.unequipRanged(sobCharacter.findRangedWeaponByName(sobCharacter.getRightHand().getName()));
                }
                if(rangedWeapon.getTwoHanded().equals(Boolean.TRUE)) {
                    if(null != sobCharacter.getLeftHand()) {
                        sobCharacter.unequipRanged(sobCharacter.findRangedWeaponByName(sobCharacter.getLeftHand().getName()));
                        sobCharacter.setLeftHand(null);
                        //TODO change null for unequipped hands to 'RangedWeapon emptyHand' and 'RangedWeapon twoHanded' placeholders
                    }
                }
                if(null != sobCharacter.getLeftHand() && sobCharacter.getLeftHand().getTwoHanded().equals(Boolean.TRUE)) {
                    sobCharacter.unequipRanged(sobCharacter.findRangedWeaponByName(sobCharacter.getLeftHand().getName()));
                    sobCharacter.setLeftHand(null);
                }
                sobCharacter.equipRanged(rangedWeapon);
                sobCharacter.setRightHand(rangedWeapon);

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

    class RangedWeaponListAdapter extends RecyclerView.Adapter<RangedWeaponListAdapter.RangedWeaponViewHolder> {

        class RangedWeaponViewHolder extends RecyclerView.ViewHolder {
            private final Button rangedWeaponItemView;

            private RangedWeaponViewHolder(View itemView) {
                super(itemView);
                rangedWeaponItemView = itemView.findViewById(R.id.textView);
            }
        }

        private final LayoutInflater mInflater;
        private List<RangedWeapon> mRangedWeapon;

        public RangedWeaponListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RangedWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RangedWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RangedWeaponViewHolder holder, int position) {
            if (null != mRangedWeapon) {
                RangedWeapon current = mRangedWeapon.get(position);
                holder.rangedWeaponItemView.setText(current.getName());
            } else {
                holder.rangedWeaponItemView.setText("No RangedWeapon");
            }

            holder.rangedWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rangedWeapon = mRangedWeapon.get(position);
                }
            });
        }

        public void setRangedWeapon(List<RangedWeapon> rangedWeapon) {
            mRangedWeapon = rangedWeapon;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mRangedWeapon) return mRangedWeapon.size();
            else return 0;
        }
    }
}
