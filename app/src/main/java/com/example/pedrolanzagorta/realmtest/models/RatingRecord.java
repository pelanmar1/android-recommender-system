package com.example.pedrolanzagorta.realmtest.models;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pedro Lanzagorta on 19/07/2016.
 */
public class RatingRecord extends RealmObject{
    // KEY [USER,PRODUCT]
    private User user;
    private Product product;
    private int rating;

    public RatingRecord(){}
    public RatingRecord(User user, Product product,int rating){
        this.user=user;
        this.product=product;
        this.rating=rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("User rating:     "+user+"\n");
        sb.append("Product rated:   "+product+"\n");
        sb.append("Rating given:    "+rating+"\n");
        return sb.toString();
    }
}
