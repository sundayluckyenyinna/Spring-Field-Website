package com.springfield.website.modules.generic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.appuser.entities.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "email_notification")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String fromMail;

    @Column(nullable = false, columnDefinition = "text")
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false)
    private AppUser appUser;

    @Column(nullable = false)
    private String recipientEmails;

    private String bccs;

    private String cccs;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EmailNotificationStatus status;

    private String failureReason;

    @Column(nullable = false)
    private String requestId;
}
