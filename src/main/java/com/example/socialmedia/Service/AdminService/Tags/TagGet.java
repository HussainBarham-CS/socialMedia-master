package com.example.socialmedia.Service.AdminService.Tags;

import com.example.socialmedia.Model.Collection.Tag;
import com.example.socialmedia.Model.Dto.TagDto;
import com.example.socialmedia.Model.Repostiroy.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagGet {

    @Autowired
    private TagRepo tagRepo;

    public List<String> getTags(){
        try{
            List<Tag> temp = tagRepo.findAll();
            List<String> result = temp.stream().map(e->e.getTag()).collect(Collectors.toList());
            return result;
        }catch (Exception e){throw new RuntimeException(e.getMessage());}
    }
}
