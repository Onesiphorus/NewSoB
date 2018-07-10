package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.AttachmentViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.ClothingViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.GearBaseViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.MeleeWeaponViewModel;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.RangedWeaponViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AddGearBaseActivity extends AppCompatActivity {

    private GearBaseViewModel mGearBaseViewModel;
    private GearBase gearBase;
    private String gearType;
    private AttachmentViewModel mAttachmentViewModel;
    private Attachment attachment;
    private ClothingViewModel mClothingViewModel;
    private Clothing clothing;
    private MeleeWeaponViewModel mMeleeWeaponViewModel;
    private MeleeWeapon meleeWeapon;
    private RangedWeaponViewModel mRangedWeaponViewModel;
    private RangedWeapon rangedWeapon;
    private SobCharacter sobCharacter;
    private String cardType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear_base);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        gearType = getIntent().getStringExtra("gear_type");
        cardType = getIntent().getStringExtra("card_type");


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        if (gearType.equals(GearTypeEnum.GEAR.label())) {
            final GearBaseListAdapter adapter = new GearBaseListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mGearBaseViewModel = ViewModelProviders.of(this).get(GearBaseViewModel.class);
            mGearBaseViewModel.getAllGear().observe(this, new Observer<List<GearBase>>() {
                @Override
                public void onChanged(@Nullable List<GearBase> gearBase) {
                    ArrayList<GearBase> filtered = new ArrayList<>(0);
                    for(GearBase item : gearBase) {
                        if(cardType.equals("mine")) {
                            if (item.getArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("targa")) {
                            if (item.getTargaArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("jargono")) {
                            if (item.getJargonoArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("derelict")) {
                            if (item.getDerelictArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("trederra")) {
                            if (item.getTrederraArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("cynder")) {
                            if (item.getCynderArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("personal")) {
                            if (item.getPersonal().equals(TRUE)) filtered.add(item);
                        } else {
                            if (item.getArtifact().equals(FALSE) && item.getTargaArtifact().equals(FALSE)
                                    && item.getJargonoArtifact().equals(FALSE) && item.getDerelictArtifact().equals(FALSE)
                                    && item.getTrederraArtifact().equals(FALSE) && item.getCynderArtifact().equals(FALSE)
                                    && item.getPersonal().equals(FALSE)) {
                                filtered.add(item);
                            }
                        }
                    }
                    adapter.setGearBase(filtered);
                }
            });
            findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, AddGearBaseActivity.class);
                intent.putExtra("gear_type", gearType);
                if (gearBase != null) {
                    sobCharacter.addGear(gearBase);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, gearBase.getName() + " added to inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.GEAR_UPGRADES.label())) {
            final AttachmentListAdapter adapter = new AttachmentListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mAttachmentViewModel = ViewModelProviders.of(this).get(AttachmentViewModel.class);
            mAttachmentViewModel.getAllAttachment().observe(this, new Observer<List<Attachment>>() {
                @Override
                public void onChanged(@Nullable List<Attachment> list) {
                    ArrayList<Attachment> filtered = new ArrayList<>(0);
                    for(Attachment item : list) {
                        if(cardType.equals("mine")) {
                            if (item.getArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("targa")) {
                            if (item.getTargaArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("jargono")) {
                            if (item.getJargonoArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("derelict")) {
                            if (item.getDerelictArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("trederra")) {
                            if (item.getTrederraArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("cynder")) {
                            if (item.getCynderArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("personal")) {
                        } else {
                            if (item.getArtifact().equals(FALSE) && item.getTargaArtifact().equals(FALSE)
                                    && item.getJargonoArtifact().equals(FALSE) && item.getDerelictArtifact().equals(FALSE)
                                    && item.getTrederraArtifact().equals(FALSE) && item.getCynderArtifact().equals(FALSE)) {
                                filtered.add(item);
                            }
                        }
                    }
                    adapter.setAttachment(filtered);
                }
            });
            findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, AddGearBaseActivity.class);
                intent.putExtra("gear_type", gearType);
                if (attachment != null) {
                    sobCharacter.addAttachment(attachment);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, attachment.getName() + " added to inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.CLOTHING.label())) {
            final ClothingListAdapter adapter = new ClothingListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mClothingViewModel = ViewModelProviders.of(this).get(ClothingViewModel.class);
            mClothingViewModel.getAllClothing().observe(this, new Observer<List<Clothing>>() {
                @Override
                public void onChanged(@Nullable List<Clothing> list) {
                    ArrayList<Clothing> filtered = new ArrayList<>(0);
                    for(Clothing item : list) {
                        if(cardType.equals("mine")) {
                            if (item.getArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("targa")) {
                            if (item.getTargaArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("jargono")) {
                            if (item.getJargonoArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("derelict")) {
                            if (item.getDerelictArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("trederra")) {
                            if (item.getTrederraArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("cynder")) {
                            if (item.getCynderArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("personal")) {
                            if (item.getPersonal().equals(TRUE)) filtered.add(item);
                        } else {
                            if (item.getArtifact().equals(FALSE) && item.getTargaArtifact().equals(FALSE)
                                    && item.getJargonoArtifact().equals(FALSE) && item.getDerelictArtifact().equals(FALSE)
                                    && item.getTrederraArtifact().equals(FALSE) && item.getCynderArtifact().equals(FALSE)
                                    && item.getPersonal().equals(FALSE)) {
                                filtered.add(item);
                            }
                        }
                    }
                    adapter.setClothing(filtered);
                }
            });
            findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, AddGearBaseActivity.class);
                intent.putExtra("gear_type", gearType);
                if (clothing != null) {
                    sobCharacter.addClothing(clothing);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, clothing.getName() + " added to inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.HAND_WEAPONS.label())) {
            final MeleeWeaponListAdapter adapter = new MeleeWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mMeleeWeaponViewModel = ViewModelProviders.of(this).get(MeleeWeaponViewModel.class);
            mMeleeWeaponViewModel.getAllMeleeWeapons().observe(this, new Observer<List<MeleeWeapon>>() {
                @Override
                public void onChanged(@Nullable List<MeleeWeapon> list) {
                    ArrayList<MeleeWeapon> filtered = new ArrayList<>(0);
                    for(MeleeWeapon item : list) {
                        if(cardType.equals("mine")) {
                            if (item.getArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("targa")) {
                            if (item.getTargaArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("jargono")) {
                            if (item.getJargonoArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("derelict")) {
                            if (item.getDerelictArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("trederra")) {
                            if (item.getTrederraArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("cynder")) {
                            if (item.getCynderArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("personal")) {
                            if (item.getPersonal().equals(TRUE)) filtered.add(item);
                        } else {
                            if (item.getArtifact().equals(FALSE) && item.getTargaArtifact().equals(FALSE)
                                    && item.getJargonoArtifact().equals(FALSE) && item.getDerelictArtifact().equals(FALSE)
                                    && item.getTrederraArtifact().equals(FALSE) && item.getCynderArtifact().equals(FALSE)
                                    && item.getPersonal().equals(FALSE)) {
                                filtered.add(item);
                            }
                        }
                    }
                    adapter.setMeleeWeapon(filtered);
                }
            });
            findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, AddGearBaseActivity.class);
                intent.putExtra("gear_type", gearType);
                if (meleeWeapon != null) {
                    sobCharacter.addMeleeWeapon(meleeWeapon);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, meleeWeapon.getName() + " added to inventory.", Toast.LENGTH_LONG).show();
                }
                startActivity(intent);
                finish();
            });
        }
        if (gearType.equals(GearTypeEnum.RANGED_WEAPONS.label())) {
            final RangedWeaponListAdapter adapter = new RangedWeaponListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            mRangedWeaponViewModel = ViewModelProviders.of(this).get(RangedWeaponViewModel.class);
            mRangedWeaponViewModel.getAllRangedWeapons().observe(this, new Observer<List<RangedWeapon>>() {
                @Override
                public void onChanged(@Nullable List<RangedWeapon> list) {
                    ArrayList<RangedWeapon> filtered = new ArrayList<>(0);
                    for(RangedWeapon item : list) {
                        if(cardType.equals("mine")) {
                            if (item.getArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("targa")) {
                            if (item.getTargaArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("jargono")) {
                            if (item.getJargonoArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("derelict")) {
                            if (item.getDerelictArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("trederra")) {
                            if (item.getTrederraArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("cynder")) {
                            if (item.getCynderArtifact().equals(TRUE)) filtered.add(item);
                        } else if (cardType.equals("personal")) {
                            if (item.getPersonal().equals(TRUE)) filtered.add(item);
                        } else {
                            if (item.getArtifact().equals(FALSE) && item.getTargaArtifact().equals(FALSE)
                                    && item.getJargonoArtifact().equals(FALSE) && item.getDerelictArtifact().equals(FALSE)
                                    && item.getTrederraArtifact().equals(FALSE) && item.getCynderArtifact().equals(FALSE)
                                    && item.getPersonal().equals(FALSE)) {
                                filtered.add(item);
                            }
                        }
                    }
                    adapter.setRangedWeapon(filtered);
                }
            });
            findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
                Intent intent = new Intent(this, AddGearBaseActivity.class);
                intent.putExtra("gear_type", gearType);
                if (rangedWeapon != null) {
                    sobCharacter.addRangedWeapon(rangedWeapon);
                    intent.putExtra("serializable_object", sobCharacter);
                    Toast.makeText(this, rangedWeapon.getName() + " added to inventory.", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this, FoundGearActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        intent.putExtra("card_type", cardType);
        startActivity(intent);
        finish();
    }

    class GearBaseListAdapter extends RecyclerView.Adapter<AddGearBaseActivity.GearBaseListAdapter.GearBaseViewHolder> {

        private final LayoutInflater mInflater;
        private List<GearBase> mGearBase;
        private Context mContext;
        private Integer RESULT_CODE = 1;

        public GearBaseListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public AddGearBaseActivity.GearBaseListAdapter.GearBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new AddGearBaseActivity.GearBaseListAdapter.GearBaseViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AddGearBaseActivity.GearBaseListAdapter.GearBaseViewHolder holder, int position) {
            if (null != mGearBase) {
                GearBase current = mGearBase.get(position);
                holder.gearBaseItemView.setText(current.getName());
            } else {
                holder.gearBaseItemView.setText("No GearBase");
            }

            holder.gearBaseItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gearBase = mGearBase.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Add " + gearBase.getName() + " to inventory";
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
                String text = current.getName();
                holder.attachmentItemView.setText(text);
            } else {
                holder.attachmentItemView.setText("No Attachment");
            }

            holder.attachmentItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attachment = mAttachment.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Add " + attachment.getName() + " to inventory";
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
                String text = current.getName();
                holder.clothingItemView.setText(text);
            } else {
                holder.clothingItemView.setText("No Clothing");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Add " + clothing.getName() + " to inventory";
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
                String text = current.getName();
                holder.meleeWeaponItemView.setText(text);
            } else {
                holder.meleeWeaponItemView.setText("No Melee");
            }

            holder.meleeWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    meleeWeapon = mMeleeWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Add " + meleeWeapon.getName() + " to inventory";
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
                String text = current.getName();
                holder.rangedWeaponItemView.setText(text);
            } else {
                holder.rangedWeaponItemView.setText("No Ranged");
            }

            holder.rangedWeaponItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rangedWeapon = mRangedWeapon.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Add " + rangedWeapon.getName() + " to inventory";
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

