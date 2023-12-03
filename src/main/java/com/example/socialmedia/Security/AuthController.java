package com.example.socialmedia.Security;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/api/v1/sparx")
@Tag(name = "Log in ")

public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private AppUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    @Operation(
            description = "This api for anyone to logIn on Sparx ",
            summary = "LOG IN SPARX "
    )
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
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
        return response;
    }






}