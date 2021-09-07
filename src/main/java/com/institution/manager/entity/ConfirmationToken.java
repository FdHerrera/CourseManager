package com.institution.manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = false)
    private Date expiresAt;
    private Date confirmedAt;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    public ConfirmationToken(String token, Date createdAt, Date expiresAt, Student student) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.student = student;
    }

    public ConfirmationToken(String token, Date createdAt, Date expiresAt, Professor professor) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.professor = professor;
    }
}
