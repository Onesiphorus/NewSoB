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
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

public class RemoveClothingActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    Clothing clothing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview_remove_gear);
        final RemoveClothingActivity.ClothingListAdapter adapter = new RemoveClothingActivity.ClothingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Clothing> clothingList = new ArrayList<>(0);
        for(Clothing clothing : sobCharacter.getClothing()) {
            if(clothing.getEquipped().equals(Boolean.FALSE)) clothingList.add(clothing);
        }
        adapter.setClothing(clothingList);

        findViewById(R.id.btn_sell).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class    );
            /*
            if(clothing != null) {
                intent.putExtra("serializable_object", clothing);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(clothing != null) {
                sobCharacter.removeClothing(clothing);
                sobCharacter.addGold(clothing.getSell());
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, clothing.getName() + " sold for $" + clothing.getSell() + ".", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ManagementMenuActivity.class    );
            /*
            if(clothing != null) {
                intent.putExtra("serializable_object", clothing);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if(clothing != null) {
                sobCharacter.removeClothing(clothing);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, clothing.getName() + " removed from inventory.", Toast.LENGTH_LONG).show();
            }
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    class ClothingListAdapter extends RecyclerView.Adapter<RemoveClothingActivity.ClothingListAdapter.ClothingViewHolder> {

        class ClothingViewHolder extends RecyclerView.ViewHolder {
            private final TextView clothingItemView;

            private ClothingViewHolder(View itemView) {
                super(itemView);
                clothingItemView = itemView.findViewById(R.id.textView);

            }
        }

        private final LayoutInflater mInflater;
        private List<Clothing> mClothing;
        private Context mContext;

        public ClothingListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RemoveClothingActivity.ClothingListAdapter.ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RemoveClothingActivity.ClothingListAdapter.ClothingViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RemoveClothingActivity.ClothingListAdapter.ClothingViewHolder holder, int position) {
            if (null != mClothing) {
                Clothing current = mClothing.get(position);
                String text = current.getName() + ": $" + current.getSell();
                holder.clothingItemView.setText(text);
            } else {
                holder.clothingItemView.setText("No Clothing");
            }

            holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clothing = mClothing.get(position);
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
