package com.example.prestigeportfoliocreators.models;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "about_me")
public class AboutMe {
    @Id
    private Integer id;

    @Column(name = "text")
    @Size(max = 1000, message = CustomErrors.ABOUT_ME_TEXT_ERROR)
    @NotBlank(message = CustomErrors.ABOUT_ME_TEXT_ERROR)
    private String text;
}
