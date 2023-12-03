package com.example.socialmedia.Model.Repostiroy;

import com.example.socialmedia.Model.Collection.Comment;
import com.example.socialmedia.Model.Collection.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<Comment,String> {
}
