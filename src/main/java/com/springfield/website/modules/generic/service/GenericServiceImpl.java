package com.springfield.website.modules.generic.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.modules.generic.storage.CloudinaryUploadService;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import com.springfield.website.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericServiceImpl implements GenericService{

    private final CloudinaryUploadService cloudinaryUploadService;

    @Override
    public OmnixApiResponse<RemoteFileUploadResponse> processRemoteFileUpload(MultipartFile multipartFile){
        try {
            RemoteFileUploadResponse responseData = cloudinaryUploadService.uploadFile(multipartFile);
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            e.printStackTrace();
            throw HttpUtil.getResolvedException(e);
        }
    }
}
