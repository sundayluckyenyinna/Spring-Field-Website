package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.loan.model.BusinessLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessLocationResponseData {

    private long id;
    private BusinessLocation location;

    public static BusinessLocationResponseData fromIdAndBusinessLocation(long id, BusinessLocation location){
        return BusinessLocationResponseData.builder()
                .id(id)
                .location(location)
                .build();
    }
}
