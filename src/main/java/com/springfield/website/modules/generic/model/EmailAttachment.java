package com.springfield.website.modules.generic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.instrumentation.StringMapConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_attachment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "text")
    @Convert(converter = StringMapConverter.class)
    private Map<String, Object> attachmentContextData = new HashMap<>();

    @Column(nullable = false)
    private String extension;

    @ManyToOne(optional = false)
    private EmailNotification emailNotification;

    private boolean useLocal = false;

    private String localFilePath;
}
