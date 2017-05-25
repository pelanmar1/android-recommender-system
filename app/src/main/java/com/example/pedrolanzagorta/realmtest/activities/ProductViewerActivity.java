package com.example.pedrolanzagorta.realmtest.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pedrolanzagorta.realmtest.R;
import com.example.pedrolanzagorta.realmtest.adapters.ProductAdapter;
import com.example.pedrolanzagorta.realmtest.controllers.ProductsController;
import com.example.pedrolanzagorta.realmtest.controllers.RecommenderSystemController;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.Product;
import com.example.pedrolanzagorta.realmtest.models.RatingRecord;
import com.example.pedrolanzagorta.realmtest.models.User;
import com.example.pedrolanzagorta.realmtest.models.UsersSimilarityRecord;
import com.example.pedrolanzagorta.realmtest.recommenderSystem.RSAlgorithm;
import com.example.pedrolanzagorta.realmtest.recommenderSystem.RecommenderSystemManager;
import com.example.pedrolanzagorta.realmtest.sessionInstances.ActiveUser;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Created by Pedro Lanzagorta on 16/07/2016.
 */
public class ProductViewerActivity extends AppCompatActivity {

    LinearLayout background;
    final Vector<Product> products = new Vector<Product>();
    public boolean hasSeen = true;
    List<Product> recommendedProducts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_viewer_layout);
        final SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.cardHolder);
        loadRandomSample(16);
        final ProductAdapter productAdapter = new ProductAdapter(this, products);
        flingContainer.setAdapter(productAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                products.remove(0);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                int rating = -1;
                if (!hasSeen) {
                    rating = 0;
                    hasSeen = true;
                }
                User tempUser = ActiveUser.getActiveUser();
                Product tempProduct = ((Product) dataObject);
                RatingRecord ratingRecord = new RatingRecord(tempUser, tempProduct, rating);
                RecommenderSystemController.addRecordToUserHistory(tempUser, ratingRecord);
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                User tempUser = ActiveUser.getActiveUser();
                Product tempProduct = ((Product) dataObject);
                RatingRecord ratingRecord = new RatingRecord(tempUser, tempProduct, 1);
                RecommenderSystemController.addRecordToUserHistory(tempUser, ratingRecord);
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                productAdapter.notifyDataSetChanged();
                if (itemsInAdapter == 0) {
                    findViewById(R.id.recommenderLayout).setVisibility(View.VISIBLE);
                    findViewById(R.id.cardHolder).setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(float v) {
                if (v < 0) {
                    findViewById(R.id.decisionLayout).setBackgroundColor(Color.RED);
                } else if (v > 0)
                    findViewById(R.id.decisionLayout).setBackgroundColor(Color.GREEN);
                else
                    findViewById(R.id.decisionLayout).setBackgroundColor(ContextCompat.getColor(ProductViewerActivity.this, R.color.colorBtnGradCenter));


            }
        });
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                hasSeen = false;
                flingContainer.getTopCardListener().selectLeft();
            }
        });


    }



    public void loadRandomSample(int n) {
        products.clear();
        Vector<Product> allProducts = ProductsController.getProducts();
        Collections.shuffle(allProducts);
        if (n < allProducts.size()) {
            for (int i = 0; i < n; i++)
                products.add(allProducts.remove(0));
        }
        Toast.makeText(ProductViewerActivity.this, "Slide to Begin Test", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActiveUser.setActiveUser(null);
    }

    public void onPrepareRecommendationClick(View view) {
        view.setEnabled(false);
        findViewById(R.id.getRecButton).setEnabled(true);
        RecommenderSystemManager rsManager = RecommenderSystemManager.getInstance();
        RSAlgorithm rsa = rsManager.getRsAlgorithm();
        rsa.updateUsersUsersMatrix();

    }

    public void onGetRecommendation(View view) {
        view.setEnabled(false);
        findViewById(R.id.prepareRecButton).setEnabled(true);
        findViewById(R.id.getRecProduct).setEnabled(true);
        RecommenderSystemManager rsManager = RecommenderSystemManager.getInstance();
        recommendedProducts = rsManager.getRecommendedProducts(ActiveUser.getActiveUser());

    }

    public void getRecProductClick(View view) {
        ImageView imgView = (ImageView)findViewById(R.id.recImg);
        if(recommendedProducts!=null && recommendedProducts.size()>0){
            Product temp = recommendedProducts.remove(0);
            Picasso.with(ProductViewerActivity.this)
                    .load(temp.getImageUrl())
                    .resize(500,700)
                    .placeholder(R.drawable.progress_animation)
                    .into(imgView);
        }
        else {
            Toast.makeText(ProductViewerActivity.this,"Ops! We got no more recommendations, try rating more!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.configErase) {
            Toast.makeText(ProductViewerActivity.this,"Please sign out in order to erase the database",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.usersList){
            startActivity(new Intent(this,UserMainPageActivity.class));
        }
        return true;
    }
}
