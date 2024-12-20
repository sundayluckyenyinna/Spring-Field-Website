package com.springfield.website.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springfield.website.modules.appuser.entities.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMeta {

    private String channelId;
    private String channelSecret;
    private AppUser appUser;
    private boolean isPartnerRequest;
}
