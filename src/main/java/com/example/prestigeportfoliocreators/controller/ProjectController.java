package com.example.prestigeportfoliocreators.controller;

import com.example.prestigeportfoliocreators.models.Projects;
import com.example.prestigeportfoliocreators.service.ProjectService;
import com.example.prestigeportfoliocreators.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(path = "/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping(path = "/new")
    public ResponseEntity<HashMap<String,Object>> newProject(@RequestBody Projects projects){
        projectService.newProject(projects);
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<HashMap<String,Object>> getProjects(){
        List<Projects> projects = projectService.getProjects();
        return new ResponseEntity<>(Response.createBody("projects ", projects), HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{id}")
    public ResponseEntity<HashMap<String,Object>> updateProject(@RequestBody Projects updateProject, @PathVariable("id") Long id){
        String errMassage = projectService.updateProject(updateProject,id);
        if (errMassage != null){
            return Response.errorMessage(errMassage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HashMap<String,Object>> deleteProject(@PathVariable("id")Long id){
        String errMessage = projectService.deleteProject(id);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @PostMapping(path = "{id}/skill/new")
    public ResponseEntity<HashMap<String,Object>> newProjectSkill(@PathVariable("id")Long id,
                                                                  @RequestParam(required = true) String skillName){
        String errMessage = projectService.newProjectSkill(skillName,id);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}/skill/delete")
    public ResponseEntity<HashMap<String, Object>> deleteProjectSkill(@PathVariable("id")Long id,
                                                                      @RequestParam(required = true)String skillName){
        String errMessage = projectService.deleteProjectSkill(skillName,id);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }
}
