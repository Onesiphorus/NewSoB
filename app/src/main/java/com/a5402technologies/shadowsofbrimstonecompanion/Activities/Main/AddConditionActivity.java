package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.a5402technologies.shadowsofbrimstonecompanion.Enums.ConditionEnum;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.PermanentCondition;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.PermanentConditionViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AddConditionActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    String conditionType;
    PermanentConditionViewModel mPermanentConditionViewModel;
    PermanentCondition permanentCondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_condition);

        conditionType = getIntent().getStringExtra("condition_type");
        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PermanentConditionListAdapter adapter = new PermanentConditionListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mPermanentConditionViewModel = ViewModelProviders.of(this).get(PermanentConditionViewModel.class);
        mPermanentConditionViewModel.getAllPermanentCondition().observe(this, new Observer<List<PermanentCondition>>() {
            @Override
            public void onChanged(@Nullable List<PermanentCondition> permanentConditions) {
                ArrayList<PermanentCondition> filtered = new ArrayList<>(0);
                for (PermanentCondition item : permanentConditions) {
                    if(conditionType.equals(item.getType())) {
                        filtered.add(item);
                    }
                    adapter.setPermanentCondition(filtered);
                }
            }
        });

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            if (null != permanentCondition) {
                if(permanentCondition.getType().equals(ConditionEnum.INJURY.label())){
                    sobCharacter.addInjury(permanentCondition);
                }
                else if(permanentCondition.getType().equals(ConditionEnum.MADNESS.label())){
                    sobCharacter.addMadness(permanentCondition);
                }
                else if(permanentCondition.getType().equals(ConditionEnum.MUTATION.label())){
                    sobCharacter.addMutation(permanentCondition);
                }
                else if(permanentCondition.getType().equals(ConditionEnum.OTHER.label())){
                    sobCharacter.addOtherCondition(permanentCondition);
                }
                Intent intent = new Intent(this, ConditionTypeActivity.class);
                intent.putExtra("serializable_object", sobCharacter);
            } else {
                Toast.makeText(this, "Nothing selected", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, ConditionTypeActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        finish();
    }

    class PermanentConditionListAdapter extends RecyclerView.Adapter<PermanentConditionListAdapter.PermanentConditionViewHolder> {

        private final LayoutInflater mInflater;
        private List<PermanentCondition> mPermanentCondition;
        private Context mContext;

        public PermanentConditionListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public PermanentConditionListAdapter.PermanentConditionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new PermanentConditionListAdapter.PermanentConditionViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PermanentConditionListAdapter.PermanentConditionViewHolder holder, int position) {
            if (null != mPermanentCondition) {
                PermanentCondition current = mPermanentCondition.get(position);
                String text = current.getName();
                holder.permanentConditionItemView.setText(text);
            } else {
                holder.permanentConditionItemView.setText("No PermanentCondition");
            }

            holder.permanentConditionItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    permanentCondition = mPermanentCondition.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Gain " + permanentCondition.getName();
                    btn.setText(text);
                }
            });
        }

        public void setPermanentCondition(List<PermanentCondition> permanentCondition) {
            mPermanentCondition = permanentCondition;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mPermanentCondition) return mPermanentCondition.size();
            else return 0;
        }

        class PermanentConditionViewHolder extends RecyclerView.ViewHolder {
            private final TextView permanentConditionItemView;

            private PermanentConditionViewHolder(View itemView) {
                super(itemView);
                permanentConditionItemView = itemView.findViewById(R.id.textView);

            }
        }
    }
}
