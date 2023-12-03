package com.example.socialmedia.Service.UserService.UserPostService;

import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.PostPreview;
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
public class UserGetPosts {

    @Autowired
    private PostRepo postRepo;
    public List<PostPreview> getPosts(int page, int size, String sort, Boolean friendFirst) {
        try{
            if(friendFirst){
                return null;
            }else{
                Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
                Page<Post> posts = postRepo.findAll(pageable);
                List<PostPreview> filteredList = posts.stream()
                        .map(e->PostPreview.toPostPreview(e))
                        .collect(Collectors.toList());
                return filteredList;
            }
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
