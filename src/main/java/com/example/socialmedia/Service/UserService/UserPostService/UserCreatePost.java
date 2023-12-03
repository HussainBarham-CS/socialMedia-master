package com.example.socialmedia.Service.UserService.UserPostService;

import com.example.socialmedia.CurrentUser;
import com.example.socialmedia.Model.Collection.Post;
import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Dto.PostDto;
import com.example.socialmedia.Model.Dto.TagDto;
import com.example.socialmedia.Model.Repostiroy.PostRepo;
import com.example.socialmedia.Model.Repostiroy.TagRepo;
import com.example.socialmedia.Security.UserRepository;
import com.example.socialmedia.UserPreview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCreatePost extends CurrentUser {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private TagRepo tagRepo;

    public boolean areAllTagsInRepository(PostDto postDto) {
        List<Tag> tagsFromRepository = tagRepo.findAll();

        List<String> tagNamesFromRepository = tagsFromRepository.stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());

        List<TagDto> tagsFromPostDto = postDto.getTags();
        List<String> temp =  tagsFromPostDto.stream()
                .map(e->e.getTag())
                .collect(Collectors.toList());

        return tagNamesFromRepository.containsAll(temp);
    }


    public PostDto create(PostDto postDto) {
        try {
            if(areAllTagsInRepository(postDto)){
                postDto.setPublishDate(new Date());
                postDto.setOwner(UserPreview.toUserPreview(getCurrentUser()));
                List<Tag> tagsFromRepository = tagRepo.findAll();
                List<TagDto> listTags = new ArrayList<>();

                for(int i =0;i<postDto.getTags().size();i++)
                {
                    for(int j=0;j<tagsFromRepository.size();j++){
                        if(postDto.getTags().get(i).getTag().equals(tagsFromRepository.get(j).getTag()))
                        {
                            TagDto temp = TagDto.builder()
                                    .id(tagsFromRepository.get(j).getId())
                                    .tag(postDto.getTags().get(i).getTag())
                                    .build();
                            if(!listTags.contains(temp)){listTags.add(temp);}
                        }
                    }

                }
                postDto.setTags(listTags);
                return PostDto.toDto(postRepo.save(Post.toEntity(postDto)));
            }
            else {
                List<Tag> tagsFromRepository = tagRepo.findAll();
                List<String> tagNamesFromRepository = tagsFromRepository.stream()
                        .map(Tag::getTag)
                        .collect(Collectors.toList());
                String allTags="(";
                for (int i =0;i<tagNamesFromRepository.size();i++){
                    allTags+=tagNamesFromRepository.get(i);
                    if(i<tagNamesFromRepository.size()-1){allTags+=")  (";}
                    else{allTags+=")";}

                }
                throw new RuntimeException("the tags must be just "+allTags);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
