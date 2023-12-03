package com.example.socialmedia.Model.Dto;

import com.example.socialmedia.Model.Collection.FriendsRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendsRequestDto {
    private String id;
    private String userSendId;
    private String userReseveId;

    public FriendsRequestDto toEntity(FriendsRequest friendsRequest){
        return FriendsRequestDto.builder()
                .id(friendsRequest.getId())
                .userSendId(friendsRequest.getUserSendId())
                .userReseveId(friendsRequest.getUserReseveId())
                .build();
    }


}
