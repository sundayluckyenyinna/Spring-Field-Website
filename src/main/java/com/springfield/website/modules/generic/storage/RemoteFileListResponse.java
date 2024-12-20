package com.springfield.website.modules.generic.storage;

import lombok.Data;

import java.util.List;


@Data
public class RemoteFileListResponse
{
    private String responseCode;
    List<?> responseData;
}
