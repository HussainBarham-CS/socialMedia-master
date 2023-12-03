package com.example.socialmedia.Service.UserService.UserFriendsService;

import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.FriendsRequest;
import com.example.socialmedia.Model.Repostiroy.FriendsRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeclineRequest extends CurrentUser {

    @Autowired
    private FriendsRequestRepo friendsRequestRepo;
    public String decline(String requestId) {
        try{
            Optional<FriendsRequest> checkRequest = friendsRequestRepo.findById(requestId);
            if(checkRequest.isPresent()){
                if(getCurrentUser().getId().equals(checkRequest.get().getUserReseveId())){
                    friendsRequestRepo.deleteById(requestId);
                    return "Done";
                }else{throw new RuntimeException("it is forbidden to decline this request");}
            }else {throw new RuntimeException("the request id is not define");}
        }catch(Exception e){throw new RuntimeException(e.getMessage());}
    }
}
