package com.example.socialmedia.Model.Collection;

import com.example.socialmedia.Model.Dto.FriendsAppUserDto;
import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.UserPreview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Friends")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendsAppUser {
    @Id
    private String id;
    private String userId;
    @Valid
    private List<UserPreview> friends;
    @Valid
    private List<UserPreview> friendsBlocked;



    public FriendsAppUser toEntity(FriendsAppUserDto friendsAppUserDto){
        return FriendsAppUser.builder()
                .id(friendsAppUserDto.getId())
                .userId(friendsAppUserDto.getUserId())
                .friends(friendsAppUserDto.getFriends())
                .friendsBlocked(friendsAppUserDto.getFriendsBlocked())
                .build();
    }

}
