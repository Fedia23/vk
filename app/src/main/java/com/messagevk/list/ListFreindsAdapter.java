package com.messagevk.list;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.messagevk.R;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

public class ListFreindsAdapter extends RecyclerView.Adapter<ListFreindsAdapter.ViewHolder> {

    private Context context;
    private VKList<VKApiUserFull> list;

    public ListFreindsAdapter(Context context, VKList<VKApiUserFull> list) {
        this.context = context;
        this.list = list;
    }

    public void setNewList(VKList<VKApiUserFull> list){
        this.list = list;
        if(list != null) {
            for (VKApiUser u : list)
                System.out.println(u.first_name.toString());
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_freinds_recycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameFriend.setText(list.get(position).first_name.toString());
    }

    @Override
    public int getItemCount() {
            return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameFriend;

        public ViewHolder(View v) {
            super(v);
            nameFriend = (TextView)v.findViewById(R.id.nameFriend);
        }
    }
}
