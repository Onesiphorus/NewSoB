package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory;

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
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.GearTypeEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

import static java.lang.Boolean.TRUE;

public class ExamineActivity extends AppCompatActivity {
    private SobCharacter sobCharacter;
    private String type;
    private GearBase gearBase;
    private MeleeWeapon meleeWeapon;
    private RangedWeapon rangedWeapon;
    private Clothing clothing;
    private Attachment attachment;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        type = getIntent().getStringExtra("gear_type");
        location = getIntent().getStringExtra("location");

        TextView tv = findViewById(R.id.tv_gear_type);
        tv.setText(type);
        String text;
        tv = findViewById(R.id.sob_xp);
        tv.setText(String.format(sobCharacter.getExperience().toString()));
        tv = findViewById(R.id.sob_money);
        text = "$" + sobCharacter.getGold().toString();
        tv.setText(text);
        tv = findViewById(R.id.sob_darkstone);
        text = sobCharacter.getDarkStoneShards().toString() + "(" + sobCharacter.getDarkStoneCount() + ")";
        tv.setText(text);

        tv = findViewById(R.id.sob_level);
        tv.setText(String.format(sobCharacter.getLevel().toString()));
        tv = findViewById(R.id.sob_grit);
        Integer value = sobCharacter.getCharacterClass().getMaxGrit() + sobCharacter.getMaxGritBonus();
        tv.setText(value.toString());


