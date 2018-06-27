package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class ClothingListAdapter extends RecyclerView.Adapter<ClothingListAdapter.ClothingViewHolder> {

    class ClothingViewHolder extends RecyclerView.ViewHolder {
        private final TextView clothingItemView;

        private ClothingViewHolder(View itemView) {
            super(itemView);
            clothingItemView = itemView.findViewById(R.id.textView);

        }
    }

    private final LayoutInflater mInflater;
    private List<Clothing> mClothing;
    private Context mContext;
    private Activity mActivity;
    private Integer RESULT_CODE = 1;

    public ClothingListAdapter(Context context, Activity activity) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mActivity = activity;
    }

    @Override
    public ClothingListAdapter.ClothingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ClothingListAdapter.ClothingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ClothingListAdapter.ClothingViewHolder holder, int position) {
        if (null != mClothing) {
            Clothing current = mClothing.get(position);
            holder.clothingItemView.setText(current.getName());
        } else {
            holder.clothingItemView.setText("No Clothing");
        }

        holder.clothingItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("serializable_object", mClothing.get(position));
                mActivity.setResult(RESULT_CODE, intent);
                mActivity.finishActivity(201);
                mActivity.finish();
            }
        });
    }

    public void setClothing(List<Clothing> clothing) {
        mClothing = clothing;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mClothing) return mClothing.size();
        else return 0;
    }
}
