package com.messagevk.list;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
            for (VKApiUser u : list) {
                System.out.println(u.photo_50);
            }
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
        String status;
        if (list.get(position).online) { status = "online"; } else { status = "offline"; }
        holder.nameFriend.setText(list.get(position).first_name.toString() + " " + status);
        Glide.with(context).load(list.get(position).photo_50).centerCrop().into(holder.userFoto);
    }

    @Override
    public int getItemCount() {
            return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameFriend;
        private ImageView userFoto;

        public ViewHolder(View v) {
            super(v);
            nameFriend = (TextView)v.findViewById(R.id.nameFriend);
            userFoto = (ImageView)v.findViewById(R.id.userImage);
        }
    }
}
