package com.example.service;

import com.example.entity.PostEntity;
import com.example.mapper.Mapper;
import com.example.dto.ProfileDTO;
import com.example.entity.ContactEntity;
import com.example.entity.ProfileEntity;
import com.example.exp.CreateException;
import com.example.exp.ItemNotFoundException;
import com.example.repository.ContactRepository;
import com.example.repository.PostRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactService contactService;
    @Autowired
    private PostRepository postRepository;

    public String save(ProfileDTO profileDTO){

        ProfileEntity byContactID = repository.findByContact_Id(profileDTO.getContactId());
        if (Optional.ofNullable(byContactID).isPresent()){
            throw new CreateException("this id contact exists");
        }
        Optional<ContactEntity> byId = contactRepository.findById(profileDTO.getContactId());

        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id contact not found");
        }

        ProfileEntity profileEntity =new ProfileEntity();
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setContact(byId.get());
        repository.save(profileEntity);
        return "Successefully added";

    }

    public List<ProfileDTO> getProfileList() {

        Iterable<ProfileEntity>profileEntities=repository.findAll();
        return profileDTOS(profileEntities);

    }
    public List<ProfileDTO>profileDTOS(Iterable<ProfileEntity>profileEntities){
        List<ProfileDTO> profileDTOList=new ArrayList<>();
        for (ProfileEntity profileEntity : profileEntities) {
            ProfileDTO dto=new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setName(profileEntity.getName());
            dto.setName(profileEntity.getSurname());
            dto.setContactId(profileEntity.getId());
            profileDTOList.add(dto);
        }
        return profileDTOList;

        }


    public String updateProfile(ProfileDTO dto, Integer id) {
        Optional<ProfileEntity> byId1 = repository.findById(id);
        if(byId1.isEmpty()){
          throw new ItemNotFoundException("this id profile is not exists");
        }
        Optional<ContactEntity> byId = contactRepository.findById(dto.getContactId());
        if(byId.isEmpty()){
          throw new ItemNotFoundException("this id contact is not exists");
        }
        byId1.get().setName(dto.getName());
        byId1.get().setSurname(dto.getSurname());
        byId1.get().setContact(byId.get());

        repository.save(byId1.get());
        return "Successefully updated";
    }

    public String deleteProfile(Integer id) {
        Optional<ProfileEntity> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id profile is not found");
        }
        repository.delete(byId.get());
        return "Successefully deleted";
    }

    public  ProfileDTO getProfileByContact(Integer contactId) {


        ProfileEntity profileEntity= repository.findByContact_Id(contactId);
        if(profileEntity==null){
            throw new ItemNotFoundException("this contact id is not found");
        }
        ProfileDTO dto=new ProfileDTO();
        dto.setId(profileEntity.getId());
        dto.setName(profileEntity.getName());
        dto.setSurname(profileEntity.getSurname());
        dto.setContactId(contactId);
        return dto;

    }
    public ProfileEntity getProfileByContactObject(Integer contactID){

        Optional<ContactEntity> contact = contactRepository.findById(contactID);
        if(contact.isEmpty()){
            throw new ItemNotFoundException("this id contact not found");
        }
        ProfileEntity byContact = repository.findByContact(contact.get());
       return byContact;
    }

    public Mapper getProfileByICP(Integer profileID) {

        Mapper byProfileId = repository.findByProfileId(profileID);
        return byProfileId;
    }

    public Mapper getProfileByICPC(Integer contactID) {

        Mapper byContactId = repository.findByContactId(contactID);
        return byContactId;
    }

    public ProfileDTO getProfileByPostID(Integer postID) {
//        Optional<PostEntity> byId = postRepository.findById(postID);
//        ProfileEntity profileEntity = byId.get().getProfile();


        ProfileEntity byPostId = repository.findByPostId(postID);
        if(byPostId==null){
            throw new ItemNotFoundException("this post id is not found");
        }

        ProfileDTO dto=new ProfileDTO();
        dto.setId(byPostId.getId());
        dto.setName(byPostId.getName());
        dto.setContactId(byPostId.getContact().getId());
        return dto;
    }
}
