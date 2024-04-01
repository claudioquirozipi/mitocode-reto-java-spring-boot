package com.mangopp.mango.service;

import org.springframework.stereotype.Service;

import com.mangopp.mango.dto.course.CourseCreateDTO;
import com.mangopp.mango.dto.course.CourseEditDTO;
import com.mangopp.mango.exception.CustomException;
import com.mangopp.mango.model.Course;
import com.mangopp.mango.repo.ICourseRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final ICourseRepo courseRepo;

    public CourseService(ICourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getCourses() {
        try {
            return this.courseRepo.findAll();
        } catch (Exception e) {
            throw new CustomException("Error fetching courses");
        }
    }

    public Course getCourseById(int id) {
        try {
            Course course = this.courseRepo.findById(id).orElse(null);
            if (course == null) {
                throw new CustomException("Course with ID " + id + " not found");
            }
            return course;
        } catch (Exception e) {
            throw new CustomException("Error fetching course with ID " + id);
        }
    }

    public String addCourse(CourseCreateDTO courseCreateDTO) {
        try {
            Course course = new Course();
            course.setName(courseCreateDTO.getName());
            course.setAcronym(courseCreateDTO.getAcronym());
            course.setStatus(courseCreateDTO.getStatus());

            this.courseRepo.save(course);
            return "Course added";

        } catch (Exception e) {
            throw new CustomException("Error adding course");
        }
    }

    public String updateCourse(int id, CourseEditDTO courseEditDTO) {
        Optional<Course> optionalCourse = courseRepo.findById(id);
        if (optionalCourse.isPresent()) {
            Course existingCourse = optionalCourse.get();
            existingCourse.setName(courseEditDTO.getName());
            existingCourse.setAcronym(courseEditDTO.getAcronym());
            existingCourse.setStatus(courseEditDTO.getStatus());
            courseRepo.save(existingCourse);
            return "Course updated";
        } else {
            throw new CustomException("Course with ID " + id + " not found");
        }

    }

    public String deleteCourse(int id) {
        try {
            Optional<Course> optionalCourse = courseRepo.findById(id);
            if (optionalCourse.isPresent()) {
                this.courseRepo.deleteById(id);
                return "Course deleted";
            } else {
                throw new CustomException("Course with ID " + id + " not found");
            }
        } catch (Exception e) {
            throw new CustomException("Error deleting course with ID " + id);
        }
    }
}
