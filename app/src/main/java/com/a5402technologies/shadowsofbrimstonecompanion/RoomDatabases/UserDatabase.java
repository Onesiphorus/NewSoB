package com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterDao;
import com.a5402technologies.shadowsofbrimstonecompanion.GithubTypeConverters;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;

@Database(entities = {SobCharacter.class}, version = 1)
@TypeConverters({GithubTypeConverters.class})
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new UserDatabase.PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static UserDatabase getDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (UserDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database")
                            .addMigrations()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CharacterDao characterDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final CharacterDao mCharacterDao;

        PopulateDbAsync(UserDatabase db) {
            mCharacterDao = db.characterDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}
