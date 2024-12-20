package com.springfield.website.modules.generic.storage;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.utils.StringValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class FirebaseStorageClient {

    @Value("${ftp.remote.firebase.base-file-path}")
    private String baseFilePath;

    @Value("${ftp.remote.firebase.access-token}")
    private String downloadAccessToken;

    @Value("${ftp.remote.firebase.default-bucket}")
    private String defaultBucket;

    @Value("${ftp.remote.firebase.default-content-type}")
    private String defaultContentType;

    private final Storage storageService;

    public RemoteFileUploadResponse saveFile(InputStream inputStream, String fileNameWithExtension){
        RemoteFileUploadResponse response = new RemoteFileUploadResponse();
        BlobId blobId = BlobId.of(defaultBucket, fileNameWithExtension);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setMetadata(Map.of("firebaseStorageDownloadTokens", downloadAccessToken))
                .setContentType(defaultContentType)
                .build();
        try {
            Blob createdBlob = storageService.create(blobInfo, inputStream.readAllBytes());
            String publicLink = this.getPublicLink(fileNameWithExtension);
            log.info("Uploaded remote file successfully with name: {} and public link: {}", createdBlob.getName(), publicLink);
            response.setResponseCode(ResponseCode.SUCCESS);
            response.setRemoteFileName(fileNameWithExtension);
            response.setPublicLink(publicLink);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception occurred while trying to upload media file to firebase storage");
            response.setResponseCode(ResponseCode.THIRD_PARTY_SERVICE_FAILURE);
            response.setRemoteFileName(fileNameWithExtension);
            response.setPublicLink(StringValues.EMPTY_STRING);
        }
        return response;
    }

    private String getPublicLink(String fileNameWithExtension){
        String basePath = baseFilePath.endsWith(StringValues.FORWARD_STROKE) ? baseFilePath : baseFilePath.concat(StringValues.FORWARD_STROKE);
        return basePath.concat(fileNameWithExtension).concat(String.format("?alt=%s&token=%s", defaultContentType, downloadAccessToken));
    }
}
