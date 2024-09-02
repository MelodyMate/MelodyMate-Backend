package com.melodymatebackend.auth.domain;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    USER("ROLE_USER", "회원"),
    ADMIN("ROLE_ADMIN", "관리자"),
    GUEST("GUEST", "게스트");

    private final String key;
    private final String title;

    public static RoleType of(String key) {
        return Arrays.stream(RoleType.values())
            .filter(r -> r.getKey().equals(key))
            .findAny()
            .orElse(GUEST);
    }
}
