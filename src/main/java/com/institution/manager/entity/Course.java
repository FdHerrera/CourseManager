package com.institution.manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank(message = "Course's name is required")
    private String courseName;
    @OneToMany(targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    private List<Student> students;
    @ManyToOne(targetEntity = Professor.class)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public Course(String courseName) {
        this.courseName = courseName;
    }
}
