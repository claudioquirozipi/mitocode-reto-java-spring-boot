package com.mangopp.mango.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangopp.mango.model.Enrollment;

@Repository
public interface IEnrollmentRepo extends JpaRepository<Enrollment, Integer> {

}
