package com.example.demo.web;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.example.demo.model.Category;
import com.example.demo.model.ProductDetail;
import com.example.demo.model.User;
import com.example.demo.payload.ChangeCartItemQtyRequest;
import com.example.demo.payload.SeenProductResponse;
import com.example.demo.payload.TopViewResponse;
import com.example.demo.service.*;
import com.example.demo.utils.ProductUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.ApiResource;
import com.stripe.net.StripeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Product;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductUtils productUtils;

    private Pageable getPageable(String order, String direction, Integer page, Integer rows) {
        Pageable pageable = null;
        Sort sort = null;
        if (order != null && direction != null) {
            if (direction.equalsIgnoreCase("desc")) {
                sort = new Sort(Direction.DESC, order);
                pageable = PageRequest.of(page - 1, rows, sort);
            } else if (direction.equalsIgnoreCase("asc")) {
                sort = new Sort(Direction.ASC, order);
                pageable = PageRequest.of(page - 1, rows, sort);
            } else {
                pageable = PageRequest.of(page - 1, rows);
            }
        } else {
            pageable = PageRequest.of(page - 1, rows);
        }
        return pageable;
    }

    @GetMapping(value = "/find/by-id/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/find/by-img-id/{id}")
    public ResponseEntity<Product> findByImgId(@PathVariable Long id) {
        Product product = productService.findByImgId(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/find/all-paging")
    public ResponseEntity<List<Product>> findAllWithPaging(@RequestParam("page") Integer page,
                                                           @RequestParam("rows") Integer rows,
                                                           @Nullable @RequestParam("order") String order,
                                                           @Nullable @RequestParam("direction") String direction) {

        Pageable pageable = getPageable(order, direction, page, rows);
        List<Product> products = productService.findAllWithPaging(pageable).getContent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/count/all")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(productService.count(), HttpStatus.OK);
    }

    @GetMapping("/find/by-name")
    public ResponseEntity<?> findByName(@NotNull @RequestParam("name") String name) {
        List<Product> products = null;
        if (!name.equals("") && !(name.length() == 1)) {
            products = productService.findByName(name);
        }
        if (products == null || products.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No_content");
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/count/by-brand")
    public ResponseEntity<Long> countByBrand(@RequestParam("brandId") String id) {
        return new ResponseEntity<>(productService.countByBrand(id), HttpStatus.OK);
    }

    @GetMapping("/count/by-category")
    public ResponseEntity<Long> countByCategory(@RequestParam("categoryId") String id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(productService.countByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/find/by-brand")
    public ResponseEntity<?> findByBrand(@RequestParam("brandId") String id,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("rows") Integer rows,
                                         @Nullable @RequestParam("order") String order,
                                         @Nullable @RequestParam("direction") String direction) {
        Pageable pageable = getPageable(order, direction, page, rows);
        List<Product> products = productService.findByBrandId(id, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find/by-category")
    public ResponseEntity<?> findByCategories(@RequestParam("categoryId") String id,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("rows") Integer rows,
                                              @Nullable @RequestParam("order") String order,
                                              @Nullable @RequestParam("direction") String direction) {
        Pageable pageable = getPageable(order, direction, page, rows);
        Category category = categoryService.findById(id);
        List<Product> products = productService.findByCategories(category, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find/top-view")
    public ResponseEntity<?> findTopView(@RequestParam("page") Integer page,
                                         @RequestParam("rows") Integer rows) {
        Pageable pageable = getPageable("viewCount", "desc", page, rows);
        List<Product> products = productService.findAllWithPaging(pageable).getContent();
        List<TopViewResponse> viewResponses = new ArrayList<>();
        for (Product product : products) {
            viewResponses.add(new TopViewResponse(product.getProductId(), product.getThumbnail(), product.getProductName()));
        }
        return ResponseEntity.ok(viewResponses);
    }

    @PutMapping("/cart/change-qty")
    public ResponseEntity<?> changeCartQty(@RequestBody ChangeCartItemQtyRequest qtyRequest) {
        ProductDetail detail = productDetailService.findById(qtyRequest.getProdDetailId());
        Long qtyToChange = qtyRequest.getNextQty();
        Long stockQty = detail.getStockQuantity();
        if (stockQty >= qtyToChange) {
            return ResponseEntity.ok("Sufficent item");
        } else {
            return ResponseEntity.badRequest().body("Only " + stockQty + " item left");
        }
    }

    @GetMapping("/filter/price")
    public ResponseEntity<?> filterPrice(@Nullable @RequestParam("priceFrom") Double priceFrom,
                                         @Nullable @RequestParam("priceTo") Double priceTo) {
        Set<Product> product = productService.filterPrice(priceFrom, priceTo);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/filter/review")
    public ResponseEntity<?> filterReview(@Nullable @RequestParam("reviewScore") Float reviewScore) {
        Set<Product> product = productService.findDistinctByAvgReviewScoreGreaterThanEqual(reviewScore);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/save/increase-view-count")
    public ResponseEntity<?> increaseViewCount(@RequestParam("productId") String id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        product.setViewCount(product.getViewCount() + 1);
        product = productService.save(product);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't update");
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @GetMapping("/find/find-by-seen-user")
    public ResponseEntity<?> findBySeenUser(HttpServletRequest request,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("rows") Integer rows,
                                            @Nullable @RequestParam("order") String order,
                                            @Nullable @RequestParam("direction") String direction) {
        String token = request.getHeader("Authorization");
        Long id = jwtService.getUserIdFromToken(token);
        if (id == null) {
            return ResponseEntity.badRequest().body("Wrong token");
        }
        Pageable pageable = getPageable(order, direction, page, rows);
        List<Product> products = productService.findSeenProductsByUserId(id, pageable);
        if (products == null) {
            return ResponseEntity.badRequest().body("Wrong userId");
        } else {
            List<SeenProductResponse> products1 = new ArrayList<>();
            for (Product product : products) {
                products1.add(new SeenProductResponse(product.getProductId(),
                        product.getProductName(), product.getThumbnail()));
            }
            return ResponseEntity.ok(products1);
        }
    }

    @PostMapping("/save/add-seen-product")
    public ResponseEntity<?> addSeenProduct(HttpServletRequest request, @RequestParam("productId") String id) {
        String token = request.getHeader("Authorization");
        Long userId = jwtService.getUserIdFromToken(token);
        Product product = productService.findSeenProductsByUserIdAndProductId(userId, id);
        if (product == null) {
            User u = userService.findById(userId);
            u.getSeenProducts().add(productService.findById(id));
            u = userService.save(u);
            if (u != null) {
                return ResponseEntity.ok("Saved");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
            }
        } else {
            return ResponseEntity.badRequest().body("Saved before");
        }
    }

    @GetMapping("/count/count-seen-product")
    public ResponseEntity<?> countSeenProduct(@RequestParam("userId") Long userId) {
        User user = userService.findById(userId);
        return ResponseEntity.ok(productService.countBySeenUsers(user));
    }

}
