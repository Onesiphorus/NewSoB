package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Activities.Main.ShadowsOfBrimstoneActivity;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterViewHolder> {

    private final LayoutInflater mInflater;
    private Context mContext;
    private List<SobCharacter> mCharacters; //cached copy
    public CharacterListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CharacterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        if (null != mCharacters) {
            SobCharacter current = mCharacters.get(position);
            holder.characterItemView.setText(current.getCharacterName());
        } else {
            holder.characterItemView.setText("No SobCharacter");
        }

        holder.characterItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(mContext, ShadowsOfBrimstoneActivity.class));
                intent.putExtra("serializable_object", mCharacters.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    public void setCharacters(List<SobCharacter> characters) {
        mCharacters = characters;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mCharacters)
            return mCharacters.size();
        else return 0;
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        private final TextView characterItemView;

        private CharacterViewHolder(View itemView) {
            super(itemView);
            characterItemView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
