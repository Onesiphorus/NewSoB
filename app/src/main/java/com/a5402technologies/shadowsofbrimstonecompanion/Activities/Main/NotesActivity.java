package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

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

import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    ArrayList<String> notes = new ArrayList<>(0);
    String note;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");
        mContext = this;

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final StringListAdapter adapter = new StringListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        for (String string : sobCharacter.getNotes()) {
            notes.add(string);
        }
        adapter.setString(notes);

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
        });

        findViewById(R.id.btn_cancel).setOnClickListener((View view) -> {
            onBackPressed();
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {
            Intent intent = new Intent(this, CreateNoteActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
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

            holder.stringItemView.setOnClickListener((View view) -> {
                note = mString.get(position);
                Intent intent = new Intent(mContext, CreateNoteActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
                intent.putExtra("note", note);
                startActivity(intent);
                finish();
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
