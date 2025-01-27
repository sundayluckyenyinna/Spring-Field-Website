package com.springfield.website.modules.generic.email;

import com.springfield.website.modules.appuser.entities.AppUser;
import com.springfield.website.modules.appuser.repository.AppUserRepository;
import com.springfield.website.modules.generic.email.payload.Attachment;
import com.springfield.website.modules.generic.email.payload.EmailResponseData;
import com.springfield.website.modules.generic.email.payload.SimpleEmailRequestPayload;
import com.springfield.website.modules.generic.model.EmailNotification;
import com.springfield.website.modules.generic.model.EmailNotificationStatus;
import com.springfield.website.modules.generic.repository.EmailRepository;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.FileUtilities;
import com.springfield.website.utils.StringValues;
import com.springfield.website.utils.pdf.ItextPdfUtility;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import com.springfield.website.utils.DateUtils;

import java.util.*;


@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(value = EmailConfigurationProperties.class)
public class EmailServiceImpl implements EmailService{

    private final EmailRepository emailRepository;
    private final EmailConfigurationProperties properties;

    @Override
    public EmailResponseData processSendSimpleEmail(SimpleEmailRequestPayload requestPayload) {
        try {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost(properties.getHostName());
            mailSender.setPort(Integer.parseInt(properties.getPort()));

            mailSender.setUsername(properties.getUsername());
            mailSender.setPassword(properties.getPassword());

            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "false");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.debug", "true");
//            props.put("mail.smtp.ssl.trust", "smtp.zeptomail.com");

            MimeMessage emailDetails = mailSender.createMimeMessage();
            emailDetails.setFrom(properties.getFrom());

            // Add the subject
            emailDetails.setSubject(requestPayload.getSubject());
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(requestPayload.getHtmlMessage(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            emailDetails.setContent(multipart);

            // Add the recipient
            List<InternetAddress> addressList = new ArrayList<>();
            if(Objects.nonNull(requestPayload.getRecipients())) {
                for (String recipient : requestPayload.getRecipients()) {
                    if(!recipient.trim().isEmpty()) {
                        InternetAddress address = new InternetAddress(recipient);
                        addressList.add(address);
                    }
                }
                emailDetails.setRecipients(Message.RecipientType.TO, addressList.toArray(InternetAddress[]::new));
            }

            // Add the BCC
            if(Objects.nonNull(requestPayload.getBcc())) {
                List<InternetAddress> bccList = new ArrayList<>();
                for (String bcc : requestPayload.getBcc()) {
                    if(!bcc.trim().isEmpty()) {
                        InternetAddress bccAddress = new InternetAddress(bcc);
                        bccList.add(bccAddress);
                    }
                }
                emailDetails.setRecipients(Message.RecipientType.BCC, bccList.toArray(InternetAddress[]::new));
            }

            // Add the cc
            List<InternetAddress> ccList = new ArrayList<>();
            if(Objects.nonNull(requestPayload.getCc())) {
                for (String cc : requestPayload.getCc()) {
                    if(!cc.trim().isEmpty()) {
                        InternetAddress ccAddress = new InternetAddress(cc);
                        ccList.add(ccAddress);
                    }
                }
                emailDetails.setRecipients(Message.RecipientType.CC, ccList.toArray(InternetAddress[]::new));
            }

            // Add all available attachment
            for(Attachment attachment: requestPayload.getAttachments()) {
                String fileFullPath;
                if(attachment.isUseLocalFile()){
                    fileFullPath = attachment.getLocalFilePath();
                }else {
                    String linkToHtmlFileToDownload = attachment.getLink();
                    String pdfFileName = "ATT_" + System.currentTimeMillis();
                    fileFullPath = ItextPdfUtility.downloadAndConvertHtmlToPdfFromLink(linkToHtmlFileToDownload, attachment.getAttachmentContextData(), pdfFileName);
                }
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(fileFullPath);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                String extension = attachment.getExtension().startsWith(StringValues.DOT) ? attachment.getExtension() : StringValues.DOT.concat(attachment.getExtension());
                attachmentBodyPart.setFileName(attachment.getName().concat(extension));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // Send the email
            mailSender.setJavaMailProperties(props);
            mailSender.send(emailDetails);
            System.out.println("Email sending successful");
            EmailResponseData responseData = EmailResponseData.withSenderId(CommonUtil.generateGuid());
            responseData.setSuccess(true);
            return responseData;
        } catch (Exception ex) {
            ex.printStackTrace();
            return EmailResponseData.withFailure(ex);
        }
    }

    @Override
    public void scheduleEmail(AppUser appUser, String subject, String template, Map<String, Object> context, List<String> recipients){
        try{
            String resourcePath = "/static/mail/".concat(template);
            String message = FileUtilities.downloadAndFormatOuterHtmlFromResource(resourcePath, context);
            EmailNotification emailNotification = EmailNotification.builder()
                    .recipientEmails(String.join(StringValues.COMMA, recipients))
                    .appUser(appUser)
                    .bccs(StringValues.EMPTY_STRING)
                    .cccs(StringValues.EMPTY_STRING)
                    .createdAt(DateUtils.getCurrentDateTime())
                    .updatedAt(DateUtils.getCurrentDateTime())
                    .failureReason(StringValues.EMPTY_STRING)
                    .subject(subject)
                    .fromMail(properties.getFrom())
                    .status(EmailNotificationStatus.PENDING)
                    .requestId(CommonUtil.generateGuid())
                    .message(message)
                    .build();
            emailRepository.saveAndFlush(emailNotification);
        }catch (Exception ignored){}
    }

    private final AppUserRepository appUserRepository;
    @EventListener(ApplicationStartedEvent.class)
    public void testEmail(){
        AppUser appUser = appUserRepository.findFirstByChannel("WEBSITE");
        scheduleEmail(appUser, "Testing", "loan-application.html", new LinkedHashMap<>(), List.of("sundayluckyenyinna@gmail.com"));
    }
}
