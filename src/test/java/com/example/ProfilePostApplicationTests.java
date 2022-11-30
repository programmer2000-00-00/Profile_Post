package com.example;

import com.example.dto.ProfileDTO;
import com.example.entity.ContactEntity;
import com.example.repository.CommentRepository;
import com.example.service.CommentService;
import com.example.service.ContactService;
import com.example.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class ProfilePostApplicationTests {
	@Autowired
   private ContactService contactService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentRepository commentRepository;
	@Test
	void contextLoads() {
		ContactEntity contactByProfileEntity = contactService.getContactByProfileEntity(3);
		System.out.println(contactByProfileEntity);
	}
	@Test
	void contextLoads1() {
		ProfileDTO profileByContact = profileService.getProfileByContact(1);
		System.out.println(profileByContact);
	}
	@Test
	void contextLoads12() {
		System.out.println(commentService.list6(1, 0, 1));
	}
	@Test
	void contextLoads123() {
		Pageable pageable= PageRequest.of(0,1);
		System.out.println(commentRepository.getPageByProfileId(1, pageable));
	}
}
