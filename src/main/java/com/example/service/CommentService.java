package com.example.service;

import com.example.dto.CommentDTO;
import com.example.dto.PostDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.UniversalMapperDTO;
import com.example.entity.CommentEntity;
import com.example.entity.PostEntity;
import com.example.entity.ProfileEntity;
import com.example.exp.ItemNotFoundException;
import com.example.mapper.MapperComment1;
import com.example.mapper.UniversalMapper;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.projection.EntityProjection.ProjectionType.DTO;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProfileRepository profileRepository;
    public String save(CommentDTO dto) {
        Optional<PostEntity> byId = postRepository.findById(dto.getPostID());
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id post is not exists");
        }
        Optional<ProfileEntity> byId1 = profileRepository.findById(dto.getProfileID());
        if(byId1.isEmpty()){
            throw new ItemNotFoundException("this id profile is not exists");
        }
        CommentEntity entity=new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setPost(byId.get());
        entity.setCreatedDate(LocalDate.now());
        entity.setProfile(byId1.get());

        repository.save(entity);
        return "successefully added";
    }

    public List<CommentDTO> getCommentList() {
        Iterable<CommentEntity>commentEntities=repository.findAll();
        return commentDTOS(commentEntities);
    }
    public List<CommentDTO> commentDTOS(Iterable<CommentEntity>commentEntities){
        List<CommentDTO> commnetDTOList=new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentDTO dto=new CommentDTO();
            dto.setId(commentEntity.getId());
            dto.setContent(commentEntity.getContent());
            dto.setPostID(commentEntity.getPost().getId());
            dto.setProfileID(commentEntity.getProfile().getId());
            dto.setCreatedDate(commentEntity.getCreatedDate());
            commnetDTOList.add(dto);
        }
        return commnetDTOList;
        }


    public String updateComment(CommentDTO dto, Integer id) {

        Optional<CommentEntity> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id comment  is not found");
        }
        Optional<PostEntity> byId1 = postRepository.findById(dto.getPostID());
        if(byId1.isEmpty()){
            throw new ItemNotFoundException("this id post is not exists");
        }
        Optional<ProfileEntity> byId2 = profileRepository.findById(dto.getProfileID());
        if(byId2.isEmpty()){
            throw new ItemNotFoundException("this id profile is not exists");
        }
        byId.get().setContent(dto.getContent());
        byId.get().setPost(byId1.get());
        byId.get().setProfile(byId2.get());

        repository.save(byId.get());
        return "Successefully updated";
    }

    public String deleteComment(Integer id) {
        Optional<CommentEntity> byId = repository.findById(id);
        if(byId.isEmpty()){
            throw new ItemNotFoundException("this id comment is not found");
        }
        repository.deleteById(id);
        return "Successefully deleted";
    }
    public Page<CommentDTO> list(Integer page,Integer size){
        Pageable pageable= PageRequest.of(page,size);
        Page<CommentEntity> commentEntitiesBy = repository.getCommentEntitiesBy(pageable);

        List<CommentEntity> content=commentEntitiesBy.getContent();
        Long totalPage=commentEntitiesBy.getTotalElements();

        List<CommentDTO>dtoList=new LinkedList<>();
        for (CommentEntity entity : content) {
            CommentDTO dto=new CommentDTO();
            dto.setId(entity.getId());
            dto.setContent(entity.getContent());
            dto.setProfileID(entity.getProfile().getId());
            dto.setPostID(entity.getPost().getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        Page<CommentDTO> commentDTOS = new PageImpl<>(dtoList, pageable, totalPage);
        return commentDTOS;

    }
    public List<CommentDTO> list1(Integer id){
        List<CommentEntity> entityList=repository.getList1(id);
        List<CommentDTO> dtoList=new ArrayList<>();
        for (CommentEntity entity : entityList) {
            CommentDTO dto=new CommentDTO();
            dto.setId(entity.getId());
            dto.setContent(entity.getContent());
            dto.setPostID(entity.getPost().getId());
            dto.setProfileID(entity.getProfile().getId());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
    public List<MapperComment1>list2(Integer id){

        List<MapperComment1> list=repository.getList2(id);
        return list;
    }
    public List<ProfileDTO> list3(Integer id){
        List<ProfileEntity>list=repository.getList3(id);
        List<ProfileDTO>profileDTOList=new ArrayList<>();
        for (ProfileEntity profileEntity : list) {
            ProfileDTO dto=new ProfileDTO();
            dto.setId(profileEntity.getId());
            dto.setName(profileEntity.getName());
            dto.setSurname(profileEntity.getSurname());
            dto.setContactId(profileEntity.getId());
            profileDTOList.add(dto);
        }
        return profileDTOList;
    }
    public Integer getList4(Integer id){
        Integer list3 = repository.getList4(id);
        return list3;
    }
    public Integer getList5(Integer id){
        Integer list3 = repository.getList5(id);
        return list3;
    }
    public CommentDTO list5(Integer id){
     CommentEntity entity=repository.getList6(id);

            CommentDTO dto=new CommentDTO();
            dto.setId(entity.getId());
            dto.setContent(entity.getContent());
            dto.setPostID(entity.getPost().getId());
            dto.setProfileID(entity.getProfile().getId());
            dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
    public Page<UniversalMapperDTO> list6(Integer id, Integer page, Integer size){
        Pageable pageable= PageRequest.of(page,size);
        Page<UniversalMapper> commentEntitiesBy = repository.getPageByProfileId(id,pageable);
        List<UniversalMapper> list=commentEntitiesBy.getContent();
        Long totalPage=commentEntitiesBy.getTotalElements();
        List<UniversalMapperDTO> dto=new ArrayList<>();
        for (UniversalMapper universalMapper : list) {
            UniversalMapperDTO universalMapperDTO=new UniversalMapperDTO();
            PostDTO postDTO=new PostDTO(universalMapper.getPostId(),universalMapper.getPostTitle());
            CommentDTO commentDTO=new CommentDTO(universalMapper.getCommentId(),universalMapper.getContent(),universalMapper.getCreatedDate());
            ProfileDTO profileDTO=new ProfileDTO(universalMapper.getProfileId(),universalMapper.getProfileName());
            universalMapperDTO.setCommentDTO(commentDTO);
            universalMapperDTO.setProfileDTO(profileDTO);
            universalMapperDTO.setPostDTO(postDTO);
            dto.add(universalMapperDTO);
        }
        Page<UniversalMapperDTO> universalMapperDTOS = new PageImpl<>(dto, pageable, totalPage);
        return universalMapperDTOS;
    }
    public List<PostDTO> list9(String fromDate,String toDate){

        List<PostEntity> list8 = repository.getList8(LocalDate.parse(fromDate), LocalDate.parse(toDate));
        List<PostDTO> postDTOList=new ArrayList<>();
        for (PostEntity entity : list8) {
            PostDTO dto=new PostDTO();
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setSurname(entity.getSurname());
            dto.setContent(entity.getContent());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setProfileID(entity.getProfile().getId());
            postDTOList.add(dto);

        }
        return postDTOList;

    }
}
