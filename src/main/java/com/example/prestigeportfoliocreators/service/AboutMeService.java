package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import com.example.prestigeportfoliocreators.models.AboutMe;
import com.example.prestigeportfoliocreators.repository.AboutMeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AboutMeService {
    @Autowired
    private AboutMeRepository aboutMeRepo;

    public void setAboutMe(AboutMe aboutMe){
        aboutMeRepo.save(aboutMe);
    }

    public String getAboutMe(){
        Optional<AboutMe> optional = aboutMeRepo.findById(1L);
        if (!optional.isPresent()){
            return CustomErrors.ABOUT_ME_LOAD_ERROR;
        }
        return optional.get().getText();
    }
}
