package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip;

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

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ClothingSlotsEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class ChangeClothingActivity extends AppCompatActivity {
    Clothing clothing;
    SobCharacter sobCharacter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equip);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        String name = getIntent().getStringExtra("name");
        String type = getIntent().getStringExtra("type");
        ArrayList<Clothing> ClothingOptions = new ArrayList<>(0);
        for(Clothing clothing : sobCharacter.getClothing()) {
            if(((clothing.getFace().equals(TRUE) && type.equals(ClothingSlotsEnum.FACE.label()))
                    || (clothing.getBelt().equals(TRUE) && type.equals(ClothingSlotsEnum.BELT.label()))
                    || (clothing.getBoots().equals(TRUE) && type.equals(ClothingSlotsEnum.BOOTS.label()))
                    || (clothing.getHat().equals(TRUE) && type.equals(ClothingSlotsEnum.HAT.label()))
                    || (clothing.getCoat().equals(TRUE) && type.equals(ClothingSlotsEnum.COAT.label()))
                    || (clothing.getTorso().equals(TRUE) && type.equals(ClothingSlotsEnum.TORSO.label()))
                    || (clothing.getPants().equals(TRUE) && type.equals(ClothingSlotsEnum.PANTS.label()))
                    || (clothing.getGloves().equals(TRUE) && type.equals(ClothingSlotsEnum.GLOVES.label()))
                    || (clothing.getShoulders().equals(TRUE) && type.equals(ClothingSlotsEnum.SHOULDERS.label())))
                    && clothing.getEquipped().equals(Boolean.FALSE)) {
                ClothingOptions.add(clothing);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview_equip);
        final ChangeClothingActivity.ClothingListAdapter adapter = new ChangeClothingActivity.ClothingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setClothing(ClothingOptions);

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if(null != clothing) {
                if(!(name.isEmpty())) {
                    sobCharacter.unequipClothing(sobCharacter.findClothingByName(name));
                }
                sobCharacter.equipClothing(clothing);
                Intent intent = new Intent(this, ChangeLoadoutActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "No weapon selected, click cancel to return.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ChangeLoadoutActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });
    }

    class ClothingListAdapter extends RecyclerView.Adapter<ChangeClothingActivity.ClothingListAdapter.ClothingViewHolder> {

        class ClothingViewHolder extends RecyclerView.ViewHolder {
            private final Button clothingItemView;

            private ClothingViewHolder(View itemView) {
                super(itemView);
                clothingItemView = itemView.findViewById(R.id.textView);
            }
        }

        private final LayoutInflater mInflater;
        private List<Clothing> mClothing;

        public ClothingListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public ChangeClothingActivity.ClothingListAdapter.ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new ChangeClothingActivity.ClothingListAdapter.ClothingViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ChangeClothingActivity.ClothingListAdapter.ClothingViewHolder holder, int position) {
            if (null != mClothing) {
                Clothing current = mClothing.get(position);
                holder.clothingItemView.setText(current.getName());
            } else {
                holder.clothingItemView.setText("No Clothing");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
                }
            });
        }

        public void setClothing(List<Clothing> clothing) {
            mClothing = clothing;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mClothing) return mClothing.size();
            else return 0;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChangeLoadoutActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}