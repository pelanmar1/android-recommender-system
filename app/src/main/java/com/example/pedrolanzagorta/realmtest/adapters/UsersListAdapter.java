package com.example.pedrolanzagorta.realmtest.adapters;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.models.User;

import java.util.List;
import java.util.Vector;

/**
 * Created by Pedro Lanzagorta on 08/07/2016.
 */
public class UsersListAdapter extends BaseAdapter {
    private final List<User> list;
    private final Activity activity;

    public UsersListAdapter(Activity activity, List<User> list){
        super();
        this.activity = activity;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.list_item_layout,null,true);
        TextView listItemTV1 = (TextView) view.findViewById(R.id.listItemTV1);
        listItemTV1.setText(list.get(position).getName());
        TextView listItemTV2 = (TextView) view.findViewById(R.id.listItemTV2);
        listItemTV2.setText(list.get(position).getUsername());
        ImageView listItemIcon = (ImageView) view.findViewById(R.id.listItemIcon);
        listItemIcon.setImageResource(R.drawable.ic_face_white_24dp);
        return view;
    }
}
