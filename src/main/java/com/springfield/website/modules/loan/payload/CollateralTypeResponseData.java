package com.springfield.website.modules.loan.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.loan.model.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollateralTypeResponseData {

    private long id;
    private CollateralType type;

    public static CollateralTypeResponseData fromIdAndCollateralType(long id, CollateralType collateralType){
        return CollateralTypeResponseData.builder()
                .id(id)
                .type(collateralType)
                .build();
    }
}
