package com.example.socialmedia;


import com.example.socialmedia.Security.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Khalid Elshafie <abolkog@gmail.com>
 * @Created 15/10/2018 11:51 PM.
 */
public abstract class CurrentUser {

    public AppUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (AppUser) authentication.getPrincipal();
    }
}