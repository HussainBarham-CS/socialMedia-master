package com.example.socialmedia.Service.UserService.UserFriendsService;

import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.FriendsAppUser;
import com.example.socialmedia.Model.Collection.FriendsRequest;
import com.example.socialmedia.Model.Repostiroy.FriendsAppUserRepo;
import com.example.socialmedia.Model.Repostiroy.FriendsRequestRepo;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.UserRepository;
import com.example.socialmedia.UserPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserSendRequest extends CurrentUser {

    @Autowired
    private FriendsRequestRepo friendsRequestRepo;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendsAppUserRepo friendsAppUserRepo;


    public String send(String userId) {
        try{
            Optional<AppUser> checkUser = userRepository.findById(userId);
            if(checkUser.isPresent()){
                Optional<FriendsAppUser> checkUserFriend = friendsAppUserRepo.findAll().stream()
                        .filter(e->e.getUserId().equals(getCurrentUser().getId())&&
                                e.getFriends().contains(checkUser.get())).findFirst();
                if(!checkUserFriend.isPresent()){
                    Optional<FriendsRequest> check = friendsRequestRepo.findAll().stream()
                            .filter(e -> e.getUserSendId().equals(getCurrentUser().getId())
                                    && e.getUserReseveId().equals(userId))
                            .findFirst();
                    if(!check.isPresent()){
                        if(!userId.equals(getCurrentUser().getId()))
                        {
                            Optional<AppUser> checkContain= userRepository.findById(userId);
                            UserPreview user = UserPreview.toUserPreview(checkContain.get());
                            List<FriendsAppUser> checkk = friendsAppUserRepo.findAll().stream()
                                    .filter(e->e.getUserId().equals(getCurrentUser().getId())&&
                                            e.getFriends().contains(user))
                                    .collect(Collectors.toList());
                            if(checkk.isEmpty())
                            {
                                FriendsRequest newFriend = FriendsRequest.builder()
                                        .userSendId(getCurrentUser().getId())
                                        .userReseveId(userId)
                                        .build();
                                friendsRequestRepo.save(newFriend);
                                return "sending wait the response";
                            }else{throw new RuntimeException("the user is already on your friend list");}
                        }else{throw new RuntimeException("sorry but you can not send request for you");}
                    }else{throw new RuntimeException("the request is alredy send you must waiting " +
                            "for anouther user to accept or decline this request");}
                }else{throw new RuntimeException("the user is already on your friend list");}
            }else{throw new RuntimeException("the user id is not define");}
        }catch(Exception e){throw new RuntimeException(e.getMessage());}
    }
}
