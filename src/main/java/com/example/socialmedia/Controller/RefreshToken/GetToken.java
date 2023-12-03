package com.example.socialmedia.Controller.RefreshToken;


import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Security.AppUserService;
import com.example.socialmedia.Security.JwtResponse;
import com.example.socialmedia.Security.SignInRequest;
import com.example.socialmedia.Security.TokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1/sparx/user/getNew")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "RefreshToken Controller")
public class GetToken extends CurrentUser {


    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AppUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = "/api/v1/sparx/user/getNew")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @Operation(
            description = "This api for front end developer to get a new valid token by refresh token to get a new valid token",
            summary = "GET VALID TOKEN"
    )
    public ResponseEntity<JwtResponse> get(){

        SignInRequest signInRequest = SignInRequest.builder()
                .password(getCurrentUser().getPassword())
                .username(getCurrentUser().getUsername())
                .build();

        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        String refreshToken = tokenUtil.generateRewfreshToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .expirationToken(String.valueOf(tokenUtil.getClaims(token).getExpiration()))
                .expirationRefreshToken(String.valueOf(tokenUtil.getClaims(refreshToken).getExpiration()))
                .tokenType("Bearer")
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
