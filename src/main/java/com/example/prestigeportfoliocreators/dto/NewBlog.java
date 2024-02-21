package com.example.prestigeportfoliocreators.dto;


import com.example.prestigeportfoliocreators.errors.CustomErrors;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewBlog {
    @Size(min = 3, max = 250, message = CustomErrors.BLOG_TITLE_ERROR)
    @NotBlank(message = CustomErrors.BLOG_TITLE_ERROR)
    private String title;

    @Size(min = 3, max = 500, message = CustomErrors.BLOG_DESCRIPTION_ERROR)
    @NotBlank(message = CustomErrors.BLOG_DESCRIPTION_ERROR)
    private String description;

    @Size(max = 5000, message = CustomErrors.BLOG_BODY_ERROR)
    @NotBlank(message = CustomErrors.BLOG_BODY_ERROR)
    private String body;
}
