package com.example.socialmedia.Service.UserService.UserPostService;


import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.PostPreview;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserGetPostsByUserId {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepo postRepo;


    public List<PostPreview> getPostsByUserId(int page , int size , String sort,String userId) {
        try {
            Optional<AppUser> check = userRepository.findById(userId);
            if (check.isPresent()) {
                Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

                List<PostPreview> filteredList = postRepo.findAll(pageable)
                        .stream()
                        .filter(e -> e.getOwner().getId().equals(userId))
                        .map(e->PostPreview.toPostPreview(e))
                        .collect(Collectors.toList());
                return filteredList;

            } else {
                throw new RuntimeException("the id is not define");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
