package com.example.socialmedia.Service.AdminService;


import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Dto.TagDto;
import com.example.socialmedia.Model.Repostiroy.TagRepo;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TagRepo tagRepo;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUserDto save(AppUserDto user) {
        try{
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            user.setRegisterDate(new Date());
            user.setRole("ADMIN");
            AppUserDto.toDto(this.userRepository.save(AppUser.toEntity(user)));
            return user;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }







}
