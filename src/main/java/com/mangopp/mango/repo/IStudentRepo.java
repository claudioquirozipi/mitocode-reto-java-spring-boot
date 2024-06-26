package com.mangopp.mango.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangopp.mango.model.Student;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Integer> {

}