package com.mangopp.mango.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mangopp.mango.model.EnrollmentDetail;

@Repository
public interface IEnrollmentDetailRepo extends JpaRepository<EnrollmentDetail, Integer> {

}
