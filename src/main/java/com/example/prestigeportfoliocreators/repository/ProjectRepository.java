package com.example.prestigeportfoliocreators.repository;

import com.example.prestigeportfoliocreators.models.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects,Long> {
    @Query(value = "SELECT * FROM projects ORDER BY date_order DESC", nativeQuery = true)
    public List<Projects> findAllByStartDateEndDate();
}
