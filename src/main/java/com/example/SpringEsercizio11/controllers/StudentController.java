package com.example.SpringEsercizio11.controllers;

import com.example.SpringEsercizio11.entities.Student;
import com.example.SpringEsercizio11.repositories.StudentRepository;
import com.example.SpringEsercizio11.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/v1")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @PostMapping(path = "/createStudent")
    public @ResponseBody Student crea(@RequestBody Student studente){
        return studentRepository.saveAndFlush(studente);
    }
    @GetMapping(path = "/getAll")
    public @ResponseBody List<Student> getAll(){
        return studentRepository.findAll();
    }
    @GetMapping(path = "/getSingle/{id}")
    public @ResponseBody Student getSingle(@PathVariable long id){
        Optional<Student> studente = studentRepository.findById(id);
        if(studente.isEmpty()){
            return studente.get();
        } else {
            return null;
        }
    }
    @PutMapping(path = "/patch/{id}")
    public @ResponseBody Student updateId (@PathVariable long id,@RequestBody Student student){
        student.setId(id);
        return studentRepository.saveAndFlush(student);
    }

    @PutMapping(path = "/{id}/isWorking" )
    public void setWorking(@PathVariable long id, @RequestParam boolean isWorking){
        studentService.setStudentRepository(id, isWorking);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteId(@PathVariable long id){
        studentRepository.deleteById(id);
        return true;
    }

}
