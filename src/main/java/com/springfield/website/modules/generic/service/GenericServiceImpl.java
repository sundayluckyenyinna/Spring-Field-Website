package com.springfield.website.modules.generic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springfield.website.common.*;
import com.springfield.website.common.OmnixApiResponse;
import com.springfield.website.common.RequestMeta;
import com.springfield.website.modules.generic.payload.CountryStateResponseData;
import com.springfield.website.modules.generic.storage.CloudinaryUploadService;
import com.springfield.website.modules.generic.storage.RemoteFileUploadResponse;
import com.springfield.website.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GenericServiceImpl implements GenericService{

    private final ObjectMapper objectMapper;
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

    @Override
    public OmnixApiResponse<Set<CountryStateResponseData>> processFetchCountryStates(RequestMeta requestMeta, String countryCode){
        try{
            if(countryCode.equalsIgnoreCase("NG")) {
                Set<CountryStateResponseData> responseData = Arrays.stream(NigerianState.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            else if(countryCode.equalsIgnoreCase("US")){
                Set<CountryStateResponseData> responseData = Arrays.stream(UnitedStateStates.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            else if(countryCode.equalsIgnoreCase("UK")){
                Set<CountryStateResponseData> responseData = Arrays.stream(EnglandStates.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            else  if(countryCode.equalsIgnoreCase("SA")){
                Set<CountryStateResponseData> responseData = Arrays.stream(SouthAfricaState.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            else  if(countryCode.equalsIgnoreCase("GH")){
                Set<CountryStateResponseData> responseData = Arrays.stream(GhanaState.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            else  if(countryCode.equalsIgnoreCase("KY")){
                Set<CountryStateResponseData> responseData = Arrays.stream(KenyaState.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            else  if(countryCode.equalsIgnoreCase("CN")){
                Set<CountryStateResponseData> responseData = Arrays.stream(CanadaState.values())
                        .map(nigerianState -> CountryStateResponseData.builder()
                                .name(nigerianState.getPrettyName())
                                .slug(nigerianState.name())
                                .build()).collect(Collectors.toSet());
                return OmnixApiResponse.ofSuccessful(responseData);
            }
            return OmnixApiResponse.ofSuccessful(new HashSet<>());
        }catch (Exception exception){
            throw HttpUtil.getResolvedException(exception);
        }
    }

    @Override
    public OmnixApiResponse<Set<String>> processFetchStateLocalGovt(RequestMeta requestMeta, String countryCode, String state){
        try{
            String fileName = "/seed/".concat(countryCode.toUpperCase()).concat("_local_govt.json");
            Resource resource = new ClassPathResource(fileName);
            Object seedContent = objectMapper.readValue(resource.getInputStream(), Object.class);
            String jsonArray = objectMapper.writeValueAsString(seedContent);
            JSONArray jsonObjectArray = new JSONArray(jsonArray);
            Set<String> lgas = new LinkedHashSet<>();
            for(int i = 0; i <jsonObjectArray.length(); i++){
                JSONObject jsonObject = jsonObjectArray.getJSONObject(i);
                String stateEnum = jsonObject.getString("stateEnum").toUpperCase();
                JSONArray lgaArray = jsonObject.getJSONArray("localGovernments");
                if(stateEnum.equalsIgnoreCase(state)) {
                    for (int j = 0; j < lgaArray.length(); j++) {
                        String lgaName = lgaArray.getString(j);
                        lgas.add(lgaName);
                    }
                }
            }
            return OmnixApiResponse.ofSuccessful(lgas);
        }catch (Exception exception){
            throw HttpUtil.getResolvedException(exception);
        }
    }
}
