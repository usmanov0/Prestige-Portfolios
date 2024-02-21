package com.example.prestigeportfoliocreators.repository;

import com.example.prestigeportfoliocreators.models.AboutMe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutMeRepository extends JpaRepository<AboutMe, Long> {
}
