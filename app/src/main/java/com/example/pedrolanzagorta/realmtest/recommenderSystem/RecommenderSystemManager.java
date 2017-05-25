package com.example.pedrolanzagorta.realmtest.recommenderSystem;

import android.util.Log;

import com.example.pedrolanzagorta.realmtest.controllers.ProductsController;
import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.Product;
import com.example.pedrolanzagorta.realmtest.models.RatingRecord;
import com.example.pedrolanzagorta.realmtest.models.User;
import com.example.pedrolanzagorta.realmtest.models.UsersSimilarityRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import Jama.Matrix;

/**
 * Created by Pedro Lanzagorta on 18/07/2016.
 */

public class RecommenderSystemManager {
    private static RecommenderSystemManager recommenderSystemManager = null;
    private double[][] usersProducts2DArray;
    private long numberOfUsers;
    private long numberOfProducts;
    private RSAlgorithm rsAlgorithm = null;

    private RecommenderSystemManager() {
        createNewUsersProducts2DArray();
    }

    public static RecommenderSystemManager getInstance() {
        if (recommenderSystemManager == null) {
            recommenderSystemManager = new RecommenderSystemManager();
        }
        return recommenderSystemManager;

    }

    public void createNewUsersProducts2DArray() {
        numberOfProducts = ProductsController.countProducts();
        numberOfUsers = UsersController.countUsers();
        usersProducts2DArray = new double[(int) numberOfUsers][(int) numberOfProducts];
        List<User> tempUsersList = UsersController.getUsers();
        for (int i = 0; i < numberOfUsers; i++) {
            setUserProductsVector(tempUsersList.get(i));
        }

    }

    public void setUserProductsVector(User user) {
        if (usersProducts2DArray != null) {

            int[] userProductsVector = new int[(int) numberOfProducts];
            List<RatingRecord> tempList = user.getUserRatingHistory();
            long tempUserId = user.getId();
            for (int i = 0; i < tempList.size(); i++) {
                long tempProductId = tempList.get(i).getProduct().getId();
                int tempRating = tempList.get(i).getRating();
                usersProducts2DArray[(int) tempUserId][(int) tempProductId] = tempRating;
            }
        } else
            Log.d("MATRIX STATE", "UsersProducts matrix hasn't been initialized.");
    }

    public void printUsersProductsMatrix() {
        Matrix tempMatrix = new Matrix(usersProducts2DArray);
        tempMatrix.print(1, 2);
    }

    public void setRSAlgorithm() {
        rsAlgorithm = new RSAlgorithm(new Matrix(usersProducts2DArray));
    }

    public RSAlgorithm getRsAlgorithm() {
        if(rsAlgorithm == null){
            setRSAlgorithm();
        }
        return rsAlgorithm;
    }

    public List<Product> getRecommendedProducts(User user) {
        PriorityQueue<UsersSimilarityRecord> tempQueue = user.getUsersSimilarityHistory();
        Matrix thisUser = new Matrix(getUserProductsVector(user),1);
        thisUser.print(1,2);
        Matrix otherUser;
        Matrix temp = new Matrix(1,thisUser.getColumnDimension(),0);
        otherUser = new Matrix(getUserProductsVector(tempQueue.poll().getOtherUser()),1);
        otherUser.print(1,2);
        temp = temp.plus(rsAlgorithm.getRecommendedProductsVector(thisUser,otherUser));
        temp.print(1,2);
        /*
        CHANGE SELECTIVITY FOR QUANTITY
        while(!tempQueue.isEmpty()){
            otherUser = new Matrix(getUserProductsVector(tempQueue.poll().getOtherUser()),1);
            temp = temp.plus(rsAlgorithm.getRecommendedProductsVector(thisUser,otherUser));
            thisUser = temp;
        }*/
        List<Product> allProductsList = ProductsController.getProductsSortedByID();
        for (int i=0;i<allProductsList.size();i++) System.out.println(allProductsList.get(i).getId()+" ");
        List<Product> tempProductList = new ArrayList<Product>();
        for(int i = 0; i< temp.getColumnDimension();i++){
            if(temp.get(0,i) == 1){
                tempProductList.add(allProductsList.get(i));
            }
        }
        return tempProductList;
    }

    public double[] getUserProductsVector(User user) {
        double[] userProductsVector = new double[(int) numberOfProducts];
        List<RatingRecord> tempList = user.getUserRatingHistory();
        for (int i = 0; i < tempList.size(); i++) {
            long tempProductId = tempList.get(i).getProduct().getId();
            int tempRating = tempList.get(i).getRating();
            userProductsVector[(int) tempProductId] = tempRating;
        }
        return userProductsVector;
    }

}
