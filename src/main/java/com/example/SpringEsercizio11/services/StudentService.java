package com.example.SpringEsercizio11.services;

import com.example.SpringEsercizio11.entities.Student;
import com.example.SpringEsercizio11.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public void setStudentRepository(Long studenteId, boolean isWorking){
        Optional<Student> studente = studentRepository.findById(studenteId);
        if (studente.isEmpty()){
            return;
        } else {
            studente.get().setWorking(isWorking);
            studentRepository.saveAndFlush(studente.get());
        }

    }
}
