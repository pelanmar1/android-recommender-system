package com.example.pedrolanzagorta.realmtest.controllers;

import com.example.pedrolanzagorta.realmtest.models.Product;
import com.example.pedrolanzagorta.realmtest.models.User;

import java.util.List;
import java.util.Vector;

import io.realm.Realm;
import io.realm.Sort;

/**
 * Created by Pedro Lanzagorta on 19/07/2016.
 */
public class ProductsController {

    private static Realm realm = Realm.getDefaultInstance();

    public static long countProducts(){
        return realm.where(Product.class).count();
    }
    public static void addProduct(Product product){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(product);
        realm.commitTransaction();
    }

    public static long countProductByName(String name){
        return realm.where(Product.class).equalTo("name",name).count();
    }
    public static long getProductIdByName(String name){
        Product tempProduct = realm.where(Product.class).equalTo("name", name).findFirst();
        long id = (tempProduct == null)? -1:tempProduct.getId();
        return  id;
    }
    public static Vector<Product> getProducts(){
        return new Vector<Product>(realm.where(Product.class).findAll());
    }
    public static void deleteAllProducts(){
        realm.beginTransaction();
        realm.where(Product.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
    public static Product getProduct(long id){
        return realm.where(Product.class).equalTo("id",id).findFirst();
    }
    public static Vector<Product> getProductsSortedByID(){
        return new Vector<Product>(realm.where(Product.class).findAll().sort("id", Sort.ASCENDING));
    }

    public static long getMaxProductId() {
        return realm.where(Product.class).max("id").longValue();
    }
}
