package com.institution.manager.repo;

import com.institution.manager.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepo extends JpaRepository<Professor, Long> {
    Optional<Professor> findByEmail(String email);
}
