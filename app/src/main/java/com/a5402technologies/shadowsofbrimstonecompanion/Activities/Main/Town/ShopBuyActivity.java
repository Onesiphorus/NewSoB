package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Town;

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
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.AttachmentViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.ClothingViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.GearBaseViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.MeleeWeaponViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.RangedWeaponViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShopBuyActivity extends AppCompatActivity {
    private SobCharacter sobCharacter;
    private String shop;
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
        setContentView(R.layout.activity_shop_buy);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        shop = getIntent().getStringExtra("shop_type");
        type = getIntent().getStringExtra("gear_type");

        TextView tv = findViewById(R.id.tv_shop_name);
        tv.setText(shop);
        tv = findViewById(R.id.tv_gear_type);
        tv.setText(type);
        String text;
        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_darkstone);
        tv.setText(String.format(sobCharacter.getDarkStoneShards().toString()));

        tv = findViewById(R.id.sob_level);
        tv.setText(String.format(sobCharacter.getLevel().toString()));


        if(type.equals(GearTypeEnum.GEAR.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final GearBaseListAdapter adapter = new GearBaseListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mGearBaseViewModel = ViewModelProviders.of(this).get(GearBaseViewModel.class);

            mGearBaseViewModel.getAllGear().observe(this, new Observer<List<GearBase>>() {
                @Override
                public void onChanged(@Nullable List<GearBase> gearBase) {
                    ArrayList<GearBase> filteredList = new ArrayList<>(0);
                    for(GearBase item : gearBase) {
                        if(item.getShop().equals(shop)) filteredList.add(item);
                    }
                    adapter.setGearBase(filteredList);
                }
            });
        } else if(type.equals(GearTypeEnum.CLOTHING.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final ClothingListAdapter adapter = new ClothingListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mClothingViewModel = ViewModelProviders.of(this).get(ClothingViewModel.class);

            mClothingViewModel.getAllClothing().observe(this, new Observer<List<Clothing>>() {
                @Override
                public void onChanged(@Nullable List<Clothing> clothing) {
                    ArrayList<Clothing> filteredList = new ArrayList<>(0);
                    for(Clothing item : clothing) {
                        if(item.getShop().equals(shop)) filteredList.add(item);
                    }
                    adapter.setClothing(filteredList);
                }
            });
        } else if(type.equals(GearTypeEnum.HAND_WEAPONS.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mMeleeWeaponViewModel = ViewModelProviders.of(this).get(MeleeWeaponViewModel.class);

            mMeleeWeaponViewModel.getAllMeleeWeapons().observe(this, new Observer<List<MeleeWeapon>>() {
                @Override
                public void onChanged(@Nullable List<MeleeWeapon> meleeWeapon) {
                    ArrayList<MeleeWeapon> filteredList = new ArrayList<>(0);
                    for(MeleeWeapon item : meleeWeapon) {
                        if(item.getShop().equals(shop)) filteredList.add(item);
                    }
                    adapter.setMeleeWeapon(filteredList);
                }
            });
        } else if(type.equals(GearTypeEnum.RANGED_WEAPONS.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mRangedWeaponViewModel = ViewModelProviders.of(this).get(RangedWeaponViewModel.class);

            mRangedWeaponViewModel.getAllRangedWeapons().observe(this, new Observer<List<RangedWeapon>>() {
                @Override
                public void onChanged(@Nullable List<RangedWeapon> rangedWeapon) {
                    ArrayList<RangedWeapon> filteredList = new ArrayList<>(0);
                    for(RangedWeapon item : rangedWeapon) {
                        if(item.getShop().equals(shop)) filteredList.add(item);
                    }
                    adapter.setRangedWeapon(filteredList);
                }
            });
        }else if(type.equals(GearTypeEnum.GEAR_UPGRADES.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final AttachmentListAdapter adapter = new AttachmentListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mAttachmentViewModel = ViewModelProviders.of(this).get(AttachmentViewModel.class);

            mAttachmentViewModel.getAllAttachment().observe(this, new Observer<List<Attachment>>() {
                @Override
                public void onChanged(@Nullable List<Attachment> attachments) {
                    ArrayList<Attachment> filteredList = new ArrayList<>(0);
                    for(Attachment item : attachments) {
                        if(item.getShop().equals(shop)) filteredList.add(item);
                    }
                    adapter.setAttachment(filteredList);
                }
            });
        }

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShopBuyActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            intent.putExtra("gear_type", type);
            intent.putExtra("shop_type", shop);
            String toast;
            if (rangedWeapon != null) {
                if(sobCharacter.getGold() >= rangedWeapon.getCost() && sobCharacter.getDarkStoneShards() >= rangedWeapon.getDarkstoneCost()) {
                    sobCharacter.removeGold(rangedWeapon.getCost());
                    sobCharacter.removeDarkstoneShards(rangedWeapon.getDarkstoneCost());
                    sobCharacter.addRangedWeapon(rangedWeapon);
                    Log.e("SUCCESS: ", rangedWeapon.getName() + " purchased and added to inventory.");
                    startActivity(intent);
                    toast = rangedWeapon.getName() + " purchased and added to inventory.";
                    finish();
                } else {
                    toast = "Can't afford to buy " + rangedWeapon.getName();
                }
            } else
            if (meleeWeapon != null) {
                if(sobCharacter.getGold() >= meleeWeapon.getCost() && sobCharacter.getDarkStoneShards() >= meleeWeapon.getDarkstoneCost()) {
                    sobCharacter.removeGold(meleeWeapon.getCost());
                    sobCharacter.removeDarkstoneShards(meleeWeapon.getDarkstoneCost());
                    sobCharacter.addMeleeWeapon(meleeWeapon);
                    Log.e("SUCCESS: ", meleeWeapon.getName() + " purchased and added to inventory.");
                    toast = meleeWeapon.getName() + " purchased and added to inventory.";
                    startActivity(intent);
                    finish();
                } else {
                    toast = "Can't afford to buy " + meleeWeapon.getName();
                }
            } else
            if (gearBase != null) {
                if(sobCharacter.getGold() >= gearBase.getCost() && sobCharacter.getDarkStoneShards() >= gearBase.getDarkstoneCost()) {
                    sobCharacter.removeGold(gearBase.getCost());
                    sobCharacter.removeDarkstoneShards(gearBase.getDarkstoneCost());
                    sobCharacter.addGear(gearBase);
                    Log.e("SUCCESS: ", gearBase.getName() + " purchased and added to inventory.");
                    toast = gearBase.getName() + " purchased and added to inventory.";
                    startActivity(intent);
                    finish();
                } else {
                    toast = "Can't afford to buy " + gearBase.getName();
                }
            } else
            if (clothing != null) {
                if(sobCharacter.getGold() >= clothing.getCost() && sobCharacter.getDarkStoneShards() >= clothing.getDarkstoneCost()) {
                    sobCharacter.removeGold(clothing.getCost());
                    sobCharacter.removeDarkstoneShards(clothing.getDarkstoneCost());
                    sobCharacter.addClothing(clothing);
                    Log.e("SUCCESS: ", clothing.getName() + " purchased and added to inventory.");
                    toast = clothing.getName() + " purchased and added to inventory.";
                    startActivity(intent);
                    finish();
                } else {
                    toast = "Can't afford to buy " + clothing.getName();
                }
            } else
            if (attachment != null) {
                if(sobCharacter.getGold() >= attachment.getCost() && sobCharacter.getDarkStoneShards() >= attachment.getDarkstoneCost()) {
                    sobCharacter.removeGold(attachment.getCost());
                    sobCharacter.removeDarkstoneShards(attachment.getDarkstoneCost());
                    sobCharacter.addAttachment(attachment);
                    Log.e("SUCCESS: ", attachment.getName() + " purchased and added to inventory.");
                    toast = attachment.getName() + " purchased and added to inventory.";
                    startActivity(intent);
                    finish();
                } else {
                    toast = "Can't afford to buy " + attachment.getName();
                }
            } else {
                toast = "Nothing selected.";
            }
            Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
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
                String label = current.getName() + ": $" + current.getCost();
                if(current.getDarkstoneCost() > 0) {
                    label += " + " + current.getDarkstoneCost() + " Dark Stone";
                }
                holder.gearBaseItemView.setText(label);
            } else {
                holder.gearBaseItemView.setText("No Gear for sale.");
            }

            holder.gearBaseItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gearBase = mGearBase.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Buy " + gearBase.getName() + " for";
                    if(gearBase.getCost() > 0) {
                        text += " $" + gearBase.getCost();
                    }
                    if(gearBase.getDarkstoneCost() > 0) {
                        text += " " + gearBase.getDarkstoneCost() + " Dark Stone";
                    }
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
                String label = current.getName() + ": $" + current.getCost();
                if(current.getDarkstoneCost() > 0) {
                    label += " + " + current.getDarkstoneCost() + " Dark Stone";
                }
                holder.clothingItemView.setText(label);
            } else {
                holder.clothingItemView.setText("No Clothing for sale.");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Buy " + clothing.getName() + " for";
                    if(clothing.getCost() > 0) {
                        text += " $" + clothing.getCost();
                    }
                    if(clothing.getDarkstoneCost() > 0) {
                        text += " " + clothing.getDarkstoneCost() + " Dark Stone";
                    }
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
                String label = current.getName() + ": $" + current.getCost();
                if(current.getDarkstoneCost() > 0) {
                    label += " + " + current.getDarkstoneCost() + " Dark Stone";
                }
                holder.meleeWeaponItemView.setText(label);
            } else {
                holder.meleeWeaponItemView.setText("No Melee Weapon for sale.");
            }

            holder.meleeWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meleeWeapon = mMeleeWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Buy " + meleeWeapon.getName() + " for";
                    if(meleeWeapon.getCost() > 0) {
                        text += " $" + meleeWeapon.getCost();
                    }
                    if(meleeWeapon.getDarkstoneCost() > 0) {
                        text += " " + meleeWeapon.getDarkstoneCost() + " Dark Stone";
                    }
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
                String label = current.getName() + ": $" + current.getCost();
                if(current.getDarkstoneCost() > 0) {
                    label += " + " + current.getDarkstoneCost() + " Dark Stone";
                }
                holder.rangedWeaponItemView.setText(label);
            } else {
                holder.rangedWeaponItemView.setText("No Ranged Weapons for sale");
            }

            holder.rangedWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rangedWeapon = mRangedWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Buy " + rangedWeapon.getName() + " for";
                    if(rangedWeapon.getCost() > 0) {
                        text += " $" + rangedWeapon.getCost();
                    }
                    if(rangedWeapon.getDarkstoneCost() > 0) {
                        text += " " + rangedWeapon.getDarkstoneCost() + " Dark Stone";
                    }
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
                    String text = "Buy " + attachment.getName() + " for";
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
        Intent intent = new Intent(this, VisitShopActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        intent.putExtra("shop_type", shop);
        startActivity(intent);
        finish();
    }
}
