package com.mangopp.mango.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mangopp.mango.dto.enrollment.EnrollmentCreateDTO;
import com.mangopp.mango.dto.enrollment.EnrollmentDetailDTO;
import com.mangopp.mango.dto.enrollment.EnrollmentEditDTO;
import com.mangopp.mango.exception.CustomException;
import com.mangopp.mango.model.Course;
import com.mangopp.mango.model.Enrollment;
import com.mangopp.mango.model.EnrollmentDetail;
import com.mangopp.mango.model.Student;
import com.mangopp.mango.repo.ICourseRepo;
import com.mangopp.mango.repo.IEnrollmentRepo;
import com.mangopp.mango.repo.IStudentRepo;

@Service
public class EnrollmentService {
    private final IStudentRepo studentRepo;
    private final IEnrollmentRepo enrollmentRepo;

    private final ICourseRepo courseRepo;

    public EnrollmentService(IStudentRepo studentRepo, IEnrollmentRepo enrollmentRepo,
            ICourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.enrollmentRepo = enrollmentRepo;
        this.courseRepo = courseRepo;
    }

    public List<Enrollment> getEnollement() {
        try {
            List<Enrollment> enrollments = this.enrollmentRepo.findAll();
            return enrollments.stream()
                    .sorted(Comparator.comparing(enrollment -> enrollment.getStudent().getAge(),
                            Comparator.reverseOrder()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Error fetching enrollments");
        }
    }

    public Map<String, List<String>> getEnollementMap() {
        try {
            Map<String, List<String>> courseStudentMap = new HashMap<>();
            List<Enrollment> enrollments = this.enrollmentRepo.findAll();

            enrollments.forEach(enrollment -> {
                List<EnrollmentDetail> enrollmentDetails = enrollment.getEnrollmentDetails();
                String studentName = enrollment.getStudent().getName();
                enrollmentDetails.forEach(enrollmentDetail -> {
                    String courseName = enrollmentDetail.getCourse().getName();
                    courseStudentMap.computeIfAbsent(courseName, key -> new ArrayList<>()).add(studentName);
                });
            });

            return courseStudentMap;
        } catch (Exception e) {
            throw new CustomException("Error fetching enrollments");
        }
    }

    public Enrollment getEnrollmentById(int id) {
        try {
            return this.enrollmentRepo.findById(id).orElseThrow(() -> new CustomException("Enrollment not found"));
        } catch (Exception e) {
            throw new CustomException("Error fetching enrollment");
        }
    }

    public String addEnollement(EnrollmentCreateDTO enrollmentCreateDTO) {

        try {
            Student student = this.studentRepo.findById(enrollmentCreateDTO.getStudentId()).orElse(null);
            if (student == null) {
                throw new CustomException("Student not found");
            }
            Enrollment enrollment = new Enrollment();
            enrollment.setEnrollmentDate(enrollmentCreateDTO.getEnrollmentDate());
            enrollment.setStatus(enrollmentCreateDTO.getStatus());
            enrollment.setStudent(student);

            List<EnrollmentDetailDTO> enrollmentDetailCreateDTO = enrollmentCreateDTO.getEnrollmentDetails();
            List<EnrollmentDetail> enrollmentDetails = new ArrayList<>();

            for (EnrollmentDetailDTO detailDTO : enrollmentDetailCreateDTO) {
                EnrollmentDetail enrollmentDetail = new EnrollmentDetail();
                Course course = this.courseRepo.findById(detailDTO.getCourseId()).orElse(null);

                if (course == null) {
                    throw new CustomException("Course not found");
                }
                enrollmentDetail.setCourse(course);
                enrollmentDetail.setRoom(detailDTO.getRoom());
                enrollmentDetail.setEnrollment(enrollment);
                enrollmentDetails.add(enrollmentDetail);
            }

            enrollment.setEnrollmentDetails(enrollmentDetails);
            this.enrollmentRepo.save(enrollment);

            return "Course added";
        } catch (Exception e) {
            throw new CustomException("Error adding course");
        }
    }

    public String updateEnrollment(int id, EnrollmentEditDTO enrollmentEditDTO) {
        try {
            Enrollment enrollment = this.enrollmentRepo.findById(id).orElse(null);
            if (enrollment == null) {
                throw new CustomException("Enrollment not found");
            }
            Student student = this.studentRepo.findById(enrollmentEditDTO.getStudentId()).orElse(null);
            if (student == null) {
                throw new CustomException("Student not found");
            }
            enrollment.setEnrollmentDate(enrollmentEditDTO.getEnrollmentDate());
            enrollment.setStatus(enrollmentEditDTO.getStatus());
            enrollment.setStudent(student);

            List<EnrollmentDetailDTO> enrollmentDetailDTO = enrollmentEditDTO.getEnrollmentDetails();
            List<EnrollmentDetail> enrollmentDetails = new ArrayList<>();

            for (EnrollmentDetailDTO detailDTO : enrollmentDetailDTO) {
                Course course = this.courseRepo.findById(detailDTO.getCourseId()).orElse(null);

                if (course == null) {
                    throw new CustomException("Course not found");
                }
                EnrollmentDetail existingDetail = enrollment.getEnrollmentDetails().stream()
                        .filter(detail -> Objects.equals(detail.getCourse().getId(), course.getId()))
                        .findFirst()
                        .orElse(null);

                if (existingDetail != null) {
                    existingDetail.setRoom(detailDTO.getRoom());
                    enrollmentDetails.add(existingDetail);
                } else {
                    EnrollmentDetail enrollmentDetail = new EnrollmentDetail();
                    enrollmentDetail.setCourse(course);
                    enrollmentDetail.setRoom(detailDTO.getRoom());
                    enrollmentDetail.setEnrollment(enrollment);
                    enrollmentDetails.add(enrollmentDetail);
                }
            }

            enrollment.getEnrollmentDetails().removeIf(detail -> !enrollmentDetails.contains(detail));
            this.enrollmentRepo.save(enrollment);

            return "Enrollment updated";
        } catch (Exception e) {
            throw new CustomException("Error updating enrollment");
        }

    }

    public String deleteCourse(int id) {
        try {
            Optional<Enrollment> optionalEnrollment = this.enrollmentRepo.findById(id);
            if (optionalEnrollment.isPresent()) {
                this.enrollmentRepo.deleteById(id);
                return "Enrollment deleted";
            } else {
                throw new CustomException("Enrollment with id: " + id + " not found");
            }
        } catch (Exception e) {
            throw new CustomException("Error deleting enrollment");
        }
    }
}
