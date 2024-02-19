package com.example.prestigeportfoliocreators.models;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects", indexes = @Index(name = "date_order_ind", columnList = "date_order"))
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 30, message = CustomErrors.PROJECT_NAME_ERROR)
    @NotBlank(message = CustomErrors.PROJECT_NAME_ERROR)
    private String name;

    @Column(name = "description")
    @Size(min = 10, max = 250, message = CustomErrors.PROJECT_DESCRIPTION_ERROR)
    @NotBlank(message = CustomErrors.PROJECT_DESCRIPTION_ERROR)
    private String description;

    @Column(name = "start_date")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/20[0-9]{2}$", message = CustomErrors.PROJECT_START_DATE_ERROR)
    @NotBlank(message = CustomErrors.PROJECT_START_DATE_ERROR)
    private String startDate;

    @Column(name = "end_date")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/20[0-9]{2}$", message = CustomErrors.PROJECT_END_DATE_ERROR)
    @NotBlank(message = CustomErrors.PROJECT_END_DATE_ERROR)
    private String endDate;

    @Column(name = "date_order")
    private String dateOrder;

    @Column(name = "skills")
    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
        private Set<Skill> skills = new HashSet<>();


    @Column(name = "link")
    @Size(min = 4, max = 250, message = CustomErrors.PROJECT_LINK_ERROR)
    @NotBlank(message = CustomErrors.PROJECT_LINK_ERROR)
    private String link;

    public void createOrder(){
        StringBuilder sb = new StringBuilder(14);
        sb.append(startDate.substring(3));
        sb.append(startDate.substring(0,2));
        sb.append(endDate.substring(3));
        sb.append(endDate.substring(0,2));
        this.dateOrder = sb.toString();
    }

    public void addSkill(Skill skill){
        skill.getProjects().add(this);
        this.skills.add(skill);
    }

    public void deleteSkill(Skill skill){
        skill.getProjects().remove(this);
        this.skills.remove(skill);
    }
}
