package com.springfield.website.modules.generic.repository;

import com.springfield.website.modules.generic.model.EmailNotification;
import com.springfield.website.modules.generic.model.EmailNotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailNotification, Long> {

    List<EmailNotification> findAllByStatus(EmailNotificationStatus status);
}
