package com.example.pedrolanzagorta.realmtest.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.adapters.UsersListAdapter;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;

/**
 * Created by Pedro Lanzagorta on 08/07/2016.
 */
public class UsersListFragment extends android.support.v4.app.ListFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_layout,container,false);
        setListAdapter(new UsersListAdapter(getActivity(), UsersController.getUsers()));
        return rootView;

    }
}
