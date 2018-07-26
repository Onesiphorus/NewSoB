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
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class EquipTailRangedActivity extends AppCompatActivity {

    RangedWeapon rangedWeapon;
    SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        ArrayList<RangedWeapon> RangedWeaponOptions = new ArrayList<>(0);
        for (RangedWeapon rangedWeapon : sobCharacter.getRangedWeapons()) {
            if (rangedWeapon.getEquipped().equals(FALSE)
                    && rangedWeapon.getTwoHanded().equals(FALSE)
                    && rangedWeapon.getThreeHanded().equals(FALSE))
                RangedWeaponOptions.add(rangedWeapon);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setRangedWeapon(RangedWeaponOptions);

        Button btn = findViewById(R.id.btn_unequip);
        String text = null != sobCharacter.getTailRanged() ? "Unequip " + sobCharacter.getTailRanged().getName() : "Empty";
        btn.setText(text);

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if (null != rangedWeapon) {
                sobCharacter.equipTailRanged(rangedWeapon);

                Intent intent = new Intent(this, CombatViewActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "No weapon selected, click cancel to return.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_unequip).setOnClickListener((View view) -> {
            Toast.makeText(this, sobCharacter.getTailRanged().getName() + " removed.", Toast.LENGTH_LONG).show();
            sobCharacter.unequipTailRanged();
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

    class RangedWeaponListAdapter extends RecyclerView.Adapter<RangedWeaponListAdapter.RangedWeaponViewHolder> {

        private final LayoutInflater mInflater;
        private List<RangedWeapon> mRangedWeapon;

        public RangedWeaponListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RangedWeaponListAdapter.RangedWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RangedWeaponListAdapter.RangedWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RangedWeaponListAdapter.RangedWeaponViewHolder holder, int position) {
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
                    Button btn = findViewById(R.id.btn_equip);
                    String text = "Equip " + rangedWeapon.getName();
                    btn.setText(text);
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

        class RangedWeaponViewHolder extends RecyclerView.ViewHolder {
            private final Button rangedWeaponItemView;

            private RangedWeaponViewHolder(View itemView) {
                super(itemView);
                rangedWeaponItemView = itemView.findViewById(R.id.textView);
            }
        }
    }
}