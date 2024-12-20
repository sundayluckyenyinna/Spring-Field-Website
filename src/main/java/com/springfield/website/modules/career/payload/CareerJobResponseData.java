package com.springfield.website.modules.career.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.career.model.CareerJob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CareerJobResponseData {

    private String title; // Job title e.g., Manager
    private String location; // Job location e.g., Lagos - Nigeria
    private String type; // Job type e.g., Full-time
    private String department; // Department e.g., Engineering
    private String salaryRange; // Salary range e.g., $50,000 - $70,000
    private String experienceLevel; // Experience level e.g., Mid-Level
    private String jobDescription; // Key responsibilities and tasks
    private String requirements; // Skills, qualifications, certifications
    private String educationLevel; // Education level e.g., Bachelor’s Degree
    private String applicationDeadline; // Application deadline
    private String companyName; // Name of the company
    private String companyOverview; // Brief description of the company
    private String benefits; // Job benefits e.g., Health insurance
    private String workingHours; // Working hours e.g., 9 AM - 5 PM
    private String reportingManager; // Manager to report to
    private String postedDate; // Date job was posted
    private String contractDuration; // Contract duration e.g., Permanent
    private String jobCategory; // Category e.g., IT, Finance
    private String travelRequirements; // Travel requirements e.g., None
    private String languagesRequired; // Required languages e.g., English
    private String applicationMethod; // How to apply e.g., Online
    private Integer numberOfOpenings; // Number of available positions
    private Boolean remoteEligibility; // Eligibility for remote work

    public static CareerJobResponseData fromCareerJob(CareerJob careerJob){
        CareerJobResponseData careerJobResponseData = new CareerJobResponseData();
        BeanUtils.copyProperties(careerJob, careerJobResponseData);
        if(Objects.nonNull(careerJob.getApplicationDeadline())) {
            careerJobResponseData.setApplicationDeadline(careerJob.getApplicationDeadline().toString());
        }
        if(Objects.nonNull(careerJob.getPostedDate())){
            careerJobResponseData.setPostedDate(careerJob.getPostedDate().toString());
        }
        return careerJobResponseData;
    }
}
