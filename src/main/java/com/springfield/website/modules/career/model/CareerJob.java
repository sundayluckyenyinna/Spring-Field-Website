package com.springfield.website.modules.career.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "career_job")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CareerJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title; // Job title e.g., Manager
    private String location; // Job location e.g., Lagos - Nigeria
    private String type; // Job type e.g., Full-time
    private String department; // Department e.g., Engineering
    private String salaryRange; // Salary range e.g., $50,000 - $70,000
    private String experienceLevel; // Experience level e.g., Mid-Level
    private String jobDescription; // Key responsibilities and tasks
    private String requirements; // Skills, qualifications, certifications
    private String educationLevel; // Education level e.g., Bachelor’s Degree
    private LocalDate applicationDeadline; // Application deadline
    private String companyName; // Name of the company
    private String companyOverview; // Brief description of the company
    private String benefits; // Job benefits e.g., Health insurance
    private String workingHours; // Working hours e.g., 9 AM - 5 PM
    private String reportingManager; // Manager to report to
    private LocalDate postedDate; // Date job was posted
    private String contractDuration; // Contract duration e.g., Permanent
    private String jobCategory; // Category e.g., IT, Finance
    private String travelRequirements; // Travel requirements e.g., None
    private String languagesRequired; // Required languages e.g., English
    private String applicationMethod; // How to apply e.g., Online
    private Integer numberOfOpenings; // Number of available positions
    private Boolean remoteEligibility; // Eligibility for remote work
}
