package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.NewItems;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.MeleeWeaponViewModel;

import java.util.List;

public class AddMeleeWeaponActivity extends AppCompatActivity {

    public static final int CLOTHING_REQUEST = 101;
    private List<MeleeWeapon> mMeleeWeapon;
    private MeleeWeaponViewModel mMeleeWeaponViewModel;
    private MeleeWeapon meleeWeapon;
    private static final int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_melee_weapon);

        SobCharacter sobCharacter = (SobCharacter)getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AddMeleeWeaponActivity.MeleeWeaponListAdapter adapter = new AddMeleeWeaponActivity.MeleeWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMeleeWeaponViewModel = ViewModelProviders.of(this).get(MeleeWeaponViewModel.class);

        mMeleeWeaponViewModel.getAllMeleeWeapons().observe(this, new Observer<List<MeleeWeapon>>() {
            @Override
            public void onChanged(@Nullable List<MeleeWeapon> meleeWeapon) {
                adapter.setMeleeWeapon(meleeWeapon);
            }
        });

        findViewById(R.id.melee_weapon_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, DebugActivity.class    );
            /*
            if(meleeWeapon != null) {
                intent.putExtra("serializable_object", meleeWeapon);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(meleeWeapon != null) {
                sobCharacter.addMeleeWeapon(meleeWeapon);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, meleeWeapon.getName() + "added to inventory.", Toast.LENGTH_LONG);
            }
            startActivity(intent);
            finish();
        });
    }

    class MeleeWeaponListAdapter extends RecyclerView.Adapter<AddMeleeWeaponActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

        class MeleeWeaponViewHolder extends RecyclerView.ViewHolder {
            private final TextView meleeWeaponItemView;

            private MeleeWeaponViewHolder(View itemView) {
                super(itemView);
                meleeWeaponItemView = itemView.findViewById(R.id.textView);

            }
        }

        private final LayoutInflater mInflater;
        private List<MeleeWeapon> mMeleeWeapon;
        private Context mContext;
        private Integer RESULT_CODE = 1;

        public MeleeWeaponListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public AddMeleeWeaponActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new AddMeleeWeaponActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AddMeleeWeaponActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder holder, int position) {
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
}

