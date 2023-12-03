package com.example.socialmedia.Service.UserService;

import com.example.socialmedia.AppUserUpdate;
import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserUpdate extends CurrentUser {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public AppUserDto update(String userId , AppUserUpdate user) {
        try {
            if(getCurrentUser().getId().equals(userId)){
                System.out.println(user.getId());
                if(user.getEmail()!=null || user.getId()!=null){
                    throw new RuntimeException("sorry but you can not modify your email or your id");
                }else {
                    user.setId(getCurrentUser().getId());
                    user.setEmail(getCurrentUser().getEmail());
                    if(user.getDateOfBirth()==null){user.setDateOfBirth(getCurrentUser().getDateOfBirth());}
                    if(user.getFirstName()==null){user.setFirstName(getCurrentUser().getFirstName());}
                    if(user.getLastName()==null){user.setLastName(getCurrentUser().getLastName());}
                    if(user.getGender()==null){user.setGender(getCurrentUser().getGender());}
                    if(user.getLocation()==null){user.setLocation(getCurrentUser().getLocation());}
                    if(user.getPassword()==null){user.setPassword(getCurrentUser().getPassword());}
                    else{user.setPassword(passwordEncoder().encode(user.getPassword()));}
                    if(user.getTitle()==null){user.setTitle(getCurrentUser().getTitle());}
                    if(user.getRole()==null||user.getRole()!=null){user.setRole("USER");}
                    if(user.getRegisterDate()!=null||user.getRegisterDate()==null){
                        user.setRegisterDate(getCurrentUser().getRegisterDate());
                    }
                    return AppUserDto.toDto(userRepository.save(AppUser.toEntity1(user)));
                }
            }else{throw new RuntimeException("it is forbidden to update data to another user");}
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
