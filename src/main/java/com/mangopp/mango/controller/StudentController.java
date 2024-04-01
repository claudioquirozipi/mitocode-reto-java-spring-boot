package com.mangopp.mango.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mangopp.mango.dto.student.StudentCreateDTO;
import com.mangopp.mango.dto.student.StudentEditDTO;
import com.mangopp.mango.model.Student;
import com.mangopp.mango.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping()
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @PostMapping()
    public String addStudent(@Valid @RequestBody StudentCreateDTO studentCreateDTO) {
        return studentService.addStudent(studentCreateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable int id,
            @Valid @RequestBody StudentEditDTO studentEditDTO) {
        String response = studentService.updateStudent(id, studentEditDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
