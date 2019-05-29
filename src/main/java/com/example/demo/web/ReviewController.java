package com.example.demo.web;

import com.example.demo.model.Product;
import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.payload.AddReviewRequest;
import com.example.demo.service.JwtService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/reviews")
@CrossOrigin("*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/find-by-product-id")
    public ResponseEntity<?> findReviewByProductId(@RequestParam("productId") String id){
        return ResponseEntity.ok(reviewService.findByProductId(id));
    }

    @PostMapping(value="/save-review")
    public ResponseEntity<?> addReview(HttpServletRequest request, @RequestBody AddReviewRequest reviewRequest){
        String token = request.getHeader("Authorization");
        Long id = jwtService.getUserIdFromToken(token);
        User u = userService.findById(id);
        Product p = productService.findById(reviewRequest.getProductId());
        Review r = new Review(new Date(System.currentTimeMillis()),reviewRequest.getReviewContent(),u,p,
                reviewRequest.getReviewScore(),reviewRequest.getReviewHeader()
                );
        Review r1 = reviewService.save(r);

        if (r1==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't save record");
        }
        return ResponseEntity.ok(reviewService.findByProductId(reviewRequest.getProductId()));
    }

}
