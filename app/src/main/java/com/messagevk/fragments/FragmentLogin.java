package com.messagevk.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.messagevk.R;
import com.messagevk.activity.WellcomeActivity;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKScopes;

public class FragmentLogin extends Fragment {

    private Button enter;

    private static final String[] sMyScope = new String[]{
            VKScope.FRIENDS,
            VKScope.WALL,
            VKScope.PHOTOS,
            VKScope.NOHTTPS,
            VKScope.MESSAGES,
            VKScope.DOCS
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        findId(v);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterVk();
            }
        });

        return v;
    }

    private void findId(View v) {
        enter = (Button)v.findViewById(R.id.enter);
    }

    private void enterVk() {
        try {
            VKSdk.login(getActivity(), sMyScope);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
