package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.CombatViewActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems.AddSideBagTokenActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

public class SideBagActivity extends AppCompatActivity {
    private SobCharacter sobCharacter;
    private String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_bag);
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        ArrayList<String> StringOptions = new ArrayList<>(0);
        for (String string : sobCharacter.getSideBag()) {

            StringOptions.add(string);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StringListAdapter adapter = new StringListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setString(StringOptions);

        Button btn = findViewById(R.id.btn_unequip);
        btn.setText("Destroy");
        btn = findViewById(R.id.btn_equip);
        btn.setText("Use");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, AddSideBagTokenActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_equip).setOnClickListener((View view) -> {
            if (null != string) {
                Toast.makeText(this, string + " used.", Toast.LENGTH_LONG).show();
                sobCharacter.removeFromSideBag(string);
                Intent intent = new Intent(this, CombatViewActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Item not selected, click cancel to return.", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_unequip).setOnClickListener((View view) -> {
            Toast.makeText(this, string + " removed.", Toast.LENGTH_LONG).show();
            sobCharacter.removeFromSideBag(string);
            Intent intent = new Intent(this, CombatViewActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChangeLoadoutActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

    class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.StringViewHolder> {

        private final LayoutInflater mInflater;
        private List<String> mString;

        public StringListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public StringListAdapter.StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new StringListAdapter.StringViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(StringListAdapter.StringViewHolder holder, int position) {
            if (null != mString) {
                String current = mString.get(position);
                holder.stringItemView.setText(current);
            } else {
                holder.stringItemView.setText("No String");
            }

            holder.stringItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    string = mString.get(position);
                    Button btn = findViewById(R.id.btn_equip);
                    String text = "Use " + string;
                    btn.setText(text);
                    btn = findViewById(R.id.btn_unequip);
                    text = "Destroy " + string;
                    btn.setText(text);
                }
            });
        }

        public void setString(List<String> string) {
            mString = string;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mString) return mString.size();
            else return 0;
        }

        class StringViewHolder extends RecyclerView.ViewHolder {
            private final Button stringItemView;

            private StringViewHolder(View itemView) {
                super(itemView);
                stringItemView = itemView.findViewById(R.id.textView);
            }
        }
    }
}