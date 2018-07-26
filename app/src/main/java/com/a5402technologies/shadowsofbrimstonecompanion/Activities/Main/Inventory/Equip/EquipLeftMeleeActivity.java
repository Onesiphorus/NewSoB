package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip;

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

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.CombatViewActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EquipLeftMeleeActivity extends AppCompatActivity {

    MeleeWeapon meleeWeapon;
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        ArrayList<MeleeWeapon> MeleeWeaponOptions = new ArrayList<>(0);
        for (MeleeWeapon meleeWeapon : sobCharacter.getMeleeWeapons()) {
            if (meleeWeapon.getEquipped().equals(Boolean.FALSE)
                    && meleeWeapon.getFree().equals(Boolean.FALSE)
                    && (meleeWeapon.getThreeHanded().equals(FALSE)
                    || sobCharacter.getThirdHand().equals(TRUE)))
                MeleeWeaponOptions.add(meleeWeapon);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final EquipLeftMeleeActivity.MeleeWeaponListAdapter adapter = new EquipLeftMeleeActivity.MeleeWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setMeleeWeapon(MeleeWeaponOptions);

        Button btn = findViewById(R.id.btn_unequip);
        String text = null != sobCharacter.getLeftMelee() ? "Unequip " + sobCharacter.getLeftMelee().getName() : "Empty";
        btn.setText(text);

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if (null != meleeWeapon) {
                sobCharacter.equipLeftMelee(meleeWeapon);

                Intent intent = new Intent(this, CombatViewActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "No weapon selected, click cancel to return.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_unequip).setOnClickListener((View view) -> {
            Toast.makeText(this, sobCharacter.getLeftMelee().getName() + " removed.", Toast.LENGTH_LONG).show();
            sobCharacter.unequipLeftMelee();
            Intent intent = new Intent(this, CombatViewActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, CombatViewActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

    class MeleeWeaponListAdapter extends RecyclerView.Adapter<EquipLeftMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

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
                    Button btn = findViewById(R.id.btn_equip);
                    String text = "Equip " + meleeWeapon.getName();
                    btn.setText(text);
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

        class MeleeWeaponViewHolder extends RecyclerView.ViewHolder {
            private final Button meleeWeaponItemView;

            private MeleeWeaponViewHolder(View itemView) {
                super(itemView);
                meleeWeaponItemView = itemView.findViewById(R.id.textView);
            }
        }
    }
}