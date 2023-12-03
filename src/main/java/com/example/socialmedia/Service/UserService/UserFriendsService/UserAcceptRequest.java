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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserAcceptRequest extends CurrentUser {

    @Autowired
    private FriendsRequestRepo friendsRequestRepo;
    @Autowired
    private FriendsAppUserRepo friendsAppUserRepo;
    @Autowired
    private UserRepository userRepository;

    public String accept(String requestId) {
        try{
            Optional<FriendsRequest> checkFriendRequest = friendsRequestRepo.findById(requestId);
            if(checkFriendRequest.isPresent()){
                if(checkFriendRequest.get().getUserReseveId().equals(getCurrentUser().getId())){

                    Optional<AppUser> friendReseveData = userRepository.findById(checkFriendRequest.get().getUserReseveId());
                    UserPreview userReseve = UserPreview.builder()
                            .id(checkFriendRequest.get().getUserReseveId())
                            .firstName(friendReseveData.get().getFirstName())
                            .lastName(friendReseveData.get().getLastName())
                            .title(friendReseveData.get().getTitle())
                            .build();
                    Optional<AppUser> friendSendData = userRepository.findById(checkFriendRequest.get().getUserSendId());
                    UserPreview userSend = UserPreview.builder()
                            .id(checkFriendRequest.get().getUserSendId())
                            .firstName(friendSendData.get().getFirstName())
                            .lastName(friendSendData.get().getLastName())
                            .title(friendSendData.get().getTitle())
                            .build();



                    List<FriendsAppUser> userReseveCheck = friendsAppUserRepo.findAll().stream()
                            .filter(e->e.getUserId().equals(userReseve.getId())).collect(Collectors.toList());

                    List<FriendsAppUser> userSendCheck = friendsAppUserRepo.findAll().stream()
                            .filter(e->e.getUserId().equals(userSend.getId())).collect(Collectors.toList());



                    if(userSendCheck.isEmpty()&&userReseveCheck.isEmpty()){
                        UserPreview newFriend = UserPreview.builder()
                                .id(userReseve.getId())
                                .firstName(userReseve.getFirstName())
                                .lastName(userReseve.getLastName())
                                .title(userReseve.getTitle())
                                .build();
                        List<UserPreview> x = new ArrayList<>();
                        x.add(newFriend);
                        FriendsAppUser sendUser = FriendsAppUser.builder()

                                .userId(userSend.getId())
                                .friends(x)
                                .build();

                        UserPreview newFriend2 = UserPreview.builder()
                                .id(userSend.getId())
                                .firstName(userSend.getFirstName())
                                .lastName(userSend.getLastName())
                                .title(userSend.getTitle())
                                .build();
                        List<UserPreview> x1 = new ArrayList<>();
                        x1.add(newFriend2);
                        FriendsAppUser reseveUser = FriendsAppUser.builder()

                                .userId(userReseve.getId())
                                .friends(x1)
                                .build();


                        friendsAppUserRepo.save(sendUser);
                        friendsAppUserRepo.save(reseveUser);
                        friendsRequestRepo.deleteById(requestId);
                        return "Done";
                    }else if (!userSendCheck.isEmpty()&&userReseveCheck.isEmpty()) {
                        List<FriendsAppUser> tempListSend = friendsAppUserRepo.findAll().stream()
                                .filter(e->e.getUserId().equals(userSend.getId()))
                                .collect(Collectors.toList());
                        tempListSend.get(0).getFriends().add(userReseve);
                        FriendsAppUser sendUser = FriendsAppUser.builder()
                                .id(tempListSend.get(0).getId())
                                .userId(userSend.getId())
                                .friends(tempListSend.get(0).getFriends())
                                .build();


                        UserPreview newFriend2 = UserPreview.builder()
                                .id(userSend.getId())
                                .firstName(userSend.getFirstName())
                                .lastName(userSend.getLastName())
                                .title(userSend.getTitle())
                                .build();
                        List<UserPreview> x1 = new ArrayList<>();
                        x1.add(newFriend2);
                        FriendsAppUser reseveUser = FriendsAppUser.builder()

                                .userId(userReseve.getId())
                                .friends(x1)
                                .build();
                        friendsAppUserRepo.save(sendUser);
                        friendsAppUserRepo.save(reseveUser);
                        friendsRequestRepo.deleteById(requestId);
                        return "Done";

                    } else if (!userSendCheck.isEmpty()&&!userReseveCheck.isEmpty()) {
                        List<FriendsAppUser> tempListSend = friendsAppUserRepo.findAll().stream()
                                .filter(e->e.getUserId().equals(userSend.getId()))
                                .collect(Collectors.toList());
                        tempListSend.get(0).getFriends().add(userReseve);
                        FriendsAppUser sendUser = FriendsAppUser.builder()
                                .id(tempListSend.get(0).getId())
                                .userId(userSend.getId())
                                .friends(tempListSend.get(0).getFriends())
                                .build();


                        List<FriendsAppUser> tempListReseve = friendsAppUserRepo.findAll().stream()
                                .filter(e->e.getUserId().equals(userReseve.getId()))
                                .collect(Collectors.toList());
                        tempListReseve.get(0).getFriends().add(userSend);
                        FriendsAppUser reseveUser = FriendsAppUser.builder()
                                .id(tempListReseve.get(0).getId())
                                .userId(userReseve.getId())
                                .friends(tempListReseve.get(0).getFriends())
                                .build();


                        friendsRequestRepo.deleteById(requestId);
                        friendsAppUserRepo.save(sendUser);
                        friendsAppUserRepo.save(reseveUser);
                        return "Done";
                    } else if (userSendCheck.isEmpty()&&!userReseveCheck.isEmpty()) {
                        UserPreview newFriend = UserPreview.builder()
                                .id(userReseve.getId())
                                .firstName(userReseve.getFirstName())
                                .lastName(userReseve.getLastName())
                                .title(userReseve.getTitle())
                                .build();
                        List<UserPreview> x = new ArrayList<>();
                        x.add(newFriend);
                        FriendsAppUser sendUser = FriendsAppUser.builder()

                                .userId(userSend.getId())
                                .friends(x)
                                .build();

                        List<FriendsAppUser> tempListReseve = friendsAppUserRepo.findAll().stream()
                                .filter(e->e.getUserId().equals(userReseve.getId()))
                                .collect(Collectors.toList());
                        tempListReseve.get(0).getFriends().add(userSend);
                        FriendsAppUser reseveUser = FriendsAppUser.builder()
                                .id(tempListReseve.get(0).getId())
                                .userId(userReseve.getId())
                                .friends(tempListReseve.get(0).getFriends())
                                .build();


                        friendsRequestRepo.deleteById(requestId);
                        friendsAppUserRepo.save(sendUser);
                        friendsAppUserRepo.save(reseveUser);
                        return "Done";
                    }else{throw new RuntimeException("error");}
                }else{throw new RuntimeException("it is forbidden to accept this request");}
            }else{throw new RuntimeException("the request id is not define");}
        }catch(Exception e){throw new RuntimeException(e.getMessage());}
    }
}
