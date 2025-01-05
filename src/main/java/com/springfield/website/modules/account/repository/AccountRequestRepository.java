package com.springfield.website.modules.account.repository;

import com.springfield.website.modules.account.entities.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {

    List<AccountRequest> findAllByPhoneNumber(String phoneNumber);
}
