package com.example.socialmedia.Model.Repostiroy;

import com.example.socialmedia.Model.Collection.FriendsAppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendsAppUserRepo extends MongoRepository<FriendsAppUser,String> {
}
