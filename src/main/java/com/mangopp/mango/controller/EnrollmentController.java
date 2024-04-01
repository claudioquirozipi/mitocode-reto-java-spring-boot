package com.mangopp.mango.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mangopp.mango.dto.enrollment.EnrollmentCreateDTO;
import com.mangopp.mango.dto.enrollment.EnrollmentEditDTO;
import com.mangopp.mango.model.Enrollment;
import com.mangopp.mango.service.EnrollmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping()
    public List<Enrollment> getEnollement() {
        return enrollmentService.getEnollement();
    }

    @GetMapping("/map")
    public Map<String, List<String>> getEnollementMap() {
        return enrollmentService.getEnollementMap();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollmentById(@PathVariable int id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @PostMapping()
    public String addEnollement(@Valid @RequestBody EnrollmentCreateDTO enrollmentCreateDTO) {
        return enrollmentService.addEnollement(enrollmentCreateDTO);
    }

    @PutMapping("/{id}")
    public String updateCourse(@PathVariable int id, @Valid @RequestBody EnrollmentEditDTO enrollmentEditDTO) {
        return enrollmentService.updateEnrollment(id, enrollmentEditDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {
        return enrollmentService.deleteCourse(id);
    }
}
