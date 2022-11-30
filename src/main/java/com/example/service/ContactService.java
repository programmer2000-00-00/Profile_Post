package com.example.service;

import com.example.dto.CommentDTO;
import com.example.dto.ContactDTO;
import com.example.entity.ContactEntity;
import com.example.entity.ProfileEntity;
import com.example.exp.CreateException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ContactRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    public String save(ContactDTO contactDTO){

        ContactEntity contactEntity=new ContactEntity();
        contactEntity.setName(contactDTO.getName());
        contactEntity.setPhone(contactDTO.getPhone());
        repository.save(contactEntity);
        return "Successefully added";

    }

    public List<ContactDTO> getContactList(){

        Iterable<ContactEntity>contactEntities=repository.findAll();
        return createDTOlist(contactEntities);

    }
    public ContactDTO getContactById(Integer id){
       Optional <ContactEntity> contact=repository.findById(id);
        ContactDTO dto=new ContactDTO();
        dto.setName(contact.get().getName());

        return dto;

    }


    public List<ContactDTO>createDTOlist(Iterable<ContactEntity>contactEntities){

        List<ContactDTO> contactDTOList=new ArrayList<>();
        for (ContactEntity contactEntity : contactEntities) {

            ContactDTO dto=new ContactDTO();
            dto.setId(contactEntity.getId());
            dto.setName(contactEntity.getName());
            contactDTOList.add(dto);
        }
        return contactDTOList;
    }

    public String updateContact(ContactDTO contactDTO,Integer id) {
        Integer i = repository.updateContact(contactDTO.getName(), id);
        if (i != 0) {
            return "Successefully updated";
        }
        return "Something is wrong";
    }

    public String deleteContact(Integer id) {

        Optional<ContactEntity> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id contact is not found");
        }

      repository.deleteById(id);
        return "Successefully deleted";
    }

    public ContactDTO getByProfileId(Integer profileID){
             profileRepository.findById(profileID).orElseThrow(() -> {
             throw new ItemNotFoundException("this id profile not found"); });

            ContactEntity contactEntity=repository.findByProfileId(profileID);
            ContactDTO dto=new ContactDTO();
            dto.setId(contactEntity.getId());
            dto.setName(contactEntity.getName());
            dto.setPhone(contactEntity.getPhone());
            return dto;
    }

    public ContactEntity getContactByProfileEntity(Integer id){

       Optional<ProfileEntity> profile=profileRepository.findById(id);
       if(profile.isEmpty()){
           throw new ItemNotFoundException("this profile is not exists");
       }

       return    repository.findByProfile(profile);

    }
}
