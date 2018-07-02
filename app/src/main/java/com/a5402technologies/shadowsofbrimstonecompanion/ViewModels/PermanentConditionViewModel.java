package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.PermanentCondition;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.PermanentConditionRepository;

import java.util.List;

public class PermanentConditionViewModel extends AndroidViewModel {

    private PermanentConditionRepository mRepository;

    private LiveData<List<PermanentCondition>> mAllPermanentCondition;

    public PermanentConditionViewModel(Application application) {
        super(application);
        mRepository = new PermanentConditionRepository(application);
        mAllPermanentCondition = mRepository.getAllPermanentCondition();
    }

    public LiveData<List<PermanentCondition>> getAllPermanentCondition() {
        return mAllPermanentCondition;
    }

    public void insert(PermanentCondition permanentCondition) {
        mRepository.insert(permanentCondition);
    }
}
