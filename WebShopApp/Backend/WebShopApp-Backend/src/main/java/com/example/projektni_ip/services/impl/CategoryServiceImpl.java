package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.services.AttributeService;
import com.example.projektni_ip.services.CategoryService;
import com.example.projektni_ip.entities.CategoryEntity;
import com.example.projektni_ip.entities.dto.SingleAttribute;
import com.example.projektni_ip.entities.dto.SingleCategory;
import com.example.projektni_ip.exceptions.NotFoundException;
import com.example.projektni_ip.repositories.CategoryEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryEntityRepository repository;

    private final AttributeService attributeService;

    public CategoryServiceImpl(CategoryEntityRepository repository, AttributeService attributeService) {
        this.repository = repository;
        this.attributeService = attributeService;
    }

    @Override
    public List<SingleCategory> findAll() {
        List<CategoryEntity> topLevelCategories = repository.findRootCategories();
        List<SingleCategory> result = new ArrayList<>();
        for (CategoryEntity categoryEntity : topLevelCategories) {
            result.add(convertToDto(categoryEntity));
        }
        return result;
    }

    private SingleCategory convertToDto(CategoryEntity categoryEntity) {
        SingleCategory dto = new SingleCategory();
        dto.setId(categoryEntity.getId());
        dto.setName(categoryEntity.getName());
        if (categoryEntity.getCategories() != null) {
            dto.setCategories(new ArrayList<>());
            for (CategoryEntity subCategoryEntity : categoryEntity.getCategories()) {
                dto.getCategories().add(convertToDto(subCategoryEntity));
            }
        }
        return dto;
    }

    @Override
    public List<Integer> getAllChildrenIds(Integer id) {
        CategoryEntity categoryEntity = repository.findById(id).orElseThrow(NotFoundException::new);
        List<Integer> childrenIds = new ArrayList<>();
        childrenIds.add(id);
        getAllChildrenIdsRecursive(categoryEntity, childrenIds);
        return childrenIds;
    }

    @Override
    public List<SingleAttribute> findAllAttributes(Integer id) {
        List<Integer> ids = getAllParentIds(id);
        List<SingleAttribute> attributes = new ArrayList<>();
        List<SingleAttribute> allAttributes = attributeService.findAll();
        for(SingleAttribute attribute: allAttributes){
            for(Integer attributeId: ids){
                if(Objects.equals(attribute.getCategory().getId(), attributeId)){
                    attributes.add(attribute);
                }
            }
        }
        return attributes;
    }


    private void getAllChildrenIdsRecursive(CategoryEntity categoryEntity, List<Integer> childrenIds) {
        for (CategoryEntity child : categoryEntity.getCategories()) {
            childrenIds.add(child.getId());
            getAllChildrenIdsRecursive(child, childrenIds);
        }
    }

    public List<Integer> getAllParentIds(Integer id) {
        List<Integer> parentIds = new ArrayList<>();
        CategoryEntity categoryEntity = repository.findById(id).orElseThrow(NotFoundException::new);
        getAllParentIdsRecursive(categoryEntity, parentIds);
        parentIds.add(id);
        return parentIds;
    }

    private void getAllParentIdsRecursive(CategoryEntity categoryEntity, List<Integer> parentIds) {
        CategoryEntity parent = categoryEntity.getCategory();
        if (parent != null) {
            parentIds.add(parent.getId());
            getAllParentIdsRecursive(parent, parentIds);
        }
    }


}
