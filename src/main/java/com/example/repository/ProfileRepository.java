package com.example.repository;

import com.example.mapper.Mapper;
import com.example.entity.ContactEntity;
import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends PagingAndSortingRepository<ProfileEntity,Integer>,CrudRepository<ProfileEntity,Integer> {


   ProfileEntity findByContact_Id(Integer id);

   @Query("select e from ProfileEntity e where e.contact=:contact")
   ProfileEntity findByContact(@Param("contact") ContactEntity contact);

   @Query("select new com.example.mapper.Mapper(p.id,p.name,c.phone) from ProfileEntity p inner join p.contact c where p.id=:profileID")
   Mapper findByProfileId(@Param("profileID") Integer profileID);


   @Query("select new com.example.mapper.Mapper(p.id,p.name,c.phone) from ProfileEntity p inner join p.contact c where p.contact.id=:contactID")
   Mapper findByContactId(@Param("contactID") Integer contactID);

   @Query(" select pf from ProfileEntity pf inner join pf.post p where  p.id=:postID")
   ProfileEntity findByPostId(@Param("postID") Integer postID);

}
