package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.SobCharacter;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.CharacterRepository;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    private CharacterRepository mRepository;

    private LiveData<List<SobCharacter>> mAllCharacters;
    private LiveData<List<String>> mAllCharacterNames;

    public CharacterViewModel (Application application) {
        super(application);
        mRepository = new CharacterRepository(application);
        mAllCharacters = mRepository.getAllCharacter();
        mAllCharacterNames = mRepository.getAllCharacterNames();
    }

    public LiveData<List<SobCharacter>> getAllCharacters() {return mAllCharacters;}

    public LiveData<List<String>> getAllCharacterNames() {return mAllCharacterNames;}

    public void insert(SobCharacter character) {mRepository.insert(character);}
}
