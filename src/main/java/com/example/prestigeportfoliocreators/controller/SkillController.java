package com.example.prestigeportfoliocreators.controller;

import com.example.prestigeportfoliocreators.models.Skill;
import com.example.prestigeportfoliocreators.service.SkillService;
import com.example.prestigeportfoliocreators.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(path = "/skills")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @PostMapping(path = "/new")
    public ResponseEntity<HashMap<String,Object>> newSkill(@RequestBody Skill skill){
        String errMessage = skillService.newSkill(skill);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<HashMap<String,Object>> getSkills(){
        HashMap<String , List<Skill>> skills = skillService.getSkills();
        return new ResponseEntity<>(Response.createBody("skills",skills), HttpStatus.OK);
    }

    @GetMapping(path = "/valid")
    public ResponseEntity<HashMap<String,Object>> validTypes(){
        List<String> validTypes = skillService.validTypes();
        return new ResponseEntity<>(Response.createBody("validTypes",validTypes), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete{name}")
    public ResponseEntity<HashMap<String,Object>> deleteSkill(@PathVariable("name") String skillName){
        String errMessage = skillService.deleteSkill(skillName);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }
}
