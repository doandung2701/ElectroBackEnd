package com.example.demo.web;

import com.example.demo.model.AddressType;
import com.example.demo.model.DeliveryAddress;
import com.example.demo.model.User;
import com.example.demo.payload.AddDeliveryAddressRequest;
import com.example.demo.service.AddressTypeService;
import com.example.demo.service.DeliveryAddressService;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/delivery-addresses")
@CrossOrigin("*")
public class DeliveryAddressController {

    @Autowired
    UserService userService;

    @Autowired
    DeliveryAddressService deliveryAddressService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AddressTypeService addressTypeService;

    @PostMapping("/save/add-delivery-address")
    public ResponseEntity<?> addDeliveryAddress(HttpServletRequest request,
                                                @Valid @RequestBody AddDeliveryAddressRequest addressRequest){
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userService.findById(userId);
        AddressType addressType = addressTypeService.findById(addressRequest.getAddressTypeId());
        DeliveryAddress deliveryAddress = new DeliveryAddress(addressRequest.getFullName(),
                addressRequest.getAddress(),addressRequest.getPhone(),addressType,user);
        deliveryAddress = deliveryAddressService.save(deliveryAddress);
        if (deliveryAddress==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
        return ResponseEntity.ok(deliveryAddressService.findByUserId(userId));
    }

    @PutMapping("/save/update-delivery-address")
    public ResponseEntity<?> updateDeliveryAddress(HttpServletRequest request,
                                                @Valid @RequestBody AddDeliveryAddressRequest addressRequest){
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);
        AddressType addressType = addressTypeService.findById(addressRequest.getAddressTypeId());
        DeliveryAddress deliveryAddress = deliveryAddressService.findById(addressRequest.getAddressId());
        deliveryAddress.setAddress(addressRequest.getAddress());
        deliveryAddress.setAddressType(addressType);
        deliveryAddress.setPhone(addressRequest.getPhone());
        deliveryAddress.setFullName(addressRequest.getFullName());
        deliveryAddress = deliveryAddressService.save(deliveryAddress);
        if (deliveryAddress==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
        return ResponseEntity.ok(deliveryAddressService.findByUserId(userId));
    }

    @GetMapping("/find/find-by-user")
    public ResponseEntity<?> findByUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);
        List<DeliveryAddress> deliveryAddresses = deliveryAddressService.findByUserId(userId);
        if (deliveryAddresses==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error!");
        }
        return ResponseEntity.ok(deliveryAddresses);
    }

    @DeleteMapping("/save/delete")
    public ResponseEntity<?> delete(HttpServletRequest request,@NotNull @RequestParam("addressId") Long id){
        DeliveryAddress address = deliveryAddressService.findById(id);
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);
        if (address==null){
            return ResponseEntity.badRequest().body("Wrong addressId");
        }else{
            deliveryAddressService.delete(address);
            return ResponseEntity.ok(deliveryAddressService.findByUserId(userId));
        }
    }

}
