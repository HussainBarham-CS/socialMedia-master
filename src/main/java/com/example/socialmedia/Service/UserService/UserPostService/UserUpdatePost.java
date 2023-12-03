package com.example.socialmedia.Service.UserService.UserPostService;

import com.example.socialmedia.AppUserUpdate;
import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.Model.Dto.TagDto;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.Model.Repostiroy.TagRepo;
import com.example.socialmedia.PostDtoUpdate;
import com.example.socialmedia.Security.AppUser;
import com.example.socialmedia.Security.AppUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserUpdatePost extends CurrentUser {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TagRepo tagRepo;


    public boolean areAllTagsInRepository(PostDtoUpdate postDtoUpdate) {
        List<Tag> tagsFromRepository = tagRepo.findAll();

        List<String> tagNamesFromRepository = tagsFromRepository.stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());

        List<TagDto> tagsFromPostDto = postDtoUpdate.getTags();
        List<String> temp =  tagsFromPostDto.stream()
                .map(e->e.getTag())
                .collect(Collectors.toList());

        return tagNamesFromRepository.containsAll(temp);
    }

    public PostDtoUpdate update(String postId, PostDtoUpdate postDtoUpdate) {
        try{
            Optional<Post> check = postRepo.findById(postId);
            if(check.isPresent()){
                if(check.get().getOwner().getId().equals(getCurrentUser().getId())){
                    if(postDtoUpdate.getOwner()==null){
                        postDtoUpdate.setOwner(check.get().getOwner());
                        if(postDtoUpdate.getLikes()==0){
                            if(postDtoUpdate.getLikes()==0){postDtoUpdate.setLikes(check.get().getLikes());}
                            if(postDtoUpdate.getText()==null){postDtoUpdate.setText(check.get().getText());}
                            if(postDtoUpdate.getLink()==null){
                                if(areAllTagsInRepository(postDtoUpdate)){
                                    postDtoUpdate.setLink(check.get().getLink());
                                    postDtoUpdate.setPublishDate(check.get().getPublishDate());
                                    postDtoUpdate.setId(check.get().getId());
                                    if(postDtoUpdate.getTags()==null){postDtoUpdate.setTags(check.get().getTags());}
                                    return PostDtoUpdate.toDto(postRepo.save(Post.toEntity1(postDtoUpdate)));
                                }else
                                {
                                    List<Tag> tagsFromRepository = tagRepo.findAll();
                                    List<String> tagNamesFromRepository = tagsFromRepository.stream()
                                            .map(Tag::getTag)
                                            .collect(Collectors.toList());
                                    String allTags="(";
                                    for (int i =0;i<tagNamesFromRepository.size();i++){
                                        allTags+=tagNamesFromRepository.get(i);
                                        if(i<tagNamesFromRepository.size()-1){allTags+=")  (";}
                                        else{allTags+=")";}
                                    }throw new RuntimeException("the tags must be just "+allTags);
                                }
                            }else{throw new RuntimeException("sorry but you can not modify your link on post");}
                        }else{throw new RuntimeException("sorry but you can not modify your likes on post");}
                    }else{throw new RuntimeException("sorry but you can not modify your owner");}
                }else{throw new RuntimeException("it is forbidden to update post to another user");}
            }else{throw new RuntimeException("the post id is not define");}
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
