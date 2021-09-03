package com.institution.manager.service.impl;

import com.institution.manager.dto.request.NewUserDto;
import com.institution.manager.entity.Professor;
import com.institution.manager.entity.Student;
import com.institution.manager.entity.User;
import com.institution.manager.enumerate.ERole;
import com.institution.manager.exception.UserIsNotAProfessorException;
import com.institution.manager.exception.UserNotFoundException;
import com.institution.manager.repo.ProfessorRepo;
import com.institution.manager.repo.StudentRepo;
import com.institution.manager.service.interf.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final StudentRepo studentRepo;
    private final ProfessorRepo professorRepo;
    @Autowired
    private final BCryptPasswordEncoder encoder;
    @Autowired
    private final MessageSource messageSource;

    @Override
    public Professor createProfessor(NewUserDto newUserDto){
        String encodedPass = encoder.encode(newUserDto.getPassword());
        SimpleGrantedAuthority professorAuthority = new SimpleGrantedAuthority(ERole.ROLE_PROFESSOR.name());
        Professor newProfessor = Professor.builder()
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .email(newUserDto.getEmail())
                .password(encodedPass)
                .phoneNumber(newUserDto.getPhoneNumber())
                .dni(newUserDto.getDni())
                .authorities(Collections.singleton(professorAuthority))
                .build();
        return professorRepo.save(newProfessor);
    }

    @Override
    public Student createStudent(NewUserDto newUserDto){
        String encodedPass = encoder.encode(newUserDto.getPassword());
        SimpleGrantedAuthority studentAuthority = new SimpleGrantedAuthority(ERole.ROLE_STUDENT.name());
        Student newStudent = Student.builder()
                .firstName(newUserDto.getFirstName())
                .lastName(newUserDto.getLastName())
                .email(newUserDto.getEmail())
                .password(encodedPass)
                .phoneNumber(newUserDto.getPhoneNumber())
                .dni(newUserDto.getDni())
                .authorities(Collections.singleton(studentAuthority))
                .build();
        return studentRepo.save(newStudent);
    }

    @Override
    public User findUser(String email) throws UserNotFoundException {
        Optional<Student> studentFound = studentRepo.findByEmail(email);
        Optional<Professor> professorFound = professorRepo.findByEmail(email);
        if(studentFound.isPresent())
            return studentFound.get();
        else if(professorFound.isPresent())
            return professorFound.get();
        else
            throw new UserNotFoundException(messageSource.getMessage("error.user.not.found", null, Locale.getDefault()));
    }

    @Override
    public boolean checkIfIsProfessor(User user) throws UserIsNotAProfessorException {
        boolean isProfessor = user.getAuthorities().stream().anyMatch(
                grantedAuthority -> grantedAuthority.getAuthority().equals(ERole.ROLE_PROFESSOR.name())
        );
        if (isProfessor)
            return true;
        else {
            throw new UserIsNotAProfessorException(messageSource.getMessage("error.user.is.not.professor", null, Locale.getDefault()));
        }
    }

}
