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
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class RemoveMeleeActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    MeleeWeapon meleeWeapon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview_remove_gear);
        final RemoveMeleeActivity.MeleeWeaponListAdapter adapter = new RemoveMeleeActivity.MeleeWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<MeleeWeapon> meleeList = new ArrayList<>(0);
        for(MeleeWeapon melee : sobCharacter.getMeleeWeapons()) {
            if(melee.getEquipped().equals(FALSE)) meleeList.add(melee);
        }
        adapter.setMeleeWeapon(meleeList);

        findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class    );
            /*
            if(meleeWeapon != null) {
                intent.putExtra("serializable_object", meleeWeapon);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(meleeWeapon != null) {
                sobCharacter.removeMeleeWeapon(meleeWeapon);
                sobCharacter.addGold(meleeWeapon.getSell());
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, meleeWeapon.getName() + " sold for $" + meleeWeapon.getSell() + ".", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class    );
            /*
            if(meleeWeapon != null) {
                intent.putExtra("serializable_object", meleeWeapon);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(meleeWeapon != null) {
                sobCharacter.removeMeleeWeapon(meleeWeapon);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, meleeWeapon.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    class MeleeWeaponListAdapter extends RecyclerView.Adapter<RemoveMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

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
        public RemoveMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RemoveMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RemoveMeleeActivity.MeleeWeaponListAdapter.MeleeWeaponViewHolder holder, int position) {
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChooseTypeToRemoveActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }
}
