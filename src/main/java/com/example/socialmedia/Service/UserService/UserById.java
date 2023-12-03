package com.example.socialmedia.Service.UserService;


import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserById {

    @Autowired
    private UserRepository userRepository;


    public AppUserDto getUserById(String id) {
        try{
            Optional<AppUser> user = this.userRepository.findById(id);
            if(user.isPresent()) { return AppUserDto.toDto(user.get()); }
            else throw new RuntimeException("the id is not found");
        }catch (Exception e){ throw new RuntimeException(e.getMessage()); }
    }
}
