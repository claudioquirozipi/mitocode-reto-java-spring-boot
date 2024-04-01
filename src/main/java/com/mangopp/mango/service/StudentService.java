package com.mangopp.mango.service;

import org.springframework.stereotype.Service;

import com.mangopp.mango.dto.student.StudentCreateDTO;
import com.mangopp.mango.dto.student.StudentEditDTO;
import com.mangopp.mango.exception.CustomException;
import com.mangopp.mango.model.Student;
import com.mangopp.mango.repo.IStudentRepo;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final IStudentRepo studentRepo;

    public StudentService(IStudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents() {
        try {
            return this.studentRepo.findAll();
        } catch (Exception e) {
            throw new CustomException("Error fetching students");
        }
    }

    public Student getStudentById(int id) {
        try {
            Student student = this.studentRepo.findById(id).orElse(null);
            if (student == null) {
                throw new CustomException("Student with ID " + id + " not found");
            }
            return student;
        } catch (Exception e) {
            throw new CustomException("Error fetching student with ID " + id);
        }
    }

    public String addStudent(StudentCreateDTO studentCreateDTO) {
        try {
            Student student = new Student();
            student.setName(studentCreateDTO.getName());
            student.setLastName(studentCreateDTO.getLastName());
            student.setDni(studentCreateDTO.getDni());
            student.setAge(studentCreateDTO.getAge());

            this.studentRepo.save(student);
            return "Student added";

        } catch (Exception e) {
            throw new CustomException("Error adding student");
        }
    }

    public String updateStudent(int id, StudentEditDTO studentEditDTO) {
        try {
            Optional<Student> optionalStudent = studentRepo.findById(id);
            if (optionalStudent.isPresent()) {
                Student existingStudent = optionalStudent.get();
                existingStudent.setName(studentEditDTO.getName());
                existingStudent.setLastName(studentEditDTO.getLastName());
                existingStudent.setDni(studentEditDTO.getDni());
                existingStudent.setAge(studentEditDTO.getAge());
                studentRepo.save(existingStudent);
                return "Student updated";
            } else {
                throw new CustomException("Student with ID " + id + " not found");
            }
        } catch (Exception e) {
            throw new CustomException("Error updating student with ID " + id);
        }
    }

    public String deleteStudent(int id) {
        try {
            Optional<Student> optionalStudent = studentRepo.findById(id);
            if (optionalStudent.isPresent()) {
                this.studentRepo.deleteById(id);
                return "Student deleted";
            } else {
                throw new CustomException("Student with ID " + id + " not found");
            }
        } catch (Exception e) {
            throw new CustomException("Error deleting student with ID " + id);
        }
    }

}
