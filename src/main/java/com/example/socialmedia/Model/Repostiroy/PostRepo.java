package com.example.socialmedia.Model.Repostiroy;

import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,String> {
}
