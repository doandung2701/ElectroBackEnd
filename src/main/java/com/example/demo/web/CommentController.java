package com.example.demo.web;

import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.payload.EditCommentRequest;
import com.example.demo.payload.PostReplyRequest;
import com.example.demo.service.CommentService;
import com.example.demo.service.JwtService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin("*")
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/find/find-all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("/find/find-by-user-id")
    public ResponseEntity<?> findByUserId(@RequestParam("userId") Long id) {
        return ResponseEntity.ok(commentService.findDistinctByUserUserIdAndParentIsNull(id));
    }

    @GetMapping("/find/find-by-product-id")
    public ResponseEntity<?> findByProductId(@RequestParam("productId") String id) {
        return ResponseEntity.ok(commentService.findDistinctByProductProductIdAndParentIsNull(id));
    }

    @PostMapping("/save/post-reply")
    public ResponseEntity<?> saveReply(HttpServletRequest request, @RequestBody PostReplyRequest postReplyRequest) {
        String token = request.getHeader("Authorization");
        Long id = jwtService.getUserIdFromToken(token);
        User u = userService.findById(id);
        Product product = productService.findById(postReplyRequest.getProductId());
        Comment parent = commentService.findById(postReplyRequest.getParentId());
        Comment reply = new Comment(postReplyRequest.getCmtCnt(), new Date(System.currentTimeMillis()), u, product,
                null, parent);
        Comment reply1 = commentService.save(reply);
        if (reply1 == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't save record");
        }
        return ResponseEntity.ok(commentService.findDistinctByProductProductIdAndParentIsNull(postReplyRequest.getProductId()));
    }

    @PostMapping("/save/post-comment")
    public ResponseEntity<?> saveComment(HttpServletRequest request, @RequestBody PostReplyRequest postReplyRequest) {
        String token = request.getHeader("Authorization");
        Long id = jwtService.getUserIdFromToken(token);
        User u = userService.findById(id);
        Product product = productService.findById(postReplyRequest.getProductId());
        Comment comment = new Comment(postReplyRequest.getCmtCnt(), new Date(System.currentTimeMillis()), u, product,
                null, null);
        Comment comment1 = commentService.save(comment);
        if (comment1 == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't save record");
        }
        return ResponseEntity.ok(commentService.findDistinctByProductProductIdAndParentIsNull(postReplyRequest.getProductId()));
    }

    @PutMapping("/save/edit-comment")
    public ResponseEntity<?> editComment(@RequestBody EditCommentRequest editCommentRequest){
        Comment comment = commentService.findById(editCommentRequest.getCmtId());
        comment.setCmtCnt(editCommentRequest.getCmtCnt());
        comment.setEditAt(new Date(System.currentTimeMillis()));
        Comment comment1 = commentService.save(comment);
        if (comment1 == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't save record");
        }
        return ResponseEntity.ok(commentService.findDistinctByProductProductIdAndParentIsNull(comment1.getProduct().getProductId()));
    }

}
