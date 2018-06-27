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

import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.RangedWeaponViewModel;

import java.util.List;

public class AddRangedWeaponActivity extends AppCompatActivity {

    public static final int CLOTHING_REQUEST = 101;
    private List<RangedWeapon> mRangedWeapon;
    private RangedWeaponViewModel mRangedWeaponViewModel;
    private RangedWeapon rangedWeapon;
    private static final int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranged_weapon);

        SobCharacter sobCharacter = (SobCharacter)getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AddRangedWeaponActivity.RangedWeaponListAdapter adapter = new AddRangedWeaponActivity.RangedWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRangedWeaponViewModel = ViewModelProviders.of(this).get(RangedWeaponViewModel.class);

        mRangedWeaponViewModel.getAllRangedWeapons().observe(this, new Observer<List<RangedWeapon>>() {
            @Override
            public void onChanged(@Nullable List<RangedWeapon> rangedWeapon) {
                adapter.setRangedWeapon(rangedWeapon);
            }
        });

        findViewById(R.id.ranged_weapon_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, DebugActivity.class    );
            /*
            if(rangedWeapon != null) {
                intent.putExtra("serializable_object", rangedWeapon);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(rangedWeapon != null) {
                sobCharacter.addRangedWeapon(rangedWeapon);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, rangedWeapon.getName() + "added to inventory.", Toast.LENGTH_LONG);
            }
            startActivity(intent);

        });
    }

    class RangedWeaponListAdapter extends RecyclerView.Adapter<AddRangedWeaponActivity.RangedWeaponListAdapter.RangedWeaponViewHolder> {

        class RangedWeaponViewHolder extends RecyclerView.ViewHolder {
            private final TextView rangedWeaponItemView;

            private RangedWeaponViewHolder(View itemView) {
                super(itemView);
                rangedWeaponItemView = itemView.findViewById(R.id.textView);

            }
        }

        private final LayoutInflater mInflater;
        private List<RangedWeapon> mRangedWeapon;
        private Context mContext;
        private Integer RESULT_CODE = 1;

        public RangedWeaponListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public AddRangedWeaponActivity.RangedWeaponListAdapter.RangedWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new AddRangedWeaponActivity.RangedWeaponListAdapter.RangedWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(AddRangedWeaponActivity.RangedWeaponListAdapter.RangedWeaponViewHolder holder, int position) {
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

