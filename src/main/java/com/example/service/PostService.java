package com.example.service;

import com.example.dto.PostDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ContactEntity;
import com.example.entity.PostEntity;
import com.example.entity.ProfileEntity;
import com.example.exp.CreateException;
import com.example.exp.ItemNotFoundException;
import com.example.mapper.MapperBYPost;
import com.example.mapper.MapperByPost1;
import com.example.mapper.MapperPost;
import com.example.repository.PostRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProfileRepository profileRepository;
    public String save(PostDTO dto) {

       /* PostEntity byprofile = postRepository.findByProfileId(dto.getProfileID());
        if(Optional.ofNullable(byprofile).isPresent()){
            throw new CreateException("this id profile exists");
        }*/
        Optional<ProfileEntity> byId = profileRepository.findById(dto.getProfileID());
        if(byId.isEmpty()){
           throw new ItemNotFoundException("this id profile is not exists");
        }
       PostEntity entity=new PostEntity();
       entity.setTitle(dto.getTitle());
       entity.setContent(dto.getContent());
       entity.setCreatedDate(LocalDate.now());
       entity.setProfile(byId.get());

       postRepository.save(entity);
       return "successefully added";
    }

    public List<PostDTO> getPostList() {
        Iterable<PostEntity>profileEntities=postRepository.findAll();
        return postDTOS(profileEntities);
    }
    public List<PostDTO>postDTOS(Iterable<PostEntity>postEntities){
        List<PostDTO> postDTOList=new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            PostDTO dto=new PostDTO();
            dto.setId(postEntity.getId());
            dto.setTitle(postEntity.getTitle());
            dto.setContent(postEntity.getContent());
            dto.setProfileID(postEntity.getId());
            postDTOList.add(dto);
        }

        return postDTOList;

    }

    public String updatePost(PostDTO dto, Integer id) {
        Optional<PostEntity> byId = postRepository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id is not found");
        }
        Optional<ProfileEntity> byId1 = profileRepository.findById(dto.getProfileID());
        if(byId1.isEmpty()){
            throw new ItemNotFoundException("this id profile is not exists");
        }
        byId.get().setTitle(dto.getTitle());
        byId.get().setContent(dto.getContent());
        byId.get().setProfile(byId1.get());

        postRepository.save(byId.get());
        return "Successefully updated";
    }

    public String deletePost(Integer id) {
        Optional<PostEntity> byId = postRepository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id profile is not found");
        }
        postRepository.deleteById(id);
        return "Successefully deleted";
    }
    public  PageImpl<PostDTO> getAllByProfile(Integer page,Integer size,Integer id){
        Pageable pageable= PageRequest.of(page,size);
        Page<PostEntity> pageObj=postRepository.getPostList(id,pageable);
        Iterable<PostEntity> content=pageObj.getContent();
        Long total=pageObj.getTotalElements();
        List<PostDTO> postDTOS=new LinkedList<>();
        for (PostEntity entity:content) {
            PostDTO postDTO=new PostDTO();
            postDTO.setProfileID(entity.getProfile().getId());
            postDTO.setSurname(entity.getSurname());
            postDTO.setTitle(entity.getTitle());
            postDTO.setCreatedDate(entity.getCreatedDate());
            postDTO.setContent(entity.getContent());
            postDTOS.add(postDTO);
        }
        PageImpl<PostDTO> all=new PageImpl<>(postDTOS,pageable,total);
        return all;
    }

    public  PageImpl<MapperPost> getAllProfileINT(Integer page, Integer size, Integer id) {
        Pageable pageable= PageRequest.of(page,size);
        Page<MapperPost> pageObj=postRepository.getPostListINT(id,pageable);
        List<MapperPost> content=pageObj.getContent();
        Long total=pageObj.getTotalElements();
//        List<PostDTO> postDTOS=new LinkedList<>();
//        for (MapperPost entity:content) {
//            PostDTO postDTO=new PostDTO();
//            postDTO.setProfileID(entity.getId());
//            postDTO.setSurname(entity.getName());
//            postDTO.setTitle(entity.getTitle());
//            postDTOS.add(postDTO);
//        }
        PageImpl<MapperPost> all=new PageImpl<>(content,pageable,total);
        return all;
    }

    public PostDTO getAllTitle(Integer postID) {
        PostEntity titleAndDate = postRepository.getTitleAndDate(postID);
        PostDTO postDTO=new PostDTO();
        postDTO.setTitle(titleAndDate.getTitle());
        postDTO.setCreatedDate(titleAndDate.getCreatedDate());
        return postDTO;
    }

    public List<PostDTO> getAllTitleProfileId(Integer profileID) {
        List<PostEntity> getTitleALL=postRepository.getAllTitle(profileID);
        List<PostDTO> getPostDTO=new LinkedList<>();
        for (PostEntity entity : getTitleALL) {
            PostDTO dto=new PostDTO();
            dto.setTitle(entity.getTitle());
            getPostDTO.add(dto);
        }
      return getPostDTO;
    }
    public List<PostDTO> getAllPost(Integer profileID){
        List<PostEntity> allPost = postRepository.getAllPost(profileID);
        List<PostDTO> postDTOS=new LinkedList<>();
        for (PostEntity entity:allPost) {
            PostDTO postDTO=new PostDTO();
            postDTO.setProfileID(entity.getId());
            postDTO.setSurname(entity.getSurname());
            postDTO.setTitle(entity.getTitle());
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }

    public Integer getAllpostCount(Integer profileID){

        Integer allPostcount = postRepository.getAllPostcount(profileID);
        return allPostcount;
    }
    public Integer getAllpostCountToday(Integer profileID){

        Integer allPostcount = postRepository.getAllPostcountToday(profileID);
        return allPostcount;
    }

    public List<MapperBYPost> getPostList1(Integer id){

        List<MapperBYPost> postList1 = postRepository.getPostList1(id);
        return postList1;
    }
    public List<PostDTO> getListPostId(){
        List<PostEntity> postEntityListt = postRepository.getPOstIDList();
        List<PostDTO> postDTOS=new LinkedList<>();
        for (PostEntity entity : postEntityListt) {
            PostDTO dto=new PostDTO();
            dto.setId(entity.getId());
            postDTOS.add(dto);

        }
        return postDTOS;

    }
    public List<PostDTO> getList3(){
        List<PostEntity> postEntityListt = postRepository.getPostList3();
        List<PostDTO> postDTOS=new LinkedList<>();
        for (PostEntity entity : postEntityListt) {
            PostDTO dto=new PostDTO();
             dto.setProfileID(entity.getProfile().getId());
             postDTOS.add(dto);
        }
        return postDTOS;
    }
    public List<PostDTO> getList5(){
        List<PostEntity> postEntityListt = postRepository.getPostList5();
        List<PostDTO> postDTOS=new LinkedList<>();
        for (PostEntity entity : postEntityListt) {
            PostDTO dto=new PostDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
//            dto.setSurname(entity.);
            dto.setContent(entity.getContent());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setProfileID(entity.getProfile().getId());
            postDTOS.add(dto);
        }
        return postDTOS;
    }
    public List<MapperByPost1> getList6(){
        List<MapperByPost1> postEntityListt = postRepository.getPostList6();
        return postEntityListt;
    }
}
