package com.ai.pj.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenStatus {
    AUTHENTICATED,
    EXPIRED,
    INVALID
}
