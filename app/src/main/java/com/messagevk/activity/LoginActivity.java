package com.messagevk.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.messagevk.R;
import com.messagevk.fragments.FragmentAcount;
import com.messagevk.fragments.FragmentLogin;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login";
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private FragmentLogin fragmentLogin;
    private FragmentAcount fragmentAcount;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        Boolean isLocked;
        isLocked = sharedPreferences.getBoolean("sharedPreferences", false);
        if (isLocked) {
            startTestActivity();
        } else {
            fragmentLogin = new FragmentLogin();

            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.activity, fragmentLogin).commit();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sharedPreferences.edit();
                        ed.putBoolean("sharedPreferences", true);
                        ed.commit();
                        startTestActivity();
                    }
                });

            }

            @Override
            public void onError(VKError error) {
                Log.i(TAG, "error " + error.errorMessage);
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void startTestActivity() {
        Intent intent = new Intent(this, WellcomeActivity.class);
        startActivity(intent);
    }

    private void replaceFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.activity, fragment);
        fragmentTransaction.commit();
    }
}
