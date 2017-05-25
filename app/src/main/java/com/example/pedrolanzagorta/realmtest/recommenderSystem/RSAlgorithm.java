package com.example.pedrolanzagorta.realmtest.recommenderSystem;

import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.User;
import com.example.pedrolanzagorta.realmtest.models.UsersSimilarityRecord;
import com.example.pedrolanzagorta.realmtest.models.UsersSimilarityRecord.*;
import com.example.pedrolanzagorta.realmtest.models.UsersSimilarityRecordComparator;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;

import Jama.Matrix;

/**
 * Created by Pedro Lanzagorta on 19/07/2016.
 */
public class RSAlgorithm {
    Matrix usersProductsMatrix;
    Vector<PriorityQueue<UsersSimilarityRecord>> usersUsersMatrix;


    public RSAlgorithm(Matrix usersProductsMatrix) {
        this.usersProductsMatrix = usersProductsMatrix;
        usersUsersMatrix = new Vector<PriorityQueue<UsersSimilarityRecord>>();
    }


    // CONSIDER: DYNAMIC PRODUCT MATRIX
    public void updateUsersUsersMatrix() {
        usersUsersMatrix.clear(); //OPTIONAL
        long numberOfUsers = usersProductsMatrix.getRowDimension();
        long numberOfItems = usersProductsMatrix.getColumnDimension();
        usersUsersMatrix = new Vector<PriorityQueue<UsersSimilarityRecord>>((int)numberOfUsers);
        UsersSimilarityRecordComparator usersSimilarityRecordComparator = new UsersSimilarityRecordComparator();
        for(int i = 0; i< numberOfUsers;i++){
            for(int j = 0;j<numberOfUsers;j++){
                if(i!=j){
                    Matrix thisUserVector = usersProductsMatrix.getMatrix(i, i,0,(int)numberOfItems-1);
                    Matrix otherUserVector = usersProductsMatrix.getMatrix(j, j,0,(int)numberOfItems-1);
                    double pearsonCorrelation = getPearsonCorrelation(thisUserVector,otherUserVector);
                    User thisUser= UsersController.getUser(i);
                    User otherUser=UsersController.getUser(j);
                    UsersSimilarityRecord usersSimilarityRecord = new UsersSimilarityRecord(thisUser,otherUser,pearsonCorrelation);
                    if(usersUsersMatrix.size()<=i)
                        usersUsersMatrix.add(i,new PriorityQueue<UsersSimilarityRecord>((int)numberOfUsers,usersSimilarityRecordComparator));
                    usersUsersMatrix.get(i).offer(usersSimilarityRecord);
                }
            }
        }

    }

    public double getPearsonCorrelation(Matrix thisUser, Matrix otherUser) {
        double pearsonCorrelation=-2;
        try {
            int vectorRowDimension = thisUser.getRowDimension();
            int vectorColumnDimension = thisUser.getColumnDimension();

            // Standarize vectors
            Matrix thisUserMeanVector = new Matrix(1, vectorColumnDimension,
                    thisUser.normInf() / vectorColumnDimension);
            Matrix otherUserMeanVector = new Matrix(1, vectorColumnDimension,
                    otherUser.normInf() / vectorColumnDimension);

            thisUser.minusEquals(thisUserMeanVector);
            otherUser.minusEquals(otherUserMeanVector);

            double thisUserNorm = thisUser.normF();
            double otherUserNorm = otherUser.normF();
            double alpha = (thisUserNorm * otherUserNorm) == 0 ? 1 : 1 / (thisUserNorm * otherUserNorm);
            pearsonCorrelation = (thisUser.times(otherUser.transpose())).times(alpha).get(0, 0);
        } catch (Exception e) {
            System.out.println(e.toString()+
                    " RS_NOTE: getPearsonCorrelation(...) takes in two row vectors with equal dimensions as parameters.");
        }

        return pearsonCorrelation;
    }

    public Vector<PriorityQueue<UsersSimilarityRecord>> getUsersUsersMatrix() {
        return usersUsersMatrix;
    }


    public Matrix getRecommendedProductsVector(Matrix thisUser,Matrix otherUser){
        Matrix tempVector = otherUser.minus(otherUser.arrayTimes(thisUser));
        return tempVector;

    }

    public Matrix getUsersProductsMatrix() {
        return usersProductsMatrix;
    }

    public void setUsersProductsMatrix(Matrix usersProductsMatrix) {
        this.usersProductsMatrix = usersProductsMatrix;
    }

    public void updateUsersUsersMatrix(Vector<PriorityQueue<UsersSimilarityRecord>> usersUsersMatrix) {
        this.usersUsersMatrix = usersUsersMatrix;
    }
}
