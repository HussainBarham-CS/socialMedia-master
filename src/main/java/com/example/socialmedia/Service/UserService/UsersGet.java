package com.example.socialmedia.Service.UserService;


import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.UserRepository;
import com.example.socialmedia.UserPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersGet {
    
    @Autowired
    private UserRepository userRepository;


    public List<UserPreview> getAll(int page, int size, String sort){

        try{
            Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
            Page<AppUser> users = userRepository.findAll(pageable);
            List<UserPreview> temp = users.stream().map(e->UserPreview.toUserPreview(e)).collect(Collectors.toList());
            return temp;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }



    }
}
