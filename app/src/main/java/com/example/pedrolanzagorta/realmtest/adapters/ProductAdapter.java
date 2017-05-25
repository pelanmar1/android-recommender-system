package com.example.pedrolanzagorta.realmtest.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.models.Product;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

/**
 * Created by Pedro Lanzagorta on 17/07/2016.
 */
public class ProductAdapter extends BaseAdapter {

    private final Vector<Product> productList;
    private final AppCompatActivity activity;
    private ProgressBar progressBar;

    public ProductAdapter(AppCompatActivity activity, Vector<Product> productList){
        super();
        this.productList= productList;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.elementAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.card_layout,parent,false);

        TextView textView = (TextView) view.findViewById(R.id.title_text);

        textView.setText(productList.elementAt(position).getName());

        ImageView productImage = (ImageView) view.findViewById(R.id.productImage);

        String imageUrl = productList.elementAt(position).getImageUrl();

        Picasso.with(activity)
                .load(imageUrl)
                .resize(900,1400)
                .placeholder(R.drawable.progress_animation)
                .into(productImage);





        return view;
    }
}
