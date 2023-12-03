package com.example.socialmedia.Security;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    AppUser findByEmail(String email);

    //Optional<AppUser> findByEmail(String email);
}