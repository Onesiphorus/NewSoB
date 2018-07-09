package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.AddItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.Inventory.Equip.SideBagActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Enums.SideBagTokenEnums;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class AddSideBagTokenActivity extends AppCompatActivity {

    SobCharacter sobCharacter;
    ArrayList<String> sideBagTokens = new ArrayList<>(0);
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gear_base);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StringListAdapter adapter = new StringListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        EnumSet<SideBagTokenEnums> allTokens = EnumSet.allOf(SideBagTokenEnums.class);

        for (SideBagTokenEnums token : allTokens) {
            sideBagTokens.add(token.label());
        }
        adapter.setString(sideBagTokens);

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            if (null != token) {
                if (sobCharacter.getSideBag().size() < sobCharacter.getSideBagSize()) {
                    Intent intent = new Intent(this, SideBagActivity.class);
                    sobCharacter.getSideBag().add(token);
                    intent.putExtra("serializable_object", sobCharacter);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "No room in sidebag!", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "No item selected", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });
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

            holder.stringItemView.setOnClickListener((View view) -> {
                token = mString.get(position);
                Button btn = findViewById(R.id.btn_accept);
                String text = "Add " + token + " to side bag";
                btn.setText(text);
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
