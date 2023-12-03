package com.example.socialmedia.Model.Repostiroy;

import com.example.socialmedia.Model.Collection.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepo extends MongoRepository<Tag,String> {
    String findByTag(String tag);
}
