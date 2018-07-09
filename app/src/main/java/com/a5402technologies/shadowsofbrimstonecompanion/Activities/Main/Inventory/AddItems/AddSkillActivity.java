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

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;
import com.a5402technologies.shadowsofbrimstonecompanion.ViewModels.SkillViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddSkillActivity extends AppCompatActivity {

    private SkillViewModel mSkillViewModel;
    private Skill skill;
    private SobCharacter sobCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skill);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SkillListAdapter adapter = new SkillListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSkillViewModel = ViewModelProviders.of(this).get(SkillViewModel.class);

        mSkillViewModel.getAllSkill().observe(this, new Observer<List<Skill>>() {
            @Override
            public void onChanged(@Nullable List<Skill> skills) {
                ArrayList<Skill> filteredByClass = new ArrayList<>(0);
                ArrayList<Skill> filteredByAvailable = new ArrayList<>(0);
                for (Skill s : skills) {
                    if (s.getType().equals(sobCharacter.getCharacterClass().getClassName())) {
                        filteredByClass.add(s);
                    }
                }
                for (Skill s : filteredByClass) {
                    if (s.getLevel() == 1) filteredByAvailable.add(s);
                    for (Skill have : sobCharacter.getUpgrades()) {
                        if (s.getCategory().equals(have.getCategory()) && s.getLevel() - 1 == have.getLevel()) {
                            filteredByAvailable.add(s);
                        }
                    }
                    for (Skill have : sobCharacter.getUpgrades()) {
                        if (s.getName().equals(have.getName())) {
                            filteredByAvailable.remove(s);
                        }
                    }
                }
                adapter.setSkill(filteredByAvailable);
            }
        });

        findViewById(R.id.btn_accept).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, ShadowsOfBrimstoneActivity.class);
            /*
            if(skill != null) {
                intent.putExtra("serializable_object", skill);
            }
            setResult(RESULT_CODE, intent);
            finish();
            */
            if (skill != null) {
                sobCharacter.addUpgrade(skill);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, skill.getName() + " learned.", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(this, AddSkillActivity.class);
        intent.putExtra("serializable_object", sobCharacter);
        startActivity(intent);
        Toast.makeText(this, "Please choose a skill to continue", Toast.LENGTH_LONG).show();
        finish();
    }

    class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillViewHolder> {

        private final LayoutInflater mInflater;
        private List<Skill> mSkill;
        private Context mContext;
        private Integer RESULT_CODE = 1;

        public SkillListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new SkillViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SkillViewHolder holder, int position) {
            if (null != mSkill) {
                Skill current = mSkill.get(position);
                String text;
                text = current.getName() + "\n " + current.getCategory() + " (Rank " + current.getLevel() + ")";
                holder.skillItemView.setText(text);
            } else {
                holder.skillItemView.setText("No Skill");
            }

            holder.skillItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    skill = mSkill.get(position);
                    Button btn = findViewById(R.id.btn_accept);
                    String text = "Learn " + skill.getName();
                    btn.setText(text);
                }
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
            private final TextView skillItemView;

            private SkillViewHolder(View itemView) {
                super(itemView);
                skillItemView = itemView.findViewById(R.id.textView);

            }
        }
    }
}