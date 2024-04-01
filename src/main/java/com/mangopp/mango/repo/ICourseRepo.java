package com.mangopp.mango.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangopp.mango.model.Course;

@Repository
public interface ICourseRepo extends JpaRepository<Course, Integer> {

}