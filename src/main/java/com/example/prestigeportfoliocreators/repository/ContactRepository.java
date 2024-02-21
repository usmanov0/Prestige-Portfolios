package com.example.prestigeportfoliocreators.repository;

import com.example.prestigeportfoliocreators.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Message,Long> {
    @Query(value = "SELECT * FROM messages ORDER BY date DESC", nativeQuery = true)
    public Page<Message> findAllWithPaginationOrderedByDate(Pageable pageable);
}
