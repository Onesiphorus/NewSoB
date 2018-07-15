package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.RemoveItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.GearTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class RemoveGearActivity extends AppCompatActivity {

    SobCharacter sobCharacter;
    GearBase gearBase;
    Attachment attachment;
    MeleeWeapon meleeWeapon;
    RangedWeapon rangedWeapon;
    Clothing clothing;
    String gearType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        gearType = getIntent().getStringExtra("gear_type");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        if (gearType.equals(GearTypeEnum.GEAR.label())) {
            final RemoveGearActivity.GearBaseListAdapter adapter = new RemoveGearActivity.GearBaseListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<GearBase> gearList = new ArrayList<>(0);
            for (GearBase gear : sobCharacter.getGear()) {
                if (gear.getPersonal().equals(FALSE)) gearList.add(gear);
            }
            adapter.setGearBase(gearList);

            findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (gearBase != null) {
                    sobCharacter.removeGear(gearBase);
                    sobCharacter.addGold(gearBase.getSell());
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, gearBase.getName() + " sold for $" + gearBase.getSell() + ".", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });

            findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (gearBase != null) {
                    sobCharacter.removeGear(gearBase);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, gearBase.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.GEAR_UPGRADES.label())) {
            final AttachmentListAdapter adapter = new AttachmentListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<Attachment> list = new ArrayList<>(0);
            list.addAll(sobCharacter.getAttachments());
            adapter.setAttachment(list);

            findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (attachment != null) {
                    sobCharacter.removeAttachment(attachment);
                    sobCharacter.addGold(attachment.getSell());
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, attachment.getName() + " sold for $" + attachment.getSell() + ".", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });

            findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (attachment != null) {
                    sobCharacter.removeAttachment(attachment);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, attachment.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.CLOTHING.label())) {
            final ClothingListAdapter adapter = new ClothingListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<Clothing> gearList = new ArrayList<>(0);
            for (Clothing clothing : sobCharacter.getClothing()) {
                if (clothing.getPersonal().equals(FALSE)) gearList.add(clothing);
            }
            adapter.setClothing(gearList);

            findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (clothing != null) {
                    sobCharacter.removeClothing(clothing);
                    sobCharacter.addGold(clothing.getSell());
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, clothing.getName() + " sold for $" + clothing.getSell() + ".", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });

            findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (clothing != null) {
                    sobCharacter.removeClothing(clothing);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, clothing.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.HAND_WEAPONS.label())) {
            final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<MeleeWeapon> gearList = new ArrayList<>(0);
            for (MeleeWeapon meleeWeapon : sobCharacter.getMeleeWeapons()) {
                if (meleeWeapon.getPersonal().equals(FALSE)) gearList.add(meleeWeapon);
            }
            adapter.setMeleeWeapon(gearList);

            findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (meleeWeapon != null) {
                    sobCharacter.removeMeleeWeapon(meleeWeapon);
                    sobCharacter.addGold(meleeWeapon.getSell());
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, meleeWeapon.getName() + " sold for $" + meleeWeapon.getSell() + ".", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });

            findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (meleeWeapon != null) {
                    sobCharacter.removeMeleeWeapon(meleeWeapon);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, meleeWeapon.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.RANGED_WEAPONS.label())) {
            final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<RangedWeapon> gearList = new ArrayList<>(0);
            for (RangedWeapon gear : sobCharacter.getRangedWeapons()) {
                if (gear.getPersonal().equals(FALSE)) gearList.add(gear);
            }
            adapter.setRangedWeapon(gearList);

            findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (rangedWeapon != null) {
                    sobCharacter.removeRangedWeapon(rangedWeapon);
                    sobCharacter.addGold(rangedWeapon.getSell());
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, rangedWeapon.getName() + " sold for $" + rangedWeapon.getSell() + ".", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });

            findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, RemoveGearActivity.class);
                intent.putExtra("gear_type", gearType);
                if (gearBase != null) {
                    sobCharacter.removeRangedWeapon(rangedWeapon);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, rangedWeapon.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChooseTypeToRemoveActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

    class GearBaseListAdapter extends RecyclerView.Adapter<RemoveGearActivity.GearBaseListAdapter.GearBaseViewHolder> {

        private final LayoutInflater mInflater;
        private List<GearBase> mGearBase;

        public GearBaseListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RemoveGearActivity.GearBaseListAdapter.GearBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RemoveGearActivity.GearBaseListAdapter.GearBaseViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RemoveGearActivity.GearBaseListAdapter.GearBaseViewHolder holder, int position) {
            if (null != mGearBase) {
                GearBase current = mGearBase.get(position);
                String text = current.getName() + ": $" + current.getSell();
                holder.gearBaseItemView.setText(text);
            } else {
                holder.gearBaseItemView.setText("No Gear");
            }

            holder.gearBaseItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gearBase = mGearBase.get(position);
                    Button btn = findViewById(R.id.btn_sell);
                    String text = "Sell " + gearBase.getName() + " for $" + gearBase.getSell();
                    btn.setText(text);
                }
            });
        }

        public void setGearBase(List<GearBase> gearBase) {
            mGearBase = gearBase;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mGearBase) return mGearBase.size();
            else return 0;
        }

        class GearBaseViewHolder extends RecyclerView.ViewHolder {
            private final TextView gearBaseItemView;

            private GearBaseViewHolder(View itemView) {
                super(itemView);
                gearBaseItemView = itemView.findViewById(R.id.textView);

            }
        }
    }

    class AttachmentListAdapter extends RecyclerView.Adapter<AttachmentListAdapter.AttachmentViewHolder> {

        private final LayoutInflater mInflater;
        private List<Attachment> mAttachment;
        private Context mContext;

        public AttachmentListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public AttachmentListAdapter.AttachmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new AttachmentListAdapter.AttachmentViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AttachmentListAdapter.AttachmentViewHolder holder, int position) {
            if (null != mAttachment) {
                Attachment current = mAttachment.get(position);
                String text = current.getName() + ": $" + current.getSell();
                holder.attachmentItemView.setText(text);
            } else {
                holder.attachmentItemView.setText("No Attachment");
            }

            holder.attachmentItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attachment = mAttachment.get(position);
                }
            });
        }

        public void setAttachment(List<Attachment> attachment) {
            mAttachment = attachment;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mAttachment) return mAttachment.size();
            else return 0;
        }

        class AttachmentViewHolder extends RecyclerView.ViewHolder {
            private final TextView attachmentItemView;

            private AttachmentViewHolder(View itemView) {
                super(itemView);
                attachmentItemView = itemView.findViewById(R.id.textView);

            }
        }
    }

    class ClothingListAdapter extends RecyclerView.Adapter<ClothingListAdapter.ClothingViewHolder> {

        private final LayoutInflater mInflater;
        private List<Clothing> mClothing;
        private Context mContext;

        public ClothingListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public ClothingListAdapter.ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new ClothingListAdapter.ClothingViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ClothingListAdapter.ClothingViewHolder holder, int position) {
            if (null != mClothing) {
                Clothing current = mClothing.get(position);
                String text = current.getName() + ": $" + current.getSell();
                holder.clothingItemView.setText(text);
            } else {
                holder.clothingItemView.setText("No Clothing");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
                    Button btn = findViewById(R.id.btn_sell);
                    String text = "Sell " + clothing.getName() + " for $" + clothing.getSell();
                    btn.setText(text);
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

        class ClothingViewHolder extends RecyclerView.ViewHolder {
            private final TextView clothingItemView;

            private ClothingViewHolder(View itemView) {
                super(itemView);
                clothingItemView = itemView.findViewById(R.id.textView);

            }
        }
    }

    class MeleeWeaponListAdapter extends RecyclerView.Adapter<MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

        private final LayoutInflater mInflater;
        private List<MeleeWeapon> mMeleeWeapon;
        private Context mContext;
        private Integer RESULT_CODE = 1;

        public MeleeWeaponListAdapter(Context context) {
            mContext = context;
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
                String text = current.getName() + ": $" + current.getSell();
                holder.meleeWeaponItemView.setText(text);
            } else {
                holder.meleeWeaponItemView.setText("No Melee");
            }

            holder.meleeWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meleeWeapon = mMeleeWeapon.get(position);
                    Button btn = findViewById(R.id.btn_sell);
                    String text = "Sell " + meleeWeapon.getName() + " for $" + meleeWeapon.getSell();
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
            private final TextView meleeWeaponItemView;

            private MeleeWeaponViewHolder(View itemView) {
                super(itemView);
                meleeWeaponItemView = itemView.findViewById(R.id.textView);

            }
        }
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
                String text = current.getName() + ": $" + current.getSell();
                holder.rangedWeaponItemView.setText(text);
            } else {
                holder.rangedWeaponItemView.setText("No Ranged");
            }

            holder.rangedWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rangedWeapon = mRangedWeapon.get(position);
                    Button btn = findViewById(R.id.btn_sell);
                    String text = "Sell " + rangedWeapon.getName() + " for $" + rangedWeapon.getSell();
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
            private final TextView rangedWeaponItemView;

            private RangedWeaponViewHolder(View itemView) {
                super(itemView);
                rangedWeaponItemView = itemView.findViewById(R.id.textView);

            }
        }
    }

}
