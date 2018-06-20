package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.MeleeWeaponDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class MeleeWeaponRepository {

    private MeleeWeaponDao mMeleeWeaponDao;
    private LiveData<List<MeleeWeapon>> mAllMeleeWeapons;

    public MeleeWeaponRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mMeleeWeaponDao = db.meleeWeaponDao();
        mAllMeleeWeapons = mMeleeWeaponDao.getAllMeleeWeapons();
    }

    public LiveData<List<MeleeWeapon>> getAllMeleeWeapons() {
        return mAllMeleeWeapons;
    }

    public void insert (MeleeWeapon meleeWeapon) {
        new insertAsyncTask(mMeleeWeaponDao).execute(meleeWeapon);
    }

    private static class insertAsyncTask extends AsyncTask<MeleeWeapon, Void, Void> {
        private MeleeWeaponDao mAsyncTaskDao;

        insertAsyncTask(MeleeWeaponDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MeleeWeapon... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
