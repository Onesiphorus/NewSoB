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

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ManagementMenuActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class RemoveRangedActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    RangedWeapon rangedWeapon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RemoveRangedActivity.RangedWeaponListAdapter adapter = new RemoveRangedActivity.RangedWeaponListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<RangedWeapon> rangedList = new ArrayList<>(0);
        for (RangedWeapon ranged : sobCharacter.getRangedWeapons()) {
            if (ranged.getEquipped().equals(Boolean.FALSE) && ranged.getPersonal().equals(FALSE)) rangedList.add(ranged);
        }
        adapter.setRangedWeapon(rangedList);

        findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveRangedActivity.class);
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
            Intent intent = new Intent(this, RemoveRangedActivity.class);
            /*
            if(rangedWeapon != null) {
                intent.putExtra("serializable_object", rangedWeapon);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if (rangedWeapon != null) {
                sobCharacter.removeRangedWeapon(rangedWeapon);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, rangedWeapon.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

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

    class RangedWeaponListAdapter extends RecyclerView.Adapter<RemoveRangedActivity.RangedWeaponListAdapter.RangedWeaponViewHolder> {

        private final LayoutInflater mInflater;
        private List<RangedWeapon> mRangedWeapon;
        private Context mContext;
        private Integer RESULT_CODE = 1;
        public RangedWeaponListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RemoveRangedActivity.RangedWeaponListAdapter.RangedWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RemoveRangedActivity.RangedWeaponListAdapter.RangedWeaponViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RemoveRangedActivity.RangedWeaponListAdapter.RangedWeaponViewHolder holder, int position) {
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
