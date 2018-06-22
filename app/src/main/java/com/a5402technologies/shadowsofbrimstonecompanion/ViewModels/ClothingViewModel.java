package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Clothing;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.ClothingRepository;

import java.util.List;

public class ClothingViewModel extends AndroidViewModel {

    private ClothingRepository mRepository;

    private LiveData<List<Clothing>> mAllClothing;

    public ClothingViewModel (Application application) {
        super(application);
        mRepository = new ClothingRepository(application);
        mAllClothing = mRepository.getAllClothing();
    }

    public LiveData<List<Clothing>> getAllClothing() {
        return mAllClothing;
    }

    public void insert(Clothing clothing) {
        mRepository.insert(clothing);
    }
}
