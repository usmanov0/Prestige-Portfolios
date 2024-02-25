package com.example.prestigeportfoliocreators.controller;

import com.example.prestigeportfoliocreators.models.AboutMe;
import com.example.prestigeportfoliocreators.service.AboutMeService;
import com.example.prestigeportfoliocreators.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/about_me")
public class AboutMeController {
    @Autowired
    public AboutMeService aboutMeService;

    @PutMapping(path = "/update")
    @CrossOrigin
    public ResponseEntity<HashMap<String,Object>> setAboutMe(HttpServletRequest request, @Valid @RequestBody AboutMe aboutMe){
        aboutMeService.setAboutMe(aboutMe);
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<HashMap<String,Object>> getAboutMe(HttpServletRequest request){
        return new ResponseEntity<>(Response.createBody("text", aboutMeService.getAboutMe()), HttpStatus.OK);
    }
}
