package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Attachment;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.AttachmentRepository;

import java.util.List;

public class AttachmentViewModel extends AndroidViewModel {

    private AttachmentRepository mRepository;

    private LiveData<List<Attachment>> mAllAttachment;

    public AttachmentViewModel(Application application) {
        super(application);
        mRepository = new AttachmentRepository(application);
        mAllAttachment = mRepository.getAllAttachment();
    }

    public LiveData<List<Attachment>> getAllAttachment() {
        return mAllAttachment;
    }

    public void insert(Attachment attachment) {
        mRepository.insert(attachment);
    }
}
