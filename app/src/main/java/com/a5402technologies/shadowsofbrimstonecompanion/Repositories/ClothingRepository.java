package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.ClothingDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class ClothingRepository {

    String mEquip;
    private ClothingDao mClothingDao;
    private LiveData<List<Clothing>> mAllClothing;

    public ClothingRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mClothingDao = db.clothingDao();
        mAllClothing = mClothingDao.getAllClothing();

    }

    public LiveData<List<Clothing>> getAllClothing() {
        return mAllClothing;
    }

    public void insert(Clothing clothing) {
        new ClothingRepository.insertAsyncTask(mClothingDao).execute(clothing);
    }

    private static class insertAsyncTask extends AsyncTask<Clothing, Void, Void> {

        private ClothingDao mAsyncTaskDao;

        insertAsyncTask(ClothingDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Clothing... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
