package com.example.demo.web;

import com.example.demo.service.JwtService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/find/find-all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/find/find-by-user-id")
    public ResponseEntity<?> findByUserId(HttpServletRequest request){
        Long userId = jwtService.getUserIdFromToken(request.getHeader("Authorization"));
        return ResponseEntity.ok(orderService.countByUserId(userId));
    }

}
