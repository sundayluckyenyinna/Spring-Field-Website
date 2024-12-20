package com.springfield.website.modules.career.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.OmnixPage;
import com.springfield.website.common.PageParameters;
import com.springfield.website.modules.career.model.CareerJob;
import com.springfield.website.modules.career.model.CareerJobApplication;
import com.springfield.website.modules.career.model.JobApplicationStatus;
import com.springfield.website.modules.career.payload.CareerJobResponseData;
import com.springfield.website.modules.career.payload.JobApplicationPayload;
import com.springfield.website.modules.career.repository.CareerJobApplicationRepository;
import com.springfield.website.modules.career.repository.CareerJobRepository;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.DateUtils;
import com.springfield.website.utils.HttpUtil;
import com.springfield.website.utils.StringValues;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService{

    private final CareerJobRepository careerJobRepository;
    private final CareerJobApplicationRepository careerJobApplicationRepository;


    @Override
    public OmnixApiResponse<OmnixPage<CareerJobResponseData>> processFetchCareerJob(PageParameters pageParameters, String searchKey){
        try{
            Specification<CareerJob> careerJobSpecification = ((root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if(CommonUtil.nonNullAndEmpty(searchKey)){
                    List<String> careerJobFields = CommonUtil.getNamesOfFieldsOfDataType(CareerJob.class, String.class);
                    List<Predicate> searchPredicateList = careerJobFields.stream()
                                    .map(fieldName -> {
                                        String sqlSearchKey = StringValues.PERCENT.concat(searchKey.toLowerCase()).concat(StringValues.PERCENT);
                                        return criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldName)), sqlSearchKey);
                                    })
                                    .toList();
                    predicates.add(criteriaBuilder.or(searchPredicateList.toArray(new Predicate[0])));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            });
            Pageable pageable = PageRequest.of(pageParameters.getPageNumber(), pageParameters.getPageSize());
            Page<CareerJob> careerJobPage = careerJobRepository.findAll(careerJobSpecification, pageable);
            Page<CareerJobResponseData> careerJobResponseData = careerJobPage.map(CareerJobResponseData::fromCareerJob);
            OmnixPage<CareerJobResponseData> responseData = OmnixPage.from(careerJobResponseData);
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            throw HttpUtil.getResolvedException(e);
        }
    }

    @Override
    public OmnixApiResponse<JobApplicationPayload.JobApplicationResponsePayload> processJobApplicationSubmission(JobApplicationPayload.JobApplicationRequestPayload requestPayload){
        try{
            CareerJobApplication jobApplication = CareerJobApplication.builder()
                    .guid(CommonUtil.generateGuid())
                    .fullName(requestPayload.getFullName())
                    .email(requestPayload.getEmail())
                    .mobileNumber(requestPayload.getMobileNumber())
                    .resume(requestPayload.getResumeLink())
                    .createdAt(DateUtils.getCurrentDateTime())
                    .updatedAt(DateUtils.getCurrentDateTime())
                    .status(JobApplicationStatus.PENDING)
                    .build();
            CareerJobApplication savedApplication = careerJobApplicationRepository.saveAndFlush(jobApplication);
            log.info("Created career job application with ID: {}", savedApplication.getId());
            JobApplicationPayload.JobApplicationResponsePayload responseData = JobApplicationPayload.JobApplicationResponsePayload.fromJobApplication(savedApplication);
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            throw HttpUtil.getResolvedException(e);
        }
    }
}
