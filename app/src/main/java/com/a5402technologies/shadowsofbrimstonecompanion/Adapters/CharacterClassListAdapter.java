package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class CharacterClassListAdapter extends RecyclerView.Adapter<CharacterClassListAdapter.CharacterClassViewHolder> {

    class CharacterClassViewHolder extends RecyclerView.ViewHolder {
        private final TextView characterClassItemView;

        private CharacterClassViewHolder(View itemView) {
            super(itemView);
            characterClassItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<CharacterClass> mCharacterClasses;

    public CharacterClassListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CharacterClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CharacterClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CharacterClassViewHolder holder, int position) {
        if (null != mCharacterClasses) {
            CharacterClass current = mCharacterClasses.get(position);
            holder.characterClassItemView.setText(current.getClassName());
        } else {
            holder.characterClassItemView.setText("No Classes");
        }
    }

    public void setCharactersClasses(List<CharacterClass> characteClasses) {
        mCharacterClasses = characteClasses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mCharacterClasses)
            return mCharacterClasses.size();
        else return 0;
    }
}
