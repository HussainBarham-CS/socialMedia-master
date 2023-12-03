package com.example.socialmedia.Service.UserService;


import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserCreate {


    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AppUserDto createUser(AppUserDto user) {
        try{
            Optional<AppUser> check = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
            if(check.isPresent()){throw new RuntimeException("the email is already exist");}
            else {
                user.setPassword(passwordEncoder().encode(user.getPassword()));
                user.setRegisterDate(new Date());
                user.setRole("USER");
                AppUserDto.toDto(this.userRepository.save(AppUser.toEntity(user)));
                return user;
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
