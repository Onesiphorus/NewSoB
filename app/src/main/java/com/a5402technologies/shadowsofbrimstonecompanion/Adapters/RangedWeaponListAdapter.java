package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class RangedWeaponListAdapter extends RecyclerView.Adapter<RangedWeaponListAdapter.RangedWeaponViewHolder> {

    class RangedWeaponViewHolder extends RecyclerView.ViewHolder {
        private final TextView rangedWeaponItemView;

        private RangedWeaponViewHolder(View itemView) {
            super(itemView);
            rangedWeaponItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<RangedWeapon> mRangedWeapon;

    public RangedWeaponListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RangedWeaponListAdapter.RangedWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RangedWeaponListAdapter.RangedWeaponViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RangedWeaponListAdapter.RangedWeaponViewHolder holder, int position) {
        if (null != mRangedWeapon) {
            RangedWeapon current = mRangedWeapon.get(position);
            holder.rangedWeaponItemView.setText(current.getName());
        } else {
            holder.rangedWeaponItemView.setText("No Ranged Weapons");
        }
    }

    public void setRangedWeapons(List<RangedWeapon> rangedWeapons) {
        mRangedWeapon = rangedWeapons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mRangedWeapon) return mRangedWeapon.size();
        else return 0;
    }
}