package com.example.demo.web;

import com.example.demo.model.AddressType;
import com.example.demo.service.AddressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address-types")
@CrossOrigin("*")
public class AddressTypeController {

    @Autowired
    private AddressTypeService addressTypeService;

    @GetMapping("/find/find-all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(addressTypeService.findAll());
    }

    @GetMapping("/find/find-by-id")
    public ResponseEntity<?> findById(@RequestParam("addressTypeId") Long id){
        return ResponseEntity.ok(addressTypeService.findById(id));
    }

}
