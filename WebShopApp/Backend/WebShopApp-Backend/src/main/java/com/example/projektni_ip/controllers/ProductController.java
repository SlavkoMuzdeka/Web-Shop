package com.example.projektni_ip.controllers;

import com.example.projektni_ip.entities.dto.*;
import com.example.projektni_ip.services.CategoryService;
import com.example.projektni_ip.services.CommentService;
import com.example.projektni_ip.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CommentService commentService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CommentService commentService, CategoryService categoryService) {
        this.productService = productService;
        this.commentService = commentService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Product> findAll(Pageable page) {
        return productService.findAll(page);
    }

    @GetMapping("/{id}")
    public SingleProduct findById(@PathVariable Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/filter/{categoryId}")
    public Page<Product> filterProducts(@PathVariable Integer categoryId, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Integer> ids = categoryService.getAllChildrenIds(categoryId);
        return productService.getAll(ids, pageable);
    }

    @GetMapping("/{productName}/filteredByName")
    public Page<Product> filterByTitle(@PathVariable String productName, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllByTitle(productName, pageable);
    }

    @PostMapping("/filterByAttributes")
    public Page<Product> filterByAttributes(@RequestBody List<AttributeUserInput> attributeUserInputs, @RequestParam("page") int page, @RequestParam("size") int size){
        List<Integer> ids = categoryService.getAllChildrenIds(attributeUserInputs.get(0).getCategory().getId());
        Pageable pageable = PageRequest.of(page, size);
        return productService.filterByAttributes(attributeUserInputs, ids, pageable);
    }

    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.insert(id, comment);
    }

    @PostMapping("/{productId}/buy")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> buyProduct(@PathVariable Integer productId, @RequestBody User user) {
        return productService.buyProduct(productId, user);
    }

    @GetMapping("/purchasedProducts/{userId}")
    public List<Product> findPurchasedProducts(@PathVariable Integer userId) {
        return productService.findPurchasedProducts(userId);
    }

    @GetMapping("/activeProducts/{userId}")
    public List<Product> findActiveProducts(@PathVariable Integer userId) {
        return productService.findActiveProducts(userId);
    }

    @GetMapping("/finishedProducts/{userId}")
    public List<Product> findFinishedProducts(@PathVariable Integer userId) {
        return productService.findFinishedProducts(userId);
    }

    @DeleteMapping("/{productId}")
    public Map<String, Object> deleteProduct(@PathVariable Integer productId) {
        return productService.deleteProduct(productId);
    }

    @PutMapping("/{productId}")
    public Map<String, Object> finishProduct(@PathVariable Integer productId) {
        return productService.finishProduct(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> insert(@RequestBody ProductToCreate product) {
        return productService.insert(product);
    }
}
