package com.example.prestigeportfoliocreators.controller;

import com.example.prestigeportfoliocreators.models.AboutMe;
import com.example.prestigeportfoliocreators.service.AboutMeService;
import com.example.prestigeportfoliocreators.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/about_me")
public class AboutMeController {
    @Autowired
    public AboutMeService aboutMeService;

    @PutMapping(path = "/update")
    public ResponseEntity<HashMap<String,Object>> setAboutMe(@Valid @RequestBody AboutMe aboutMe){
        aboutMeService.setAboutMe(aboutMe);
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<HashMap<String,Object>> getAboutMe(){
        return new ResponseEntity<>(Response.createBody("text", aboutMeService.getAboutMe()), HttpStatus.OK);
    }
}
