package com.example.pedrolanzagorta.realmtest.models;

import java.util.Comparator;

/**
 * Created by Pedro Lanzagorta on 22/07/2016.
 */
public class UsersSimilarityRecordComparator implements Comparator<UsersSimilarityRecord> {

    @Override
    public int compare(UsersSimilarityRecord o1, UsersSimilarityRecord o2) {
        int resp;
        if(o1.getSimilarityRate()<o2.getSimilarityRate())
            resp=1;
        else if(o1.getSimilarityRate()>o2.getSimilarityRate())
            resp = -1;
        else
            resp = 0;
        return resp;
    }
}