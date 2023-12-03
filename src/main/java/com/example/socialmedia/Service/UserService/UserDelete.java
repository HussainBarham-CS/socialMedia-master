package com.example.socialmedia.Service.UserService;

import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDelete extends CurrentUser {

    @Autowired
    private UserRepository userRepository;


    public String delete(String userId){
        try{
            if(getCurrentUser().getId().equals(userId)) {
                userRepository.deleteById(userId);
                return "Deleted";
            }else{throw new RuntimeException("it is forbidden to delete data to another user");}
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
