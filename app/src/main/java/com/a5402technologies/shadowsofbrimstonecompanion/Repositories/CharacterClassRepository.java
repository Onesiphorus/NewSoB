package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterClassDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class CharacterClassRepository {

    private CharacterClassDao mCharacterClassDao;
    private LiveData<List<CharacterClass>> mAllCharacterClasses;

    public CharacterClassRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mCharacterClassDao = db.characterClassDao();
        mAllCharacterClasses = mCharacterClassDao.getAllCharacterClasses();
    }

    public LiveData<List<CharacterClass>> getAllCharacterClasses() {
        return mAllCharacterClasses;
    }

    public void insert (CharacterClass characterClass) {
        new insertAsyncTask(mCharacterClassDao).execute(characterClass);
    }

    private static class insertAsyncTask extends AsyncTask<CharacterClass, Void, Void> {

        private CharacterClassDao mAsyncTaskDao;

        insertAsyncTask(CharacterClassDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final CharacterClass... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
