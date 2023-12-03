package com.example.socialmedia.Service.AdminService.Tags;

import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Dto.TagDto;
import com.example.socialmedia.Model.Repostiroy.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TagCreate {


    @Autowired
    private TagRepo tagRepo;



    public TagDto createTag(TagDto tagDto){
        try{
            Optional<String> test = Optional.ofNullable(tagRepo.findByTag(tagDto.getTag()));
            if(test.isPresent()){
                throw new RuntimeException("the tag is already define on database :(");
            }
            else return TagDto.toDto(tagRepo.save(Tag.toEntity(tagDto)));
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
