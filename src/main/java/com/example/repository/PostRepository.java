package com.example.repository;

import com.example.entity.PostEntity;
import com.example.mapper.MapperBYPost;
import com.example.mapper.MapperByPost1;
import com.example.mapper.MapperPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<PostEntity,Integer>, CrudRepository<PostEntity,Integer> {

    PostEntity findByProfileId(Integer profileId);

    @Query("select  p from PostEntity as p where p.profile.id=:id")
    Page<PostEntity> getPostList(@Param("id")Integer id, Pageable pageable);
    @Query("select new com.example.mapper.MapperPost(pf.id,pf.name,p.title) from PostEntity as p inner join p.profile pf where p.profile.id=:id")
    Page<MapperPost> getPostListINT(Integer id, Pageable pageable);
    @Query("select new PostEntity(p.title,p.createdDate) from PostEntity as p where p.id=:postID")
    PostEntity getTitleAndDate(@Param("postID") Integer postID);
    @Query("select new PostEntity(p.title) from PostEntity as p inner join p.profile pf where p.profile.id=:profileID")
    List<PostEntity> getAllTitle(@Param("profileID") Integer profileID);
    @Query(value ="select  p.*  from post as p inner join profile as pf on p.profile_id=pf.id " +
            " where pf.id=:profile_id order by p.id desc limit 5 ",nativeQuery = true)
    List<PostEntity> getAllPost(@Param("profile_id") Integer profile_id);
    @Query(value ="select  count(pf.id)  from post as p inner join profile as pf on p.profile_id=pf.id " +
            " where pf.id=:profile_id ",nativeQuery = true)
    Integer getAllPostcount(@Param("profile_id") Integer profile_id);

    @Query(value ="select  count(pf.id)  from post as p inner join profile as pf on p.profile_id=pf.id " +
            " where pf.id=:profile_id and p.created_date=current_date",nativeQuery = true)
    Integer getAllPostcountToday(@Param("profile_id") Integer profile_id);

    @Query("select new com.example.mapper.MapperBYPost(pf.id,p.id,p.createdDate) from PostEntity as p inner join p.profile pf where p.profile.id=:id")
     List<MapperBYPost> getPostList1(@Param("id")Integer id);

    @Query("select new PostEntity(p.id) from PostEntity as p")
    List<PostEntity> getPOstIDList();

    @Query("select new PostEntity(p.profile) from PostEntity as p")
    List<PostEntity> getPostList3();

    @Query("select new PostEntity(p.createdDate) from PostEntity as p")
    List<PostEntity> getPostList4();

    @Query("select  p from PostEntity as p where p.createdDate=current_date ")
    List<PostEntity> getPostList5();

    @Query("select new com.example.mapper.MapperByPost1(p.id,p.title,pf.id,pf.name) from PostEntity as p inner join p.profile pf where p.createdDate=current_date ")
    List<MapperByPost1> getPostList6();

}
