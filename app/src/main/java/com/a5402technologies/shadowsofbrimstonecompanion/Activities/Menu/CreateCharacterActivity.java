package com.a5402technologies.shadowsofbrimstonecompanion.Activities.Menu;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.SkillViewModel;

import java.util.List;

public class CreateCharacterActivity extends AppCompatActivity {


    private SkillViewModel mSkillViewModel;
    private Skill skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        CharacterClass characterClass = (CharacterClass) getIntent().getSerializableExtra("serializable_object");


        RecyclerView recyclerView = findViewById(R.id.recyclerview_skills);
        final SkillListAdapter adapter = new SkillListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setSkill(characterClass.getStartingSkills());

        TextView tv = findViewById(R.id.fullscreen_content);
        tv.setText(characterClass.getClassName());

        EditText name = findViewById(R.id.character_name_value);

        //TODO Modify Starting Gear according to starting upgrade

        Button btn = findViewById(R.id.create_button);
        btn.setOnClickListener((View view) -> {
            SobCharacter sobCharacter = new SobCharacter(name.getText().toString(), characterClass);
            sobCharacter.addUpgrade(skill);
            Intent intent = new Intent(this, FinishCharacterActivity.class);
            intent.putExtra("serializable_object", sobCharacter);
            startActivity(intent);
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillViewHolder> {

        private final LayoutInflater mInflater;
        private List<Skill> mSkill;

        public SkillListAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_character, parent, false);
            return new SkillViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SkillViewHolder holder, int position) {
            if (null != mSkill) {
                Skill current = mSkill.get(position);
                holder.skillItemView.setText(current.getName());
            } else {
                holder.skillItemView.setText("No Skill");
            }
            holder.skillItemView.setOnClickListener((View view) -> {
                skill = mSkill.get(position);
            });
        }

        public void setSkill(List<Skill> skill) {
            mSkill = skill;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (null != mSkill) return mSkill.size();
            else return 0;
        }

        class SkillViewHolder extends RecyclerView.ViewHolder {
            private final RadioButton skillItemView;

            private SkillViewHolder(View itemView) {
                super(itemView);
                skillItemView = itemView.findViewById(R.id.radioButton);
            }
        }
    }
}
