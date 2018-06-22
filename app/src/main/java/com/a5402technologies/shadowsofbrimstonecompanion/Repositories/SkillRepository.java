package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.SkillDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class SkillRepository {

    private SkillDao mSkillDao;
    private LiveData<List<Skill>> mAllSkills;

    public SkillRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mSkillDao = db.skillDao();
        mAllSkills = mSkillDao.getAllSkill();
    }

    public LiveData<List<Skill>> getAllSkill() {
        return mAllSkills;
    }

    public void insert (Skill skill) {
        new SkillRepository.insertAsyncTask(mSkillDao).execute(skill);
    }

    private static class insertAsyncTask extends AsyncTask<Skill, Void, Void> {

        private SkillDao mAsyncTaskDao;

        insertAsyncTask(SkillDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Skill... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}