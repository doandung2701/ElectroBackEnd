package com.example.demo.web;

import com.example.demo.model.*;
import com.example.demo.payload.ChargeRequest;
import com.example.demo.payload.PurchaseCartRequest;
import com.example.demo.service.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("*")
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShippingStatusService shippingStatusService;

    @PostMapping("/charge")
    public ResponseEntity<?> charge(HttpServletRequest request, @RequestBody ChargeRequest chargeRequest) {
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userService.findById(userId);
        Long deliveryAddressId = chargeRequest.getDeliveryAddress();
        String paymentMethodId = chargeRequest.getPaymentMethodId();
        List<PurchaseCartRequest> cartRequests = chargeRequest.getPurchaseCartRequests();
        DeliveryAddress deliveryAddress = deliveryAddressService.findById(deliveryAddressId);
        System.out.println("Method: " + paymentMethodId);
        PaymentMethod paymentMethod = paymentMethodService.findById(paymentMethodId);
        Integer quantity=0 ;
        Double total =0d ;
        Double totalOrder = 0d;
        String error ="";
        boolean isError = false;
        List<Long> prodDetailIds = new ArrayList<>();
        for (PurchaseCartRequest cartRequest : cartRequests) {
            prodDetailIds.add(cartRequest.getProdDetailId());
        }
        List<ProductDetail> productDetails = productDetailService.findByProdDetailIdIn(prodDetailIds);
        Set<OrderDetail> orderDetails = new HashSet<>();
        Date now = new Date(System.currentTimeMillis());
        for (ProductDetail detail : productDetails) {
            OrderDetail orderDetail = new OrderDetail();
            if (detail.getDiscountExDate()==null||detail.getDiscountExDate().before(now)){
                orderDetail.setDiscount(0d);
            }else{
                orderDetail.setDiscount(detail.getDiscount());
            }
            orderDetail.setPrice(detail.getPrice());
            orderDetail.setProductDetail(detail);
            for (PurchaseCartRequest cartRequest : cartRequests) {
                if (cartRequest.getProdDetailId()==detail.getProdDetailId()){
                    quantity = cartRequest.getQuantity();
                    if (quantity>detail.getStockQuantity()){
                        isError = true;
                        error+="Not enough "+detail.getProduct().getProductName()+ " "+detail.getColor()+".\n";
                    }
                    total = quantity*(orderDetail.getPrice()-
                            (orderDetail.getPrice()*orderDetail.getDiscount()/100));
                    orderDetail.setQuantity(quantity);
                    detail.setStockQuantity(detail.getStockQuantity()-quantity);
                    orderDetail.setTotal(total);
                    totalOrder+=total;
                }
            }
            orderDetails.add(orderDetail);
        }
        if (isError){
            return ResponseEntity.badRequest().body(error);
        }
        totalOrder*=100;
        Oder oder = new Oder(user,deliveryAddress.getAddress(),deliveryAddress.getPhone(),deliveryAddress.getFullName()
        ,deliveryAddress.getAddressType(),paymentMethod,orderDetails,now,
                null,null,false,shippingStatusService.findById(1l));
        try {
            if (paymentMethod.getId().equalsIgnoreCase("CRE")){
                Map<String, Object> params = new HashMap<>();
                oder.setPaid(true);
                oder.setPaymentDate(now);
                String stripeToken = request.getParameter("token");
                Stripe.apiKey = "sk_test_m1mpTRHzFRbe5qhpBC581cgb00wELJ7ap2";
                params.put("amount",totalOrder.intValue() );
                params.put("currency", "usd");
                params.put("description", "Example charge");
                params.put("receipt_email", user.getEmail());
                params.put("source", stripeToken);
                Charge charge = Charge.create(params);
                oder.setChargeId(charge.getId());
            }
            for (OrderDetail orderDetail:oder.getOrderDetails()){
                orderDetail.setOrder(oder);
            }
            orderService.save(oder);
            return ResponseEntity.ok("Charged!");
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

}
