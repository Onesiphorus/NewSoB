package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.CharacterDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class CharacterRepository {
    private CharacterDao mCharacterDao;
    private LiveData<List<String>> mAllCharacterNames;
    private LiveData<List<SobCharacter>> mAllCharacters;
    public CharacterRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mCharacterDao = db.characterDao();
        mAllCharacterNames = mCharacterDao.getAllCharacterNames();
        mAllCharacters = mCharacterDao.getAllSobCharacters();
    }

    public LiveData<List<SobCharacter>> getAllCharacter() {
        return mAllCharacters;
    }
    public LiveData<List<String>> getAllCharacterNames() {
        return mAllCharacterNames;
    }

    public LiveData<SobCharacter> getCharacterByName(String name) {
        return mCharacterDao.getCharacterByName(name);
    }

    public void insert (SobCharacter character) {
        new insertAsyncTask(mCharacterDao).execute(character);
    }

    private static class insertAsyncTask extends AsyncTask<SobCharacter, Void, Void> {

        private CharacterDao mAsyncTaskDao;

        insertAsyncTask(CharacterDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SobCharacter... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
