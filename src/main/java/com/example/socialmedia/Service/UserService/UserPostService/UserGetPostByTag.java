package com.example.socialmedia.Service.UserService.UserPostService;

import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.Model.Repostiroy.TagRepo;
import com.example.socialmedia.PostPreview;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.UserPreview;
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
public class UserGetPostByTag {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TagRepo tagRepo;


    public boolean areAllTagsInRepository(String tag) {
        List<Tag> tagsFromRepository = tagRepo.findAll();

        List<String> tagNamesFromRepository = tagsFromRepository.stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());


        return tagNamesFromRepository.contains(tag);
    }


    public List<PostPreview> getAllByTag(int page, int size, String sort, String tagId) {
        try{
            Optional<Tag> check = tagRepo.findById(tagId);
            if(check.isPresent()){
                Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
                Page<Post> posts = postRepo.findAll(pageable);
                List<PostPreview> filteredList = posts.stream()
                        .filter(e -> e.getTags() != null && e.getTags().stream()
                                .anyMatch(tag -> tag.getTag().contains(check.get().getTag())))
                        .map(e->PostPreview.toPostPreview(e))
                        .collect(Collectors.toList());

                System.out.println(filteredList);
                return filteredList;
            }else{throw new RuntimeException("the tag id is not define");}
        }catch(Exception e){throw new RuntimeException(e.getMessage());}

    }
}
