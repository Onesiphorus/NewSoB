package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class SkillListAdapter extends RecyclerView.Adapter<SkillListAdapter.SkillViewHolder> {

    class SkillViewHolder extends RecyclerView.ViewHolder {
        private final RadioButton skillItemView;

        private SkillViewHolder(View itemView) {
            super(itemView);
            skillItemView = itemView.findViewById(R.id.radioButton);
        }
    }

    private final LayoutInflater mInflater;
    private List<Skill> mSkill;

    public SkillListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SkillListAdapter.SkillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_character, parent, false);
        return new SkillListAdapter.SkillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SkillListAdapter.SkillViewHolder holder, int position) {
        if (null != mSkill) {
            Skill current = mSkill.get(position);
            holder.skillItemView.setText(current.getName());
        } else {
            holder.skillItemView.setText("No Skill");
        }
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
}
