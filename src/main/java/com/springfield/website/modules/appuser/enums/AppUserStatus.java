package com.springfield.website.modules.appuser.enums;

import com.springfield.website.common.Enumerable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppUserStatus implements Enumerable<AppUserStatus> {
    ACTIVE(1, "Active"),
    DISABLED(2, "Disabled");

    private final Integer id;
    private final String desc;

    @Override
    public AppUserStatus[] getValues() {
        return AppUserStatus.values();
    }
}
