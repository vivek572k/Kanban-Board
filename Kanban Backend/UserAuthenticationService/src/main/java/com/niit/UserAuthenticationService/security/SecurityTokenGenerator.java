package com.niit.UserAuthenticationService.security;

import com.niit.UserAuthenticationService.domain.User;

public interface SecurityTokenGenerator {
    String createToken(User user);
}
