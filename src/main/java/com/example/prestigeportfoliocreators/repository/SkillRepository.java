package com.example.prestigeportfoliocreators.repository;

import com.example.prestigeportfoliocreators.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query(value = "SELECT * FROM  skills WHERE type = :type ORDER BY name ASC", nativeQuery = true)
    public List<Skill> findAllSkillNameByType(@Param("type") String type);

    @Query(value = "SELECT * FROM skills WHERE name = :name", nativeQuery = true)
    public Optional<Skill> findSkillByName(@Param("name") String name);
}
