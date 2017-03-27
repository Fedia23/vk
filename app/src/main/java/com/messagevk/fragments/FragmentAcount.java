package com.messagevk.fragments;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.messagevk.R;
import com.messagevk.activity.WellcomeActivity;
import com.messagevk.list.ListFreindsAdapter;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiFriends;
import com.vk.sdk.api.methods.VKApiUsers;
import com.vk.sdk.api.model.VKList;

import java.util.ArrayList;
import java.util.List;

public class FragmentAcount extends Fragment {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    public List<String> friend = new ArrayList<>();

    public FragmentAcount() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.rview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

       // showFriends(v);

       // System.out.println(friends.get(request.getMethodParameters()).response.get().responseString.toString() + "SSSS");

        return v;
    }

    private void playRecyler() {
        mAdapter = new ListFreindsAdapter(getActivity(), friend);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void showFriends(View v) {
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name"));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VKList list = (VKList) response.parsedModel;
                friend = new ArrayList<String>(list);
                playRecyler();
               // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_freinds_recycler, list);

            }
        });
    }
}
