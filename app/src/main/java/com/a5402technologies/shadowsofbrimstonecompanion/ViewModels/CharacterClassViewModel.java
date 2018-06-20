package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.CharacterClass;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.CharacterClassRepository;

import java.util.List;

public class CharacterClassViewModel extends AndroidViewModel {

    private CharacterClassRepository mRepository;

    private LiveData<List<CharacterClass>> mAllCharacterClasses;

    public CharacterClassViewModel (Application application) {
        super(application);
        mRepository = new CharacterClassRepository(application);
        mAllCharacterClasses = mRepository.getAllCharacterClasses();
    }

    public LiveData<List<CharacterClass>> getAllCharacterClasses() {
        return mAllCharacterClasses;
    }

    public void insert(CharacterClass characterClass) {
        mRepository.insert(characterClass);
    }
}
