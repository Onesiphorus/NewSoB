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
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.GearBaseViewModel;

import java.util.List;

public class AddGearBaseActivity extends AppCompatActivity {

    public static final int CLOTHING_REQUEST = 101;
    private GearBaseViewModel mGearBaseViewModel;
    private GearBase gearBase;
    private SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gear_base);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AddGearBaseActivity.GearBaseListAdapter adapter = new AddGearBaseActivity.GearBaseListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mGearBaseViewModel = ViewModelProviders.of(this).get(GearBaseViewModel.class);

        mGearBaseViewModel.getAllGear().observe(this, new Observer<List<GearBase>>() {
            @Override
            public void onChanged(@Nullable List<GearBase> gearBase) {
                adapter.setGearBase(gearBase);
            }
        });

        findViewById(R.id.gear_base_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            /*
            if(gearBase != null) {
                intent.putExtra("serializable_object", gearBase);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if (gearBase != null) {
                sobCharacter.addGear(gearBase);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, gearBase.getName() + "added to inventory.", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
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
}

