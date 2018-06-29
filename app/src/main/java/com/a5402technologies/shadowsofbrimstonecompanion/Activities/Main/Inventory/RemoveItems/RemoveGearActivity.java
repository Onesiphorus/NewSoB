package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.RemoveItems;

import android.content.Context;
import android.content.Intent;
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

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

public class RemoveGearActivity extends AppCompatActivity {

    SobCharacter sobCharacter;
    GearBase gearBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview_remove_gear);
        final RemoveGearActivity.GearBaseListAdapter adapter = new RemoveGearActivity.GearBaseListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<GearBase> gearList = new ArrayList<>(0);
        for(GearBase gear : sobCharacter.getGear()) {
            gearList.add(gear);
        }
        adapter.setGearBase(gearList);

        findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class    );
            /*
            if(gearBase != null) {
                intent.putExtra("serializable_object", gearBase);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(gearBase != null) {
                sobCharacter.removeGear(gearBase);
                sobCharacter.addGold(gearBase.getSell());
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, gearBase.getName() + " sold for $" + gearBase.getSell() + ".", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class    );
            /*
            if(gearBase != null) {
                intent.putExtra("serializable_object", gearBase);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(gearBase != null) {
                sobCharacter.removeGear(gearBase);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, gearBase.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    class GearBaseListAdapter extends RecyclerView.Adapter<RemoveGearActivity.GearBaseListAdapter.GearBaseViewHolder> {

        class GearBaseViewHolder extends RecyclerView.ViewHolder {
            private final TextView gearBaseItemView;

            private GearBaseViewHolder(View itemView) {
                super(itemView);
                gearBaseItemView = itemView.findViewById(R.id.textView);

            }
        }

        private final LayoutInflater mInflater;
        private List<GearBase> mGearBase;
        private Context mContext;
        private Integer RESULT_CODE = 1;

        public GearBaseListAdapter(Context context) {
            mContext = context;
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
}
