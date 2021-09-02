package com.institution.manager.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(targetEntity = Student.class)
    @JoinColumn(name = "student_id")
    private List<Student> students;
    @ManyToOne(targetEntity = Professor.class)
    @JoinColumn(name = "professor_id")
    private Professor professor;

}
