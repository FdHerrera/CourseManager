package com.institution.manager.repo;

import com.institution.manager.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);

    @Modifying
    @Query("UPDATE Student s SET enabled = TRUE WHERE id = ?1")
    void enableStudent(Long id);
}
