package com.example.pedrolanzagorta.realmtest.models;

import com.example.pedrolanzagorta.realmtest.controllers.ProductsController;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pedro Lanzagorta on 17/07/2016.
 */
public class Product extends RealmObject {
    @PrimaryKey
    private String name;
    private String imageUrl;
    private long id=-1;

    public Product(){}
    public Product(String name, String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
        long countProducts = ProductsController.countProducts();
        if(countProducts==0) {
            id = 0;
        }else
            id = ProductsController.getMaxProductId()+1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public boolean equals(Object other){
        boolean resp = false;
        Product user = ((Product)other);
        if((this == other) || (
                (other != null && this.getClass() == other.getClass()) &&
                        (user.getId() == this.id )))
            resp = true;

        return resp;
    }


}
