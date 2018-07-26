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

public class EquipTailMeleeActivity extends AppCompatActivity {

    MeleeWeapon meleeWeapon;
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        ArrayList<MeleeWeapon> MeleeWeaponOptions = new ArrayList<>(0);
        for (MeleeWeapon meleeWeapon : sobCharacter.getMeleeWeapons()) {
            if (meleeWeapon.getEquipped().equals(FALSE)
                    && meleeWeapon.getTwoHanded().equals(FALSE)
                    && meleeWeapon.getThreeHanded().equals(FALSE))
                MeleeWeaponOptions.add(meleeWeapon);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setMeleeWeapon(MeleeWeaponOptions);

        Button btn = findViewById(R.id.btn_unequip);
        String text = null != sobCharacter.getTailMelee() ? "Unequip " + sobCharacter.getTailMelee().getName() : "Empty";
        btn.setText(text);

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if (null != meleeWeapon) {
                sobCharacter.equipTailMelee(meleeWeapon);

                Intent intent = new Intent(this, CombatViewActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "No weapon selected, click cancel to return.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_unequip).setOnClickListener((View view) -> {
            Toast.makeText(this, sobCharacter.getTailMelee().getName() + " removed.", Toast.LENGTH_LONG).show();
            sobCharacter.unequipTailMelee();
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

    class MeleeWeaponListAdapter extends RecyclerView.Adapter<MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

        private final LayoutInflater mInflater;
        private List<MeleeWeapon> mMeleeWeapon;

        public MeleeWeaponListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public MeleeWeaponListAdapter.MeleeWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new MeleeWeaponListAdapter.MeleeWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MeleeWeaponListAdapter.MeleeWeaponViewHolder holder, int position) {
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