package com.example.pedrolanzagorta.realmtest.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.fragments.UsersListFragment;

import io.realm.Realm;

public class UserMainPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.userLayout,new UsersListFragment(),"User list fragment");
        transaction.commit();

    }
}
