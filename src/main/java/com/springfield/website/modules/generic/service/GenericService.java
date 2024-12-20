package com.springfield.website.modules.generic.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface GenericService {
    OmnixApiResponse<RemoteFileUploadResponse> processRemoteFileUpload(MultipartFile multipartFile);
}
