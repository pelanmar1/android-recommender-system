package com.example.pedrolanzagorta.realmtest.sessionInstances;

import com.example.pedrolanzagorta.realmtest.controllers.UsersController;
import com.example.pedrolanzagorta.realmtest.models.User;

/**
 * Created by Pedro Lanzagorta on 16/07/2016.
 */
public class ActiveUser {
    static User activeUser = null;
    private ActiveUser(){}

    public static User getActiveUser(){
        return activeUser;
    }
    public static boolean setActiveUser(String username){
        if(username == null)
            activeUser=null;
        else {
            if (activeUser == null)
                activeUser = UsersController.getUser(username);
        }
        return activeUser == null;
    }
}
