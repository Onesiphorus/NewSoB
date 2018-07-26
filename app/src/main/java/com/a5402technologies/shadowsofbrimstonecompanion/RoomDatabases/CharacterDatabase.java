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
public abstract class CharacterDatabase extends RoomDatabase {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

        }
    };
    private static CharacterDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new CharacterDatabase.PopulateDbAsync(INSTANCE).execute();
                }
            };

    public static CharacterDatabase getDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (CharacterDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CharacterDatabase.class, "user_database")
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

        PopulateDbAsync(CharacterDatabase db) {
            mCharacterDao = db.characterDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}
