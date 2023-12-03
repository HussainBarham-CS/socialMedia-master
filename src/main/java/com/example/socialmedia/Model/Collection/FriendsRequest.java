package com.example.socialmedia.Model.Collection;

import com.example.socialmedia.Model.Dto.FriendsAppUserDto;
import com.example.socialmedia.Model.Dto.FriendsRequestDto;
import com.example.socialmedia.Security.AppUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import java.util.List;
@Document(collection = "FriendsRequest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendsRequest {

    @Id
    private String id;
    private String userSendId;
    private String userReseveId;



    public FriendsRequest toEntity(FriendsRequestDto friendsRequestDto){
        return FriendsRequest.builder()
                .id(friendsRequestDto.getId())
                .userSendId(friendsRequestDto.getUserSendId())
                .userReseveId(friendsRequestDto.getUserReseveId())
                .build();
    }
}
