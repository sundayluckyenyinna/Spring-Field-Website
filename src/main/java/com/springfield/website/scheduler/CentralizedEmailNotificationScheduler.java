package com.springfield.website.scheduler;

import com.springfield.website.modules.generic.email.EmailService;
import com.springfield.website.modules.generic.email.payload.EmailResponseData;
import com.springfield.website.modules.generic.email.payload.SimpleEmailRequestPayload;
import com.springfield.website.modules.generic.model.EmailNotification;
import com.springfield.website.modules.generic.model.EmailNotificationStatus;
import com.springfield.website.modules.generic.repository.EmailRepository;
import com.springfield.website.utils.DateUtils;
import com.springfield.website.utils.StringValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class CentralizedEmailNotificationScheduler {

    private final EmailService emailService;
    private final EmailRepository emailRepository;

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void processPendingEmailNotificationRequest(){
        List<EmailNotification> pendingEmails = emailRepository.findAllByStatus(EmailNotificationStatus.PENDING);
        pendingEmails.forEach(this::processPendingEmailNotification);
    }

    private void processPendingEmailNotification(EmailNotification emailNotification){
        try {
            SimpleEmailRequestPayload requestPayload = SimpleEmailRequestPayload.builder()
                    .recipients(List.of(emailNotification.getRecipientEmails().split(StringValues.COMMA)))
                    .bcc(List.of(emailNotification.getBccs().split(StringValues.COMMA)))
                    .cc(List.of(emailNotification.getCccs().split(StringValues.COMMA)))
                    .message(emailNotification.getMessage())
                    .htmlMessage(emailNotification.getMessage())
                    .subject(emailNotification.getSubject())
                    .from(emailNotification.getFromMail())
                    .attachments(new ArrayList<>())
                    .build();
            EmailResponseData emailResponseData = emailService.processSendSimpleEmail(requestPayload);
            log.info("Email response Data: {}", emailResponseData);

            if(emailResponseData.isSuccess()) {
                emailNotification.setStatus(EmailNotificationStatus.DELIVERED);
            }else {
                emailNotification.setStatus(EmailNotificationStatus.FAILED);
            }
            emailNotification.setUpdatedAt(DateUtils.getCurrentDateTime());
            emailRepository.saveAndFlush(emailNotification);
        }catch (Exception exception){
            log.error("Exception occurred while processing email. Exception message is: {}", exception.getMessage());
            emailNotification.setStatus(EmailNotificationStatus.FAILED);
            emailNotification.setUpdatedAt(DateUtils.getCurrentDateTime());
            emailRepository.saveAndFlush(emailNotification);
        }
    }
}
