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
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class RemoveSkillActivity extends AppCompatActivity {
    SobCharacter sobCharacter;
    Skill skill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        sobCharacter = (SobCharacter) getIntent().getSerializableExtra("serializable_object");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final RemoveSkillActivity.SkillListAdapter adapter = new RemoveSkillActivity.SkillListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Skill> skillList = new ArrayList<>(0);
        for (Skill skill : sobCharacter.getUpgrades()) {
            skillList.add(skill);
        }
        adapter.setSkill(skillList);

        findViewById(R.id.btn_destroy).setOnClickListener((View view) -> {
            Intent intent = new Intent(this, RemoveSkillActivity.class);
            if (skill != null) {
                sobCharacter.removeUpgrade(skill);
                intent.putExtra("serializable_object", sobCharacter);
                Toast.makeText(this, skill.getName() + " unlearned?", Toast.LENGTH_LONG).show();
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

    class SkillListAdapter extends RecyclerView.Adapter<RemoveSkillActivity.SkillListAdapter.SkillViewHolder> {

        private final LayoutInflater mInflater;
        private List<Skill> mSkill;
        private Context mContext;
        public SkillListAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public RemoveSkillActivity.SkillListAdapter.SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
            return new RemoveSkillActivity.SkillListAdapter.SkillViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RemoveSkillActivity.SkillListAdapter.SkillViewHolder holder, int position) {
            if (null != mSkill) {
                Skill current = mSkill.get(position);
                holder.skillItemView.setText(current.getName());
            } else {
                holder.skillItemView.setText("No Skill");
            }

            holder.skillItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    skill = mSkill.get(position);
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
