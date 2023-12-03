package com.example.socialmedia.Model.Repostiroy;

import com.example.socialmedia.Model.Collection.FriendsRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendsRequestRepo extends MongoRepository<FriendsRequest,String> {
}
