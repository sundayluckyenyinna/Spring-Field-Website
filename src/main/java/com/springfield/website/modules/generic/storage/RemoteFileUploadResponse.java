package com.springfield.website.modules.generic.storage;

import lombok.Data;

@Data
public class RemoteFileUploadResponse {

    private String responseCode;
    private String publicLink;
    private String remoteFileName;

}
