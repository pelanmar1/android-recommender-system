package com.example.pedrolanzagorta.realmtest.models;

import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.recommenderSystem.RecommenderSystemManager;

import java.util.PriorityQueue;
import java.util.Vector;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Pedro Lanzagorta on 06/07/2016.
 */
public class User extends RealmObject {
    @PrimaryKey
    private String username;
    private String password;
    private String name;
    private long id=-1;
    private static final int NUMBER_OF_NEIGHBORS=2;

    private RealmList<RatingRecord> userRatingHistory;
    @Ignore
    private PriorityQueue<UsersSimilarityRecord> usersSimilarityHistory;


    public User(){}
    public User(String name,String userName, String password){
        this.username = userName;
        this.password = password;
        this.name = name;
        long countUsers = UsersController.countUsers();
        if(countUsers==0) {
            id = 0;
        }else
            id = UsersController.getMaxUserId()+1;
        userRatingHistory = new RealmList<RatingRecord>();
        usersSimilarityHistory = new PriorityQueue<UsersSimilarityRecord>(NUMBER_OF_NEIGHBORS,new UsersSimilarityRecordComparator());


    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Name:     "+ name + "\n");
        sb.append("Username: "+ username +"\n");
        sb.append("Password: "+password+"\n");
        sb.append("ID:       "+id+"\n");
        return sb.toString();
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public boolean equals(Object other){
        boolean resp = false;
        User user = ((User)other);
        if((this == other) || (
                (other != null && this.getClass() == other.getClass()) &&
                        (user.getUsername().equals(this.username) && user.getPassword().equals(this.password))))
            resp = true;

        return resp;
    }
    public long getId(){
        return id;
    }

    public RealmList<RatingRecord> getUserRatingHistory(){
        return userRatingHistory;
    }
    public PriorityQueue<UsersSimilarityRecord> getUsersSimilarityHistory(){
        Vector<PriorityQueue<UsersSimilarityRecord>> uSQVector = RecommenderSystemManager.getInstance().getRsAlgorithm().getUsersUsersMatrix();
        PriorityQueue uSQ = (uSQVector == null)? null : uSQVector.get((int)id);
        return uSQ;
    }

}
