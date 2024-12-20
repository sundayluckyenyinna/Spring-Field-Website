package com.springfield.website.security;

import com.springfield.website.common.RequestMeta;
import com.springfield.website.common.RequestMetaArg;
import com.springfield.website.common.ResponseCode;
import com.springfield.website.modules.appuser.entities.AppUser;
import com.springfield.website.modules.appuser.repository.AppUserRepository;
import com.springfield.website.utils.CommonUtil;
import com.springfield.website.utils.HttpUtil;
import com.springfield.website.utils.StringValues;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class RequestMetaArgumentResolver implements HandlerMethodArgumentResolver {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestMetaArg.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        String channelId = httpServletRequest.getHeader(StringValues.CHANNEL_ID);
        String channelSecret = httpServletRequest.getHeader(StringValues.CHANNEL_SECRET);

        if(CommonUtil.anyNullOrEmpty(channelId, channelSecret)){
            throw HttpUtil.getResolvedException(ResponseCode.FORBIDDEN_RESOURCE, "One of Channel-ID or Channel-Secret is null or empty");
        }
        AppUser appUser = appUserRepository.findFirstByChannelId(channelId);
        if(Objects.isNull(appUser)){
            throw HttpUtil.getResolvedException(ResponseCode.FORBIDDEN_RESOURCE, "Application user record not found");
        }
        if(!passwordEncoder.matches(channelSecret, appUser.getChannelSecret())){
            throw HttpUtil.getResolvedException(ResponseCode.FORBIDDEN_RESOURCE, "Invalid application user credentials");
        }
        return RequestMeta.builder()
                .channelId(channelId)
                .channelSecret(channelSecret)
                .appUser(appUser)
                .isPartnerRequest(appUser.isForPartner())
                .build();
    }
}
