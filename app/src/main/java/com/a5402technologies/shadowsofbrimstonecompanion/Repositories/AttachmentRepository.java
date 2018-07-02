package com.a5402technologies.shadowsofbrimstonecompanion.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.a5402technologies.shadowsofbrimstonecompanion.DaoInterfaces.AttachmentDao;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.RoomDatabases.SOBRoomDatabase;

import java.util.List;

public class AttachmentRepository {

    private AttachmentDao mAttachmentDao;
    private LiveData<List<Attachment>> mAllAttachment;

    public AttachmentRepository(Application application) {
        SOBRoomDatabase db = SOBRoomDatabase.getDatabase(application);
        mAttachmentDao = db.attachmentDao();
        mAllAttachment = mAttachmentDao.getAllAttachment();

    }

    public LiveData<List<Attachment>> getAllAttachment() {
        return mAllAttachment;
    }

    public void insert(Attachment attachment) {
        new AttachmentRepository.insertAsyncTask(mAttachmentDao).execute(attachment);
    }

    private static class insertAsyncTask extends AsyncTask<Attachment, Void, Void> {

        private AttachmentDao mAsyncTaskDao;

        insertAsyncTask(AttachmentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Attachment... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
