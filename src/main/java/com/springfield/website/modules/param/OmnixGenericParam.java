package com.springfield.website.modules.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.utils.DateUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "omnix_generic_param")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmnixGenericParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paramKey;

    @Column(nullable = false)
    private String paramValue;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        if(Objects.isNull(this.getCreatedAt())){
            this.setCreatedAt(DateUtils.getCurrentDateTime());
        }
    }
}
