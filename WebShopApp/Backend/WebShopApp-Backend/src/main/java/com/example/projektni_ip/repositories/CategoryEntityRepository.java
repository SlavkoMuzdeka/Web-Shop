package com.example.projektni_ip.repositories;

import com.example.projektni_ip.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query("SELECT c FROM CategoryEntity c WHERE c.category IS NULL")
    List<CategoryEntity> findRootCategories();
}
