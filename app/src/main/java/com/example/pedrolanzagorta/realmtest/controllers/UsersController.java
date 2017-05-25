package com.example.pedrolanzagorta.realmtest.controllers;

import com.example.pedrolanzagorta.realmtest.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import io.realm.Realm;
import io.realm.Sort;


/**
 * Created by Pedro Lanzagorta on 06/07/2016.
 */
public class UsersController {
    private static Realm realm = Realm.getDefaultInstance();

    public static void registerUser(User user){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    public static List<User> getUsers(){
        return realm.where(User.class).findAll();
    }

    public static long countUsername(String username){
        return realm.where(User.class).equalTo("username",username).count();
    }
    public static long verifyUser(String username,String password){
        return realm.where(User.class).equalTo("username",username).equalTo("password",password).count();
    }
    public static void deleteAllUsers(){

        realm.beginTransaction();
        realm.where(User.class).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }
    public static User getUser(String username){
        return realm.where(User.class).equalTo("username",username).findFirst();
    }
    public static long countUsers(){
        return realm.where(User.class).count();
    }
    public static User getUser(long id){
        return realm.where(User.class).equalTo("id",id).findFirst();
    }
    public static long getMaxUserId(){
        return realm.where(User.class).max("id").longValue();
    }
    public static List<User> getUsersSortedById(){
        return realm.where(User.class).findAll().sort("id", Sort.ASCENDING);
    }

}
