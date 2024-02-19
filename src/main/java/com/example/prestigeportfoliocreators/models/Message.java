package com.example.prestigeportfoliocreators.models;

import com.example.prestigeportfoliocreators.errors.CustomErrors;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = CustomErrors.MESSAGE_NAME_ERROR)
    @NotBlank(message = CustomErrors.MESSAGE_NAME_ERROR)
    private String name;

    @Column(name = "contact_info")
    @Size(min = 3, max = 100, message = CustomErrors.MESSAGE_CONTACT_INFO_ERROR)
    @NotBlank(message = CustomErrors.MESSAGE_CONTACT_INFO_ERROR)
    private String contactInfo;

    @Column(name = "body")
    @Size(min = 15, max = 1000, message = CustomErrors.MESSAGE_BODY_ERROR)
    @NotBlank(message = CustomErrors.MESSAGE_BODY_ERROR)
    private String body;

    @Column(name = "date")
    private String date;
}
