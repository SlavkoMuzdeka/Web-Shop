package com.example.projektni_ip.services;

import com.example.projektni_ip.entities.dto.*;
import com.example.projektni_ip.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ProductService {

    Page<Product> findAll(Pageable page);

    Page<Product> getAll(List<Integer> ids, Pageable pageable);

    Page<Product> filterByAttributes(List<AttributeUserInput> attributeUserInputs,  List<Integer> ids, Pageable pageable);

    Page<Product> getAllByTitle(String productName, Pageable pageable);

    SingleProduct findById(Integer id) throws NotFoundException;

    Product findOne(Integer id) throws NotFoundException;

    Map<String, Object> buyProduct(Integer productId, User user);

    List<Product> findPurchasedProducts(Integer userId);

    List<Product> findActiveProducts(Integer userId);

    List<Product> findFinishedProducts(Integer userId);

    Map<String, Object> deleteProduct(Integer productId);

    Map<String, Object> finishProduct(Integer productId);

    Map<String, Object> insert(ProductToCreate product);
}
