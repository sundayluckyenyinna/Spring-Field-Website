package com.springfield.website.modules.loan.repository;

import com.springfield.website.modules.loan.model.LoanApplication;
import com.springfield.website.modules.loan.model.LoanApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long>, JpaSpecificationExecutor<LoanApplication> {

    LoanApplication findFirstByEmailAndBusinessNameAndStatusIn(String email, String businessName, List<LoanApplicationStatus> statuses);
}
