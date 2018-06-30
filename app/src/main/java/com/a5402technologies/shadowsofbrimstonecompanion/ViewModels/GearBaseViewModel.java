package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.GearBaseRepository;

import java.util.List;

public class GearBaseViewModel extends AndroidViewModel {

    private GearBaseRepository mRepository;

    private LiveData<List<GearBase>> mAllGear;

    public GearBaseViewModel(Application application) {
        super(application);
        mRepository = new GearBaseRepository(application);
        mAllGear = mRepository.getAllGear();
    }

    public LiveData<List<GearBase>> getAllGear() {
        return mAllGear;
    }

    public void insert(GearBase gearBase) {
        mRepository.insert(gearBase);
    }
}
