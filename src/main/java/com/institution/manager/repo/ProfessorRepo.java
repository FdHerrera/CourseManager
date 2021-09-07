package com.institution.manager.repo;

import com.institution.manager.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);
    @Modifying
    @Query("UPDATE Professor p SET enabled = TRUE WHERE id = ?1")
    void enableProfessor(Long id);
}
