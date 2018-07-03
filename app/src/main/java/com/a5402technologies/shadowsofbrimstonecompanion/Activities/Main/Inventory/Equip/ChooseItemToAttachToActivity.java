package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.AttachmentViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.ClothingViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.GearBaseViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.MeleeWeaponViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.RangedWeaponViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class ChooseItemToAttachToActivity extends AppCompatActivity {

    private SobCharacter sobCharacter;
    private Attachment sentAttachment;
    private String type;
    private GearBaseViewModel mGearBaseViewModel;
    private GearBase gearBase;
    private MeleeWeaponViewModel mMeleeWeaponViewModel;
    private MeleeWeapon meleeWeapon;
    private RangedWeaponViewModel mRangedWeaponViewModel;
    private RangedWeapon rangedWeapon;
    private ClothingViewModel mClothingViewModel;
    private Clothing clothing;
    private AttachmentViewModel mAttachmentViewModel;
    private Attachment attachment;
    private String logText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        type = getIntent().getStringExtra("type");
        sentAttachment = (Attachment) getIntent().getSerializableExtra("attachment");


        if(type.equals("gear")) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final GearBaseListAdapter adapter = new GearBaseListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<GearBase> filteredList = new ArrayList<>(0);
            for(GearBase item : sobCharacter.getGear()) {
                Integer slotsUsed = 0;
                for(Attachment attachment : item.getAttachments())
                {
                    slotsUsed += attachment.getSlotsRequired();
                }
                if(item.getUpgrades() - slotsUsed > attachment.getSlotsRequired()) {
                    filteredList.add(item);
                }
            }
            adapter.setGearBase(filteredList);
        } else if(type.equals("clothing")) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final ClothingListAdapter adapter = new ClothingListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<Clothing> filteredList = new ArrayList<>(0);
            for(Clothing item : sobCharacter.getClothing()) {
                Integer slotsUsed = 0;
                for(Attachment attachment : item.getAttachments())
                {
                    slotsUsed += attachment.getSlotsRequired();
                }
                if(item.getUpgrades() - slotsUsed > attachment.getSlotsRequired()) {
                    filteredList.add(item);
                }
            }
            adapter.setClothing(filteredList);
        } else if(type.equals("melee")) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<MeleeWeapon> filteredList = new ArrayList<>(0);
            for(MeleeWeapon item : sobCharacter.getMeleeWeapons()) {
                Integer slotsUsed = 0;
                for(Attachment attachment : item.getAttachments())
                {
                    slotsUsed += attachment.getSlotsRequired();
                }
                if(item.getUpgrades() - slotsUsed > attachment.getSlotsRequired()) {
                    filteredList.add(item);
                }
            }
            adapter.setMeleeWeapon(filteredList);
        } else if(type.equals("ranged")) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            ArrayList<RangedWeapon> filteredList = new ArrayList<>(0);
            for(RangedWeapon item : sobCharacter.getRangedWeapons()) {
                Integer slotsUsed = 0;
                for(Attachment attachment : item.getAttachments())
                {
                    slotsUsed += attachment.getSlotsRequired();
                }
                if(item.getUpgrades() - slotsUsed > attachment.getSlotsRequired()) {
                    filteredList.add(item);
                }
            }
            adapter.setRangedWeapon(filteredList);
        }

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Integer ds = sobCharacter.getDarkStoneShards();
            Integer cost = sentAttachment.getRequiredDarkStoneToAttach();
            Intent intent = new Intent(this, ManageItemUpgradesActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            String toast;
            if(cost <= ds) {
                sobCharacter.setDarkStoneShards(ds - cost);
                sobCharacter.findAttachmentByName(sentAttachment.getName()).setEquipped(TRUE);
                if (rangedWeapon != null) {
                    rangedWeapon.addAttachment(sentAttachment);
                    toast = sentAttachment.getName() + " attached to " + rangedWeapon.getName();
                } else
                if (meleeWeapon != null) {
                    meleeWeapon.addAttachment(sentAttachment);
                    toast = sentAttachment.getName() + " attached to " + meleeWeapon.getName();
                } else
                if (gearBase != null) {
                    gearBase.addAttachment(sentAttachment);
                    toast = sentAttachment.getName() + " attached to " + gearBase.getName();
                } else
                if (clothing != null) {
                    clothing.addAttachment(sentAttachment);
                    toast = sentAttachment.getName() + " attached to " + clothing.getName();
                } else {
                    toast = "Nothing selected.";
                }
            } else toast = "Need more Dark Stone!";
            Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
            startActivity(intent);
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> onBackPressed());
    }

    class GearBaseListAdapter extends RecyclerView.Adapter<GearBaseListAdapter.GearBaseViewHolder> {

        private final LayoutInflater mInflater;
        private List<GearBase> mGearBase;
        private Context mContext;
        private Integer RESULT_CODE = 1;
        public GearBaseListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public GearBaseListAdapter.GearBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new GearBaseListAdapter.GearBaseViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(GearBaseListAdapter.GearBaseViewHolder holder, int position) {
            if (null != mGearBase) {
                GearBase current = mGearBase.get(position);
                holder.gearBaseItemView.setText(current.getName());
            } else {
                holder.gearBaseItemView.setText("No Gear for sale.");
            }

            holder.gearBaseItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gearBase = mGearBase.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Attach " + sentAttachment.getName() + " to " + gearBase.getName();
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
    class ClothingListAdapter extends RecyclerView.Adapter<ClothingListAdapter.ClothingViewHolder> {

        private final LayoutInflater mInflater;
        private List<Clothing> mClothing;
        private Context mContext;
        private Integer RESULT_CODE = 1;
        public ClothingListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new ClothingViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ClothingViewHolder holder, int position) {
            if (null != mClothing) {
                Clothing current = mClothing.get(position);
                holder.clothingItemView.setText(current.getName());
            } else {
                holder.clothingItemView.setText("No Clothing for sale.");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Attach " + sentAttachment.getName() + " to " + clothing.getName();
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
                holder.meleeWeaponItemView.setText(current.getName());
            } else {
                holder.meleeWeaponItemView.setText("No Melee Weapon for sale.");
            }

            holder.meleeWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meleeWeapon = mMeleeWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Attach " + sentAttachment.getName() + " to " + meleeWeapon.getName();
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
        private Context mContext;
        public RangedWeaponListAdapter(Context context) {
            mContext = context;
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
                holder.rangedWeaponItemView.setText("No Ranged Weapons for sale");
            }

            holder.rangedWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rangedWeapon = mRangedWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Attach " + sentAttachment.getName() + " to " + rangedWeapon.getName();
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
    class AttachmentListAdapter extends RecyclerView.Adapter<AttachmentListAdapter.AttachmentViewHolder> {

        private final LayoutInflater mInflater;
        private List<Attachment> mAttachment;
        private Context mContext;
        private Integer RESULT_CODE = 1;
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
                String label = current.getName() + ": $" + current.getCost();
                if(current.getDarkstoneCost() > 0) {
                    label += " + " + current.getDarkstoneCost() + " Dark Stone";
                }
                holder.attachmentItemView.setText(label);
            } else {
                holder.attachmentItemView.setText("No Gear for sale.");
            }

            holder.attachmentItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attachment = mAttachment.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = attachment.getName();
                    if(attachment.getCost() > 0) {
                        text += " $" + attachment.getCost();
                    }
                    if(attachment.getDarkstoneCost() > 0) {
                        text += " " + attachment.getDarkstoneCost() + " Dark Stone";
                    }
                    btn.setText(text);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChooseCategoryForAttachment.class);
        intent.putExtra("serializable_object", sobCharacter);
        intent.putExtra("attachment", sentAttachment);
        startActivity(intent);
        finish();
    }
}
