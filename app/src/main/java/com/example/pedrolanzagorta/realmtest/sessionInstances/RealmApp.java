package com.example.pedrolanzagorta.realmtest.sessionInstances;

import android.app.Application;

import com.example.pedrolanzagorta.realmtest.models.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Pedro Lanzagorta on 06/07/2016.
 */
public class RealmApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
    }
}
