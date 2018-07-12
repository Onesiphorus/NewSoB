package com.a5402technologies.shadowsofbrimstonecompanion.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a5402technologies.shadowsofbrimstonecompanion.R;

import java.util.List;

public class StringListAdapter extends RecyclerView.Adapter<StringListAdapter.StringViewHolder> {

    private final LayoutInflater mInflater;
    private List<String> mString;

    public StringListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public StringListAdapter.StringViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new StringListAdapter.StringViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StringListAdapter.StringViewHolder holder, int position) {
        if (null != mString) {
            String current = mString.get(position);
            holder.stringItemView.setText(current);
        } else {
            holder.stringItemView.setText("No String");
        }
    }

    public void setString(List<String> string) {
        mString = string;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (null != mString) return mString.size();
        else return 0;
    }

    class StringViewHolder extends RecyclerView.ViewHolder {
        private final Button stringItemView;

        private StringViewHolder(View itemView) {
            super(itemView);
            stringItemView = itemView.findViewById(R.id.textView);
        }
    }
}