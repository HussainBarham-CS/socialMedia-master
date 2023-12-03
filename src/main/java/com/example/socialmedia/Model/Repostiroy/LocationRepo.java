package com.example.socialmedia.Model.Repostiroy;

import com.example.socialmedia.Model.Collection.Location;
import com.example.socialmedia.Model.Collection.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepo extends MongoRepository<Location,String> {
}
