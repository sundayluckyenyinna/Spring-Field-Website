package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.loan.model.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessTypeResponseData {

    private Long id;
    private BusinessType type;

    public static BusinessTypeResponseData fromIdAndBusinessType(long id, BusinessType businessType){
        return BusinessTypeResponseData.builder()
                .id(id)
                .type(businessType)
                .build();
    }
}
