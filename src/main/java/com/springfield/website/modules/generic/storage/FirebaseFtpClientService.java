package com.springfield.website.modules.generic.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service(value = "firebase_ftp_service")
@RequiredArgsConstructor
public class FirebaseFtpClientService {

    private final FirebaseStorageClient firebaseStorageClient;

    public RemoteFileUploadResponse processRemoteFileUpload(InputStream inputStream, String remoteDirPath, String filenameWithExtension) {
        return firebaseStorageClient.saveFile(inputStream, filenameWithExtension);
    }

    public RemoteFileListResponse processRemoteFileListing(List<String> remoteDirPaths) {
        return null;
    }

    public RemoteFileUploadResponse processRemoteFileUpload(InputStream inputStream, String fileNameWithExtension) {
        return firebaseStorageClient.saveFile(inputStream, fileNameWithExtension);
    }
}
