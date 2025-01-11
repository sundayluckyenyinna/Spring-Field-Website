package com.springfield.website.modules.generic.email.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleEmailRequestPayload {

    private List<String> recipients = new ArrayList<>();

    private List<String> bcc = new ArrayList<>();

    private List<String> cc = new ArrayList<>();

    private String message;

    private String htmlMessage;

    private String subject;

    private String from;

    private List<Attachment> attachments = new ArrayList<>();

}
