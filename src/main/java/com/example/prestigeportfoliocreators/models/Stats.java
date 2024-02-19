package com.example.prestigeportfoliocreators.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stats")
public class Stats {
    @Id
    private Integer id = 1;

    @Column(name = "date")
    private String date;

    @Column(name = "views")
    private Long views;
}
