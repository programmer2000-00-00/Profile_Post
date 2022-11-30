package com.example.controller;

import com.example.dto.ContactDTO;
import com.example.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<?>createContact(@RequestBody ContactDTO contactDTO){
         String result=contactService.save(contactDTO);
         return ResponseEntity.ok(result);
    }
    @GetMapping("/get")
    public ResponseEntity<?>getListContact(){
        List<ContactDTO> result=contactService.getContactList();
        return ResponseEntity.ok(result);
    }
    @PutMapping("/update")
    public ResponseEntity<?>updateContact(@RequestBody ContactDTO dto,@RequestParam("id") Integer id){
   String result=contactService.updateContact(dto,id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?>deleteContact(@RequestParam("id") Integer id){
        String result=contactService.deleteContact(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get/id")
    public ResponseEntity<?>getContactById(@PathVariable("id") Integer id){
       ContactDTO contactDTO=contactService.getContactById(id);
        return ResponseEntity.ok(contactDTO);
    }

    @GetMapping("/get/profile/{profileID}")
    public ResponseEntity<?>getProfileById(@PathVariable("profileID") Integer profileID){
        ContactDTO contactDTO=contactService.getByProfileId(profileID);
        return ResponseEntity.ok(contactDTO);
    }


}
