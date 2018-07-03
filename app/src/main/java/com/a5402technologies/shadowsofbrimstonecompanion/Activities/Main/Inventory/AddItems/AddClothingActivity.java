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

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.ClothingViewModel;

import java.util.List;

public class AddClothingActivity extends AppCompatActivity {

    private ClothingViewModel mClothingViewModel;
    private Clothing clothing;
    private SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ClothingListAdapter adapter = new ClothingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mClothingViewModel = ViewModelProviders.of(this).get(ClothingViewModel.class);

        mClothingViewModel.getAllClothing().observe(this, new Observer<List<Clothing>>() {
            @Override
            public void onChanged(@Nullable List<Clothing> clothing) {
                adapter.setClothing(clothing);
            }
        });

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, FoundGearActivity.class);
            /*
            if(clothing != null) {
                intent.putExtra("serializable_object", clothing);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if (clothing != null) {
                sobCharacter.addClothing(clothing);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, clothing.getName() + "added to inventory.", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this, FoundGearActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
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
                holder.clothingItemView.setText(current.getName());
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
}


