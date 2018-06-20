package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class MeleeWeaponListAdapter extends RecyclerView.Adapter<MeleeWeaponListAdapter.MeleeWeaponViewHolder> {

    class MeleeWeaponViewHolder extends RecyclerView.ViewHolder {
        private final TextView meleeWeaponItemView;

        private MeleeWeaponViewHolder(View itemView) {
            super(itemView);
            meleeWeaponItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<MeleeWeapon> mMeleeWeapon;

    public MeleeWeaponListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MeleeWeaponViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new MeleeWeaponListAdapter.MeleeWeaponViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MeleeWeaponListAdapter.MeleeWeaponViewHolder holder, int position) {
        if (null != mMeleeWeapon) {
            MeleeWeapon current = mMeleeWeapon.get(position);
            holder.meleeWeaponItemView.setText(current.getName());
        } else {
            holder.meleeWeaponItemView.setText("No Melee Weapons");
        }
    }

    public void setMeleeWeapons(List<MeleeWeapon> meleeWeapons) {
        mMeleeWeapon = meleeWeapons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mMeleeWeapon) return mMeleeWeapon.size();
        else return 0;
    }
}
