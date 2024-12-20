package com.springfield.website.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class OmnixApiResponse<T>{

    @Schema(example = "00", description = "response code for the request")
    protected String responseCode;

    @Schema(example = "Successful operation", description = "response message for the request and describing the API response")
    protected String responseMessage;

    @Schema(example = "[]", description = "List of customer side errors")
    protected List<String> errors = new ArrayList<>();

    private T responseData;

    public static <T> OmnixApiResponse<T> getInstance(){
        return new OmnixApiResponse<>();
    }

    public static <T> OmnixApiResponse<T> getInstance(Class<T> tClass){
        return new OmnixApiResponse<>();
    }

    public OmnixApiResponse<T> withResponseCode(String responseCode){
        setResponseCode(responseCode);
        return this;
    }

    public OmnixApiResponse<T> withResponseMessage(String responseMessage){
        setResponseMessage(responseMessage);
        return this;
    }

    public OmnixApiResponse<T> withErrors(Collection<String> errors){
        setErrors(new ArrayList<>(errors));
        return this;
    }

    public OmnixApiResponse<T> withError(String error){
        this.errors = this.errors == null ? new ArrayList<>() : this.errors;
        this.errors.add(error);
        return this;
    }

    public OmnixApiResponse<T> withData(T data){
        setResponseData(data);
        return this;
    }

    public OmnixApiResponse<T> ofSuccess(T responseData){
        setResponseCode(ResponseCode.SUCCESS);
        setResponseMessage("Successful operation");
        setResponseData(responseData);
        return this;
    }

    public static <T> OmnixApiResponse<T> ofSuccessful(T responseData){
        return new OmnixApiResponse<>(ResponseCode.SUCCESS, "Successful operation", new ArrayList<>(), responseData);
    }
}
