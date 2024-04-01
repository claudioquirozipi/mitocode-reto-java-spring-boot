package com.mangopp.mango.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.mangopp.mango.dto.course.CourseCreateDTO;
import com.mangopp.mango.dto.course.CourseEditDTO;
import com.mangopp.mango.model.Course;
import com.mangopp.mango.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping()
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);

    }

    @PostMapping()
    public String addCourse(@Valid @RequestBody CourseCreateDTO courseCreateDTO) {
        return courseService.addCourse(courseCreateDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @Valid @RequestBody CourseEditDTO courseEditDTO) {
        String response = courseService.updateCourse(id, courseEditDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable int id) {
        return courseService.deleteCourse(id);
    }

}
