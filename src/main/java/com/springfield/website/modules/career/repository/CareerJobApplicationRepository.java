package com.springfield.website.modules.career.repository;

import com.springfield.website.modules.career.model.CareerJobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerJobApplicationRepository extends JpaRepository<CareerJobApplication, Long>, JpaSpecificationExecutor<CareerJobApplication> {
}