        if (type.equals(GearTypeEnum.GEAR.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final GearBaseListAdapter adapter = new GearBaseListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            if (location.equals("inventory")) {
                adapter.setGearBase(sobCharacter.getGear());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.removeGear(gearBase);
                    sobCharacter.getTransport().getGear().add(gearBase);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            } else if (location.equals("transport")) {
                adapter.setGearBase(sobCharacter.getTransport().getGear());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.addGear(gearBase);
                    sobCharacter.getTransport().getGear().remove(gearBase);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            }
        } else if (type.equals(GearTypeEnum.CLOTHING.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final ClothingListAdapter adapter = new ClothingListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            if (location.equals("inventory")) {
                adapter.setClothing(sobCharacter.getClothing());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.removeClothing(clothing);
                    sobCharacter.getTransport().getClothing().add(clothing);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            } else if (location.equals("transport")) {
                adapter.setClothing(sobCharacter.getTransport().getClothing());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.addClothing(clothing);
                    sobCharacter.getTransport().getClothing().remove(clothing);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            }
        } else if (type.equals(GearTypeEnum.HAND_WEAPONS.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            if (location.equals("inventory")) {
                adapter.setMeleeWeapon(sobCharacter.getMeleeWeapons());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.removeMeleeWeapon(meleeWeapon);
                    sobCharacter.getTransport().getMeleeWeapons().add(meleeWeapon);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            } else if (location.equals("transport")) {
                adapter.setMeleeWeapon(sobCharacter.getTransport().getMeleeWeapons());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.addMeleeWeapon(meleeWeapon);
                    sobCharacter.getTransport().getMeleeWeapons().remove(meleeWeapon);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            }
        } else if (type.equals(GearTypeEnum.RANGED_WEAPONS.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            if (location.equals("inventory")) {
                adapter.setRangedWeapon(sobCharacter.getRangedWeapons());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.removeRangedWeapon(rangedWeapon);
                    sobCharacter.getTransport().getRangedWeapons().add(rangedWeapon);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            } else if (location.equals("transport")) {
                adapter.setRangedWeapon(sobCharacter.getTransport().getRangedWeapons());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.addRangedWeapon(rangedWeapon);
                    sobCharacter.getTransport().getRangedWeapons().remove(rangedWeapon);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            }
        } else if (type.equals(GearTypeEnum.GEAR_UPGRADES.label())) {
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final AttachmentListAdapter adapter = new AttachmentListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            if (location.equals("inventory")) {
                adapter.setAttachment(sobCharacter.getAttachments());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.removeAttachment(attachment);
                    sobCharacter.getTransport().getAttachments().add(attachment);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            } else if (location.equals("transport")) {
                adapter.setAttachment(sobCharacter.getTransport().getAttachments());
                findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                    sobCharacter.addAttachment(attachment);
                    sobCharacter.getTransport().getAttachments().remove(attachment);
                    Intent intent = new Intent(this, ExamineActivity.class);
                    intent.putExtra("serializable_object", sobCharacter);
                    intent.putExtra("location", location);
                    intent.putExtra("gear_type", type);
                    startActivity(intent);
                    finish();
                });
            }
        }

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ExamineInventoryActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        intent.putExtra("location", location);
        startActivity(intent);
        finish();
    }

    class GearBaseListAdapter extends RecyclerView.Adapter<GearBaseListAdapter.GearBaseViewHolder> {

        private final LayoutInflater mInflater;
        private List<GearBase> mGearBase;

        public GearBaseListAdapter(Context context) {
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
                String label = current.getName();
                if (current.getPersonal().equals(TRUE)) {
                    label += " (Personal)";
                }
                holder.gearBaseItemView.setText(label);
            } else {
                holder.gearBaseItemView.setText("No Gear for sale.");
            }
            holder.gearBaseItemView.setTextSize(14);
            holder.gearBaseItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gearBase = mGearBase.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Send " + gearBase.getName();
                    if (location.equals("inventory")) {
                        text += " to transport";

                    }
                    if (location.equals("transport")) {
                        text += " to inventory";
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

        public ClothingListAdapter(Context context) {
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
                String label = current.getName();
                if (current.getPersonal().equals(TRUE)) {
                    label += " (Personal)";
                }
                if (current.getEquipped().equals(TRUE)) {
                    label += " (Equipped)";
                }
                holder.clothingItemView.setTextSize(14);
                holder.clothingItemView.setText(label);
            } else {
                holder.clothingItemView.setText("No Clothing for sale.");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Send " + clothing.getName();
                    if (location.equals("inventory")) {
                        text += " to transport";

                    }
                    if (location.equals("transport")) {
                        text += " to inventory";
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
                String label = current.getName();
                if (current.getPersonal().equals(TRUE)) {
                    label += " (Personal)";
                }
                if (current.getEquipped().equals(TRUE)) {
                    label += " (Equipped)";
                }
                holder.meleeWeaponItemView.setTextSize(14);
                holder.meleeWeaponItemView.setText(label);
            } else {
                holder.meleeWeaponItemView.setText("No Melee Weapon for sale.");
            }

            holder.meleeWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meleeWeapon = mMeleeWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Send " + meleeWeapon.getName();
                    if (location.equals("inventory")) {
                        text += " to transport";

                    }
                    if (location.equals("transport")) {
                        text += " to inventory";
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
                String label = current.getName();
                if (current.getPersonal().equals(TRUE)) {
                    label += " (Personal)";
                }
                if (current.getEquipped().equals(TRUE)) {
                    label += " (Equipped)";
                }
                holder.rangedWeaponItemView.setTextSize(14);
                holder.rangedWeaponItemView.setText(label);
            } else {
                holder.rangedWeaponItemView.setText("No Ranged Weapons for sale");
            }

            holder.rangedWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rangedWeapon = mRangedWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Send " + rangedWeapon.getName();
                    if (location.equals("inventory")) {
                        text += " to transport";

                    }
                    if (location.equals("transport")) {
                        text += " to inventory";
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

        public AttachmentListAdapter(Context context) {
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
                String label = current.getName();
                if (current.getEquipped().equals(TRUE)) {
                    label += " (Equipped)";
                }
                holder.attachmentItemView.setTextSize(14);
                holder.attachmentItemView.setText(label);
            } else {
                holder.attachmentItemView.setText("No Gear for sale.");
            }

            holder.attachmentItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attachment = mAttachment.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Send " + attachment.getName();
                    if (location.equals("inventory")) {
                        text += " to transport";

                    }
                    if (location.equals("transport")) {
                        text += " to inventory";
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
}
