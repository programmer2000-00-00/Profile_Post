package com.example.repository;

import com.example.entity.CommentEntity;
import com.example.entity.PostEntity;
import com.example.entity.ProfileEntity;
import com.example.mapper.MapperComment1;
import com.example.mapper.UniversalMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.xml.stream.events.Comment;
import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Integer>, CrudRepository<CommentEntity, Integer> {

    Page<CommentEntity> getCommentEntitiesBy(Pageable pageable);

    @Query("select c from CommentEntity c where c.profile.id=:id")
    List<CommentEntity> getList1(@Param("id") Integer id);

    @Query("select  new com.example.mapper.MapperComment1(ct.id,ct.content,p.id,p.name) from CommentEntity ct inner join ct.profile p where p.id=:id")
    List<MapperComment1> getList2(@Param("id") Integer id);

    @Query("select  p from CommentEntity ct inner join ct.profile p where ct.id=:id")
    List<ProfileEntity> getList3(@Param("id") Integer id);

    @Query("select  count(p.id) from CommentEntity ct inner join ct.profile p where p.id=:id")
    Integer getList4(@Param("id") Integer id);

    @Query("select  count(p.id) from CommentEntity ct inner join ct.post p where p.id=:id")
    Integer getList5(@Param("id") Integer id);

    @Query(value = "select ct.* from comment as ct inner join post as p on p.id=ct.post_id where ct.post_id=:id order by p.created_date desc limit 1 ", nativeQuery = true)
    CommentEntity getList6(@Param("id") Integer id);

    @Query("select c from CommentEntity c inner join c.post p " +
            "inner join p.profile pf where pf.id=:id")
    Page<CommentEntity> getList7(@Param("id") Integer id, Pageable pageable);

    @Query("select new com.example.mapper.UniversalMapper(c.post.id,c.post.title,c.id,c.content,c.createdDate,c.profile.id,c.profile.name) from CommentEntity as c where c.profile.id=:pId")
    Page<UniversalMapper> getPageByProfileId(@Param("pId") Integer pId, Pageable pageable);

    @Query("select  p from CommentEntity ct inner join  ct.post p where ct.createdDate between  :fromDate and :toDate ")
    List<PostEntity> getList8(@Param("fromDate")LocalDate date1, @Param("toDate")LocalDate date2 );
}
