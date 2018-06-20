package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class GearListAdapter extends RecyclerView.Adapter<GearListAdapter.GearViewHolder> {

    class GearViewHolder extends RecyclerView.ViewHolder {
        private final TextView gearItemView;

        private GearViewHolder(View itemView) {
            super(itemView);
            gearItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<GearBase> mGearBase;

    public GearListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public GearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new GearViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GearViewHolder holder, int position) {
        if (null != mGearBase) {
            GearBase current = mGearBase.get(position);
            holder.gearItemView.setText(current.getName());
        } else {
            holder.gearItemView.setText("No Gear");
        }
    }

    public void setGearBase(List<GearBase> gear) {
        mGearBase = gear;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mGearBase) return mGearBase.size();
        else return 0;
    }
}
