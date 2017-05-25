package com.example.pedrolanzagorta.realmtest.controllers;

import com.example.pedrolanzagorta.realmtest.models.RatingRecord;
import com.example.pedrolanzagorta.realmtest.models.User;

import io.realm.Realm;

/**
 * Created by Pedro Lanzagorta on 19/07/2016.
 */
public class RecommenderSystemController {

    private static Realm realm = Realm.getDefaultInstance();

    public static void addRecordToUserHistory(User user, RatingRecord ratingRecord){
        realm.beginTransaction();
        user.getUserRatingHistory().add(ratingRecord);
        realm.commitTransaction();
    }


}

