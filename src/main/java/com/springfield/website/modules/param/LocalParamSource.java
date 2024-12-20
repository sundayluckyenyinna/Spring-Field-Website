package com.springfield.website.modules.param;

import com.springfield.website.common.ParamKey;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocalParamSource {

    private final OmnixGenericParamRepository paramRepository;


    public String getParamOrDefault(ParamKey paramKey, String defaultValue){
        OmnixGenericParam param = paramRepository.findFirstByParamKey(paramKey.name());
        if(Objects.isNull(param)){
            param = OmnixGenericParam.builder()
                    .paramKey(paramKey.name())
                    .paramValue(defaultValue)
                    .createdAt(DateUtils.getCurrentDateTime())
                    .build();
            OmnixGenericParam newlyCreatedParam = paramRepository.saveAndFlush(param);
            return newlyCreatedParam.getParamValue();
        }
        return param.getParamValue();
    }
}
