package com.example.socialmedia.Model.Dto;

import com.example.socialmedia.Model.Collection.FriendsAppUser;
import com.example.socialmedia.Security.AppUserDto;
import com.example.socialmedia.UserPreview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendsAppUserDto {
    private String id;
    private String userId;
    @Valid
    private List<UserPreview> friends;
    @Valid
    private List<UserPreview> friendsBlocked;


    public FriendsAppUserDto toEntity(FriendsAppUser friendsAppUser){
        return FriendsAppUserDto.builder()
                .id(friendsAppUser.getId())
                .userId(friendsAppUser.getUserId())
                .friends(friendsAppUser.getFriends())
                .friendsBlocked(friendsAppUser.getFriendsBlocked())
                .build();
    }
}
