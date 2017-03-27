package com.messagevk.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ListFreindsAdapter extends RecyclerView.Adapter<ListFreindsAdapter.ViewHolder> {

    Context context;
    List<String> listFriends;

    public ListFreindsAdapter(Context context, List<String> listFriends) {
        this.context = context;
        this.listFriends = listFriends;
    }

    public void getList(List<String> listFriends) {
        this.listFriends = listFriends;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listFriends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View v) {
            super(v);
        }
    }
}
