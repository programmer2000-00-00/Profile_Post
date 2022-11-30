package com.example.repository;

import com.example.entity.ContactEntity;
import com.example.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ContactRepository extends PagingAndSortingRepository<ContactEntity,Integer>,CrudRepository<ContactEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update ContactEntity set name=:name where id=:id ")
    Integer updateContact(@Param("name") String name, @Param("id") Integer id);

    ContactEntity findByProfileId(Integer profileID);

    @Query("select e from ContactEntity e where e.profile=:profile  ")
    ContactEntity findByProfile(@Param("profile") Optional<ProfileEntity> profile);
}

