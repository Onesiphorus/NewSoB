package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.PermanentConditionDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.PermanentCondition;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class PermanentConditionRepository {

    private PermanentConditionDao mPermanentConditionDao;
    private LiveData<List<PermanentCondition>> mAllPermanentCondition;

    public PermanentConditionRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mPermanentConditionDao = db.permanentConditionDao();
        mAllPermanentCondition = mPermanentConditionDao.getAllPermanentCondition();

    }

    public LiveData<List<PermanentCondition>> getAllPermanentCondition() {
        return mAllPermanentCondition;
    }

    public void insert(PermanentCondition permanentCondition) {
        new PermanentConditionRepository.insertAsyncTask(mPermanentConditionDao).execute(permanentCondition);
    }

    private static class insertAsyncTask extends AsyncTask<PermanentCondition, Void, Void> {

        private PermanentConditionDao mAsyncTaskDao;

        insertAsyncTask(PermanentConditionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PermanentCondition... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
