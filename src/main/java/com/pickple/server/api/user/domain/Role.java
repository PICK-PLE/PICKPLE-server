package com.pickple.server.api.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("admin"),
    HOST("host"),
    GUEST("guest");

    private final String role;
}
