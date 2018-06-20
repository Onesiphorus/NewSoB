package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.ClothingRepository;

import java.util.List;

public class ClothingViewModel extends AndroidViewModel {

    private ClothingRepository mRepository;

    private LiveData<List<Clothing>> mAllGear;

    public ClothingViewModel (Application application) {
        super(application);
        mRepository = new ClothingRepository(application);
        mAllGear = mRepository.getAllClothing();
    }

    public LiveData<List<Clothing>> getAllGear() {
        return mAllGear;
    }

    public void insert(Clothing gearBase) {
        mRepository.insert(gearBase);
    }
}
