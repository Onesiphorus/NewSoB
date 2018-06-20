package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.RangedWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.RangedWeaponRepository;

import java.util.List;

public class RangedWeaponViewModel extends AndroidViewModel {

    private RangedWeaponRepository mRepository;

    private LiveData<List<RangedWeapon>> mAllRangedWeapons;

    public RangedWeaponViewModel (Application application) {
        super(application);
        mRepository = new RangedWeaponRepository(application);
        mAllRangedWeapons = mRepository.getAllRangedWeapons();
    }

    public LiveData<List<RangedWeapon>> getAllRangedWeapons() {
        return mAllRangedWeapons;
    }

    public void insert(RangedWeapon RangedWeapon) {
        mRepository.insert(RangedWeapon);
    }
}
