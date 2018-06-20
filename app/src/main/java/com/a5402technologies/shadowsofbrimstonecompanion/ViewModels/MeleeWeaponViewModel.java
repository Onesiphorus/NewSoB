package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.GearBase;
import com.a5402technologies.shadowsofbrimstonecompanion.Models.MeleeWeapon;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.MeleeWeaponRepository;

import java.util.List;

public class MeleeWeaponViewModel extends AndroidViewModel {

    private MeleeWeaponRepository mRepository;

    private LiveData<List<MeleeWeapon>> mAllMeleeWeapons;

    public MeleeWeaponViewModel (Application application) {
        super(application);
        mRepository = new MeleeWeaponRepository(application);
        mAllMeleeWeapons = mRepository.getAllMeleeWeapons();
    }

    public LiveData<List<MeleeWeapon>> getAllMeleeWeapons() {
        return mAllMeleeWeapons;
    }

    public void insert(MeleeWeapon meleeWeapon) {
        mRepository.insert(meleeWeapon);
    }
}
