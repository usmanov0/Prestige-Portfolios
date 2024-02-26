package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import com.example.prestigeportfoliocreators.models.Projects;
import com.example.prestigeportfoliocreators.models.Skill;
import com.example.prestigeportfoliocreators.repository.ProjectRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private SkillService skillService;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public void newProject(Projects project){
        project.createOrder();
        projectRepo.save(project);
    }

    public List<Projects> getProjects(){
        return projectRepo.findAllByStartDateEndDate();
    }

    public String updateProject(Projects updateProject,  Long id){
        Optional<Projects> optional = projectRepo.findById(id);
        if (!optional.isPresent()){
            return CustomErrors.PROJECT_ID_ERROR;
        }
        Projects projects = optional.get();
        projects.setName(updateProject.getName());
        projects.setDescription(updateProject.getDescription());
        projects.setStartDate(updateProject.getStartDate());
        projects.setEndDate(updateProject.getEndDate());
        projects.setLink(updateProject.getLink());

        Set<ConstraintViolation<Projects>> violations = validator.validate(projects);
        if (!violations.isEmpty()){
            return "Validation error: " + violations.toString();
        }

        projectRepo.save(projects);
        return null;
    }

    public String deleteProject(Long id){
        Optional<Projects> optional = projectRepo.findById(id);
        if (!optional.isPresent()){
            return CustomErrors.PROJECT_ID_ERROR;
        }

        Projects projects = optional.get();
        Set<Skill> skills = projects.getSkills();
        for (Skill skill : skills){
            skill.getProjects().remove(projects);
        }
        skills.clear();
        projectRepo.delete(projects);
        return null;
    }

    public String newProjectSkill(String skillName, Long id){
        Optional<Projects> projectsOptional = projectRepo.findById(id);
        if (!projectsOptional.isPresent()){
            return CustomErrors.PROJECT_ID_ERROR;
        }

        Optional<Skill> skillOptional = skillService.getSkillByName(skillName);
        if (!skillOptional.isPresent()){
            return CustomErrors.SKILL_NOT_FOUND_ERROR;
        }
        Projects projects = projectsOptional.get();
        projects.addSkill(skillOptional.get());
        projectRepo.save(projects);
        return null;
    }

    public String deleteProjectSkill(String skillName, Long id){
        Optional<Projects> projects = projectRepo.findById(id);
        if (!projects.isPresent()){
            return CustomErrors.PROJECT_ID_ERROR;
        }

        Optional<Skill> skill = skillService.getSkillByName(skillName);
        if (!skill.isPresent()){
            return CustomErrors.SKILL_NOT_FOUND_ERROR;
        }
        Projects project = projects.get();
        project.deleteSkill(skill.get());
        projectRepo.save(project);
        return null;
    }
}
