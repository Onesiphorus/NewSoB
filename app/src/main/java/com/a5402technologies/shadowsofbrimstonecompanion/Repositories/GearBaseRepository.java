package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.GearBaseDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class GearBaseRepository {

    private GearBaseDao mGearBaseDao;
    private LiveData<List<GearBase>> mAllGear;

    public GearBaseRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mGearBaseDao = db.gearBaseDao();
        mAllGear = mGearBaseDao.getAllGear();
    }

    public LiveData<List<GearBase>> getAllGear() {
        return mAllGear;
    }

    public void insert (GearBase gearBase) {
        new GearBaseRepository.insertAsyncTask(mGearBaseDao).execute(gearBase);
    }

    private static class insertAsyncTask extends AsyncTask<GearBase, Void, Void> {

        private GearBaseDao mAsyncTaskDao;

        insertAsyncTask(GearBaseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final GearBase... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
