package com.example.socialmedia.Service.UserService.UserFriendsService;

import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.FriendsAppUser;
import com.example.socialmedia.Model.Repostiroy.FriendsAppUserRepo;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.UserRepository;
import com.example.socialmedia.UserPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRemoveFriend extends CurrentUser {
    @Autowired
    private FriendsAppUserRepo friendsAppUserRepo;
    @Autowired
    private UserRepository userRepository;
    public String remove(String userId) {
        try{
            Optional<AppUser> checkUser = userRepository.findById(userId);
            if (checkUser.isPresent()){
                Optional<AppUser> friend = userRepository.findById(userId);
                UserPreview userPreview = UserPreview.toUserPreview(friend.get());
                List<FriendsAppUser> checkFriend = friendsAppUserRepo.findAll().stream()
                        .filter(e->e.getUserId().equals(getCurrentUser().getId())&&
                                e.getFriends().contains(userPreview))
                        .collect(Collectors.toList());
                if(!checkFriend.isEmpty()){
                    checkFriend.get(0).getFriends().remove(userPreview);
                    friendsAppUserRepo.save(checkFriend.get(0));
                    return "done";
                }else{throw new RuntimeException("the user is not define on friend list");}
            }else{throw new RuntimeException("the user id is not define");}
        }catch(Exception e){throw new RuntimeException(e.getMessage());}
    }
}
