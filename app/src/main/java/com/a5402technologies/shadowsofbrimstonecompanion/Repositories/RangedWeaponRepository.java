package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.RangedWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class RangedWeaponRepository {

    private RangedWeaponDao mRangedWeaponDao;
    private LiveData<List<RangedWeapon>> mAllRangedWeapons;

    public RangedWeaponRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mRangedWeaponDao = db.rangedWeaponDao();
        mAllRangedWeapons = mRangedWeaponDao.getAllRangedWeapons();
    }

    public LiveData<List<RangedWeapon>> getAllRangedWeapons() {
        return mAllRangedWeapons;
    }

    public void insert(RangedWeapon rangedWeapon) {
        new RangedWeaponRepository.insertAsyncTask(mRangedWeaponDao).execute(rangedWeapon);
    }

    private static class insertAsyncTask extends AsyncTask<RangedWeapon, Void, Void> {
        private RangedWeaponDao mAsyncTaskDao;

        insertAsyncTask(RangedWeaponDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RangedWeapon... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
