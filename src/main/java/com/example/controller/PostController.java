package com.example.controller;

import com.example.dto.PostDTO;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostDTO dto){
        String result= postService.save(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/get")
    public ResponseEntity<?>getListPost(){
        List<PostDTO> result= postService.getPostList();
        return ResponseEntity.ok(result);
    }
    @PutMapping("/update")
    public ResponseEntity<?>updatePost(@RequestBody PostDTO dto,@RequestParam("id") Integer id){
        String result= postService.updatePost(dto,id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>deletePost(@RequestParam("id") Integer id){
        String result= postService.deletePost(id);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/all/page")
    public  ResponseEntity<?> getPage(@RequestParam("page")Integer page,
                                                  @RequestParam("size")Integer size,
                                                  @RequestParam("id")Integer id){
        return ResponseEntity.ok(postService.getAllByProfile(page,size,id));
    }
    @GetMapping("/all/page/int")
    public  ResponseEntity<?> getPageint(@RequestParam("page")Integer page,
                                      @RequestParam("size")Integer size,
                                      @RequestParam("id")Integer id){
        return ResponseEntity.ok(postService.getAllProfileINT(page,size,id));
    }
    @GetMapping("/all/page/title")
    public  ResponseEntity<?> getPagetitle(@RequestParam("postID")Integer postID){
        return ResponseEntity.ok(postService.getAllTitle(postID));
    }
    @GetMapping("/all/title")
    public  ResponseEntity<?> getAlltitle(@RequestParam("profileID")Integer profileID){
        return ResponseEntity.ok(postService.getAllTitleProfileId(profileID));
    }
    @GetMapping("/all/post")
    public  ResponseEntity<?> getAllPost(@RequestParam("profileID")Integer profileID){
        return ResponseEntity.ok(postService.getAllPost(profileID));
    }

    @GetMapping("/all/post/count")
    public  ResponseEntity<?> getAllPostcount(@RequestParam("profileID")Integer profileID){
        return ResponseEntity.ok(postService.getAllpostCount(profileID));
    }

    @GetMapping("/all/post/count/today")
    public  ResponseEntity<?> getAllPostcounttoday(@RequestParam("id") Integer id){
        return ResponseEntity.ok(postService.getAllpostCountToday(id));
    }

    @GetMapping("/all/post/list1")
    public  ResponseEntity<?> getAllPostList1(@RequestParam("id") Integer id){
        return ResponseEntity.ok(postService.getPostList1(id));
    }

    @GetMapping("/all/post/list2")
    public  ResponseEntity<?> getAllPostList2(){
        return ResponseEntity.ok(postService.getListPostId());
    }
//    @GetMapping("/all/post/list4")
//    public  ResponseEntity<?> getList4(){
//        return ResponseEntity.ok(postService.getList4());
//    }
    @GetMapping("/all/post/list5")
    public  ResponseEntity<?> getList5(){
        return ResponseEntity.ok(postService.getList5());
    }
    @GetMapping("/all/post/list6")
    public  ResponseEntity<?> getList6(){
        return ResponseEntity.ok(postService.getList6());
    }
}
