package com.example.socialmedia.Service.AdminService.Tags;

import com.example.socialmedia.Model.Collection.Tag;

import com.example.socialmedia.Model.Repostiroy.TagRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagDelete {


    @Autowired
    private TagRepo tagRepo;



    public String deleteTag(String tagId){
        try{
            Optional<Tag> test = tagRepo.findById(tagId);
            if(test.isPresent()){
                tagRepo.deleteById(tagId);
                return "Deleted";
            }else{throw new RuntimeException("the id is not exist");}
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
