package com.example.controller;

import com.example.dto.CommentDTO;
import com.example.dto.PostDTO;
import com.example.dto.ProfileDTO;
import com.example.mapper.MapperComment1;
import com.example.mapper.UniversalMapper;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")

public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentDTO dto){
        String result=commentService.save(dto);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get")
    public ResponseEntity<?>getListComment(){
        List<CommentDTO> result=commentService.getCommentList();
        return ResponseEntity.ok(result);
    }
    @PutMapping("/update")
    public ResponseEntity<?>updateComment(@RequestBody CommentDTO dto,@RequestParam("id") Integer id){
        String result=commentService.updateComment(dto,id);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>deleteComment(@RequestParam("id") Integer id){
        String result=commentService.deleteComment(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getList")
    public ResponseEntity<?>getList1(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        Page<CommentDTO> list =commentService.list(page,size);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/getList1")
    public ResponseEntity<?>getList2(@RequestParam("id") Integer id){
        List<CommentDTO> list =commentService.list1(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/getList2")
    public ResponseEntity<?>getList3(@RequestParam("id") Integer id){
        List<MapperComment1> list =commentService.list2(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/getList3")
    public ResponseEntity<?>getList4(@RequestParam("id") Integer id){
        List<ProfileDTO> list =commentService.list3(id);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/getList4")
    public ResponseEntity<?>getList5(@RequestParam("id") Integer id){
       Integer res =commentService.getList4(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getList5")
    public ResponseEntity<?>getList6(@RequestParam("id") Integer id){
        Integer res =commentService.getList5(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getList6")
    public ResponseEntity<?>getList7(@RequestParam("id") Integer id){
        CommentDTO res =commentService.list5(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/getList7")
    public ResponseEntity<?>getList8(@RequestParam("id") Integer id,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size){
        return ResponseEntity.ok(commentService.list6(id,page,size));
    }
    @GetMapping("/getList8")
    public ResponseEntity<?>getList9(@RequestParam("date1") String date1,
                                     @RequestParam("date2") String date2){
        return ResponseEntity.ok(commentService.list9(date1,date2));
    }
}
