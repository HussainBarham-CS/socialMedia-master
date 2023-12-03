package com.example.socialmedia.Security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private String token;
    private String expirationToken;
    private String tokenType;
    private String refreshToken;
    private String expirationRefreshToken;

}