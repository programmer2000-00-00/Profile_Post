package com.example.controller;

import com.example.mapper.Mapper;
import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @PostMapping("/create1")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO dto){
        String result=service.save(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get")
    public ResponseEntity<?>getListProfile(){
        List<ProfileDTO> result=service.getProfileList();
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?>updateProfile(@RequestBody ProfileDTO dto,@RequestParam("id") Integer id){
        String result=service.updateProfile(dto,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?>deleteProfile(@RequestParam("id") Integer id){
        String result=service.deleteProfile(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/contact/{id}")
    public ResponseEntity<?>getProfileByContact(@PathVariable("id") Integer id){
        ProfileDTO result=service.getProfileByContact(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/profileICP/{profileID}")
    public ResponseEntity<?>getProfileByICP(@PathVariable("profileID") Integer profileID){
        Mapper result=service.getProfileByICP(profileID);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/profileICPC/{contactID}")
    public ResponseEntity<?>getProfileByICPC(@PathVariable("contactID") Integer contactID){
        Mapper result=service.getProfileByICPC(contactID);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get/profilePostID/{postID}")
    public ResponseEntity<?>getProfilePostID(@PathVariable("postID") Integer postID){
        ProfileDTO result=service.getProfileByPostID(postID);
        return ResponseEntity.ok(result);
    }
}
