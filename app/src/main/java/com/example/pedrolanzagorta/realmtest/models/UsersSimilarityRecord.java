package com.example.pedrolanzagorta.realmtest.models;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pedro Lanzagorta on 19/07/2016.
 */
public class UsersSimilarityRecord extends RealmObject{

    private User thisUser;
    private User otherUser;
    private double similarityRate;


    public UsersSimilarityRecord(){

    }
    public UsersSimilarityRecord(User thisUser, User otherUser, double similarityRate){
        this.thisUser=thisUser;
        this.otherUser=otherUser;
        this.similarityRate=similarityRate;
    }

    public User getThisUser() {
        return thisUser;
    }

    public void setThisUser(User thisUser) {
        this.thisUser = thisUser;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public double getSimilarityRate() {
        return similarityRate;
    }

    public void setSimilarityRate(double similarityRate) {
        this.similarityRate = similarityRate;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("This user:          "+thisUser+"\n");
        sb.append("Other user:         "+otherUser+"\n");
        sb.append("Similarity rate:    "+similarityRate+"\n");
        return sb.toString();
    }


}



