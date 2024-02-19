package com.example.prestigeportfoliocreators.models;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skills")
public class Skill {
    public static final HashSet<String> validTypes = new HashSet<>(Arrays.asList("Language", "Framework/Library", "Database", "Software"));


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", unique = true)
    @NotBlank(message = CustomErrors.SKILL_NAME_ERROR)
    private String name;

    @Column(name = "type")
    @NotBlank(message = CustomErrors.SKILL_TYPE_ERROR)
    private String type;

    @Column(name = "simple_icons_icon_slug")
    @Size(max = 50, message = CustomErrors.SKILL_SIMPLE_ICONS_ICON_SLUG_ERROR)
    private String simpleIconsIconSlug;

    @Column(name = "projects")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "skill_project",
            joinColumns = @JoinColumn(name = "skill_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private final Set<Projects> projects = new HashSet<>();

    public boolean checkValidType(){
        return validTypes.contains(type);
    }
}
