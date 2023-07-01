package com.example.projektni_ip.repositories;

import com.example.projektni_ip.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {

    Page<ProductEntity> findAllByIsFinishedIsFalseAndIsActiveIsTrue(Pageable pageable);

    ProductEntity findByIdAndIsFinishedIsFalseAndIsActiveTrue(Integer id);

    List<ProductEntity> findAllByBuyer_Id(Integer userId);

    List<ProductEntity> findAllBySeller_IdAndIsActiveIsTrueAndIsFinishedIsFalse(Integer userId);

    List<ProductEntity> findAllBySeller_IdAndIsActiveIsTrueAndIsFinishedIsTrue(Integer userId);

    @Query("SELECT p FROM ProductEntity p WHERE lower(p.title) LIKE lower(concat('%', :productName, '%'))")
    List<ProductEntity> findByProductTitleContainingIgnoreCase(String productName);

}
