package com.institution.manager.entity;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Student extends User{
    public Student(String firstName, String lastName, String email, String dni, String password, String phoneNumber, Date createdAt) {
        super(firstName, lastName, email, dni, password, phoneNumber, createdAt);
    }
}
