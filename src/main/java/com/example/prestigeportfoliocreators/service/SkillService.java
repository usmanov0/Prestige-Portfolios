package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import com.example.prestigeportfoliocreators.models.Skill;
import com.example.prestigeportfoliocreators.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepo;

    public String newSkill(Skill skill){
        if (skillRepo.findSkillByName(skill.getName()).isPresent()){
            return CustomErrors.SKILL_ALREADY_EXISTS_ERROR;
        }
        if (!skill.checkValidType()){
            return CustomErrors.SKILL_TYPE_ERROR;
        }
        skillRepo.save(skill);
        return null;
    }

    public HashMap<String, List<Skill>> getSkills(){
        HashMap<String, List<Skill>> res = new HashMap<>();
        List<String> types = validTypes();
        for (String type : types){
            res.put(type,skillRepo.findAllSkillNameByType(type));
        }
        return res;
    }

    public Optional<Skill> getSkillByName(String name){
        return skillRepo.findSkillByName(name);
    }

    public String deleteSkill(String name){
        Optional<Skill> skill = skillRepo.findSkillByName(name);
        if (!skill.isPresent()){
            return CustomErrors.SKILL_NOT_FOUND_ERROR;
        }

        skillRepo.delete(skill.get());
        return null;
    }

    public List<String> validTypes(){
        return new ArrayList<String>(Skill.validTypes);
    }

}
