package com.springfield.website.modules.generic.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.utils.StringValues;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@AllArgsConstructor
public class CloudinaryUploadService {

    private final Cloudinary cloudinary;

    public RemoteFileUploadResponse uploadFile(MultipartFile file) {
        RemoteFileUploadResponse response = new RemoteFileUploadResponse();
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String uploadLink = uploadResult.get("url").toString();
            response.setResponseCode(ResponseCode.SUCCESS_CODE);
            response.setRemoteFileName(file.getOriginalFilename());
            response.setPublicLink(uploadLink);
            return response;
        } catch (Exception exception) {
            response.setResponseCode(ResponseCode.FAILED_TRANSACTION);
            response.setRemoteFileName(file.getOriginalFilename());
            response.setPublicLink(StringValues.EMPTY_STRING);
            return response;
        }
    }
}