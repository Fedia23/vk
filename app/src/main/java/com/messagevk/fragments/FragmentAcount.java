package com.messagevk.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.messagevk.R;
import com.messagevk.list.ListFreindsAdapter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKList;

public class FragmentAcount extends Fragment {

    public RecyclerView mRecyclerView;
    ListFreindsAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    private TabHost tabHost;

    private VKList<VKApiUserFull> list;

    public FragmentAcount() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_acount, container, false);

        tabHost = (TabHost)v.findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Друзі");
        tabSpec.setContent(R.id.rview);
        tabHost.addTab(tabSpec);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.rview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListFreindsAdapter(getActivity(), list = new VKList<>());
        mRecyclerView.setAdapter(mAdapter);

        showFriends();

        return v;
    }

    private void showFriends() {
        final VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, photo_50"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(final VKResponse response) {
                super.onComplete(response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list.addAll((VKList<VKApiUserFull>) response.parsedModel);
                        mAdapter.setNewList(list);
                        mRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
