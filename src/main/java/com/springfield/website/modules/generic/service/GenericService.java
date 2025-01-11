package com.springfield.website.modules.generic.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.modules.generic.payload.CountryStateResponseData;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface GenericService {
    OmnixApiResponse<RemoteFileUploadResponse> processRemoteFileUpload(MultipartFile multipartFile);

    OmnixApiResponse<Set<CountryStateResponseData>> processFetchCountryStates(RequestMeta requestMeta, String countryCode);

    OmnixApiResponse<Set<String>> processFetchStateLocalGovt(RequestMeta requestMeta, String countryCode, String state);
}
