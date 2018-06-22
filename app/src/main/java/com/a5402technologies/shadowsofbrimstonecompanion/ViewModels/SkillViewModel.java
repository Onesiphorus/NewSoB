package com.a5402technologies.shadowsofbrimstonecompanion.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.a5402technologies.shadowsofbrimstonecompanion.Models.Skill;
import com.a5402technologies.shadowsofbrimstonecompanion.Repositories.SkillRepository;

import java.util.List;

public class SkillViewModel extends AndroidViewModel {

    private SkillRepository mRepository;

    private LiveData<List<Skill>> mAllSkills;

    public SkillViewModel (Application application) {
        super(application);
        mRepository = new SkillRepository(application);
        mAllSkills = mRepository.getAllSkill();
    }

    public LiveData<List<Skill>> getAllSkill() {
        return mAllSkills;
    }

    public void insert(Skill skill) {
        mRepository.insert(skill);
    }
}
