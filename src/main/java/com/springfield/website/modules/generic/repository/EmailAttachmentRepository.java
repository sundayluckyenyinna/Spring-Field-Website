package com.springfield.website.modules.generic.repository;

import com.springfield.website.modules.generic.model.EmailAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAttachmentRepository extends JpaRepository<EmailAttachment, Long> {
}
