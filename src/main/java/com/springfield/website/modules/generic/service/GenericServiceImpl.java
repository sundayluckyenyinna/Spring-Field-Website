package com.springfield.website.modules.generic.service;

import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.modules.generic.storage.FirebaseFtpClientService;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import com.springfield.website.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericServiceImpl implements GenericService{

    private final FirebaseFtpClientService firebaseFtpClientService;

    @Override
    public OmnixApiResponse<RemoteFileUploadResponse> processRemoteFileUpload(MultipartFile multipartFile){
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String fileName = multipartFile.getOriginalFilename();
            RemoteFileUploadResponse responseData = firebaseFtpClientService.processRemoteFileUpload(inputStream, fileName);
            return OmnixApiResponse.ofSuccessful(responseData);
        }catch (Exception e){
            throw HttpUtil.getResolvedException(e);
        }
    }
}
