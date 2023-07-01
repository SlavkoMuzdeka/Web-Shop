package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.services.ProductService;
import com.example.projektni_ip.entities.*;
import com.example.projektni_ip.entities.dto.*;
import com.example.projektni_ip.exceptions.NotFoundException;
import com.example.projektni_ip.repositories.ProductEntityRepository;
import com.example.projektni_ip.services.ImageService;
import com.example.projektni_ip.services.ProductAttributeService;
import com.example.projektni_ip.services.StatisticService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductEntityRepository repository;
    private final ImageService imageService;
    private final ModelMapper modelMapper;

    private final StatisticService statisticService;

    private final ProductAttributeService productAttributeService;

    @PersistenceContext
    private EntityManager entityManager;

    public ProductServiceImpl(ProductEntityRepository repository, ImageService imageService, ModelMapper modelMapper, StatisticService statisticService, ProductAttributeService productAttributeService) {
        this.repository = repository;
        this.imageService = imageService;
        this.modelMapper = modelMapper;
        this.statisticService = statisticService;
        this.productAttributeService = productAttributeService;
    }

    @Override
    public Page<Product> findAll(Pageable page) {
        Page<ProductEntity> productEntity = repository.findAllByIsFinishedIsFalseAndIsActiveIsTrue(page);
        return productEntity.map(this::convertToProduct);
    }

    @Override
    public Page<Product> getAll(List<Integer> ids, Pageable pageable) {
        List<ProductEntity> productEntities = repository.findAll();
        List<Product> products = findProducts(ids, productEntities);
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        long total = products.size();
        return new PageImpl<>(products.subList(offset, Math.min(offset + pageable.getPageSize(), products.size())), pageable, total);
    }

    @Override
    public Page<Product> filterByAttributes(List<AttributeUserInput> attributeUserInputs, List<Integer> ids, Pageable pageable) {
        List<ProductEntity> productEntities = repository.findAll();
        List<ProductEntity> resultEntity = new ArrayList<>();

        for (AttributeUserInput atrUserInput : attributeUserInputs) {
            if (atrUserInput.getUserInput().equals("true")) {
                atrUserInput.setUserInput("1");
            } else if (atrUserInput.getUserInput().equals("false")) {
                atrUserInput.setUserInput("0");
            }
            for (ProductEntity p : productEntities) {
                List<ProductAttributeEntity> attributes = p.getProductAttributes();
                for (ProductAttributeEntity productAttr : attributes) {
                    if (productAttr.getAttribute().equals(atrUserInput.getUserInput()) && !p.getIsFinished() && p.getIsActive()) {
                        resultEntity.add(p);
                    }
                }
            }
        }
        List<Product> result = resultEntity.stream()
                .map(this::convertToProduct)
                .distinct()
                .toList();
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        long total = result.size();
        return new PageImpl<>(result.subList(offset, Math.min(offset + pageable.getPageSize(), result.size())), pageable, total);
    }

    private List<Product> findProducts(List<Integer> ids, List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            for (Integer categoryId : ids) {
                if (Objects.equals(product.getCategory().getId(), categoryId) && !product.getIsFinished() && product.getIsActive()) {
                    products.add(convertToProduct(product));
                }
            }
        }
        return products;
    }


    @Override
    public Page<Product> getAllByTitle(String productName, Pageable pageable) {
        List<ProductEntity> productEntities = repository.findByProductTitleContainingIgnoreCase(productName);
        List<Product> filteredProducts;
        filteredProducts = productEntities.stream()
                .filter(ProductEntity::getIsActive)
                .filter(p -> !p.getIsFinished())
                .map(this::convertToProduct)
                .toList();

        int offset = pageable.getPageNumber() * pageable.getPageSize();
        long total = filteredProducts.size();
        return new PageImpl<>(filteredProducts.subList(offset, Math.min(offset + pageable.getPageSize(), filteredProducts.size())), pageable, total);
    }

    public Product convertToProduct(ProductEntity productEntity) {
        return getProduct(productEntity);
    }

    @Override
    public SingleProduct findById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findByIdAndIsFinishedIsFalseAndIsActiveTrue(id), SingleProduct.class);
    }

    @Override
    public Product findOne(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new), Product.class);
    }

    @Override
    public Map<String, Object> buyProduct(Integer productId, User user) {
        ProductEntity productEntity = repository.findById(productId).orElseThrow(NotFoundException::new);
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);

        productEntity.setBuyer(userEntity);
        productEntity.setIsSold(true);
        productEntity = repository.saveAndFlush(productEntity);
        entityManager.refresh(productEntity);

        statisticService.insertLog(logProductIsPurchased(productEntity));

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("status_code", 200);
        return response;
    }

    private StatisticEntity logProductIsPurchased(ProductEntity productEntity) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setDescription("Korisnik " + productEntity.getBuyer().getFirstName() + " " + productEntity.getBuyer().getLastName() + " je kupio proizvod " + productEntity.getTitle() + ".");
        statisticEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        return statisticEntity;
    }

    @Override
    public List<Product> findPurchasedProducts(Integer userId) {
        return repository.findAllByBuyer_Id(userId)
                .stream()
                .map(this::getProduct)
                .toList();

    }

    @Override
    public List<Product> findActiveProducts(Integer userId) {
        return repository.findAllBySeller_IdAndIsActiveIsTrueAndIsFinishedIsFalse(userId)
                .stream()
                .map(this::getProduct)
                .toList();
    }

    @Override
    public List<Product> findFinishedProducts(Integer userId) {
        return repository.findAllBySeller_IdAndIsActiveIsTrueAndIsFinishedIsTrue(userId)
                .stream()
                .map(this::getProduct)
                .toList();
    }

    @Override
    public Map<String, Object> deleteProduct(Integer productId) {
        ProductEntity productEntity = repository.findById(productId).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (productEntity == null) {
            response.put("status", false);
            response.put("status_code", 404);
            return response;
        } else {
            productEntity.setIsActive(false);
            productEntity = repository.saveAndFlush(productEntity);
            entityManager.refresh(productEntity);
            response.put("status", true);
            response.put("status_code", 200);
            statisticService.insertLog(logProductIsDeleted(productEntity));
        }
        return response;
    }

    private StatisticEntity logProductIsDeleted(ProductEntity productEntity){
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setDescription("Korisnik " + productEntity.getSeller().getFirstName() + " " +  productEntity.getSeller().getLastName() + " je obrisao proizvod " + productEntity.getTitle() + ".");
        statisticEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        return statisticEntity;
    }

    @Override
    public Map<String, Object> finishProduct(Integer productId) {
        ProductEntity productEntity = repository.findById(productId).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (productEntity == null) {
            response.put("status", false);
            response.put("status_code", 404);
            return response;
        } else {
            productEntity.setIsFinished(true);
            productEntity = repository.saveAndFlush(productEntity);
            entityManager.refresh(productEntity);
            response.put("status", true);
            response.put("status_code", 200);
        }
        return response;
    }

    private Product getProduct(ProductEntity productEntity) {
        Product product = modelMapper.map(productEntity, Product.class);
        Image image = new Image();
        if (productEntity.getImages() != null && !productEntity.getImages().isEmpty()) {
            ImageEntity imageEntity = productEntity.getImages().get(0);
            image.setId(imageEntity.getId());
            image.setImg(imageEntity.getImg());
        }
        product.setImage(image);
        return product;
    }

    @Override
    public Map<String, Object> insert(ProductToCreate product) {
        Map<String, Object> result = new HashMap<>();
        ProductEntity productEntity = insertProductEntity(product);
        insertImages(product, productEntity);
        insertAttributes(product, productEntity);

        result.put("status", true);
        result.put("status_code", 201);
        statisticService.insertLog(logProductIsCreated(productEntity));
        return result;
    }

    private StatisticEntity logProductIsCreated(ProductEntity productEntity) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setDescription("Korisnik " + productEntity.getSeller().getFirstName() + " " + productEntity.getSeller().getLastName() + " je kreirao proizvod " + productEntity.getTitle() + ".");
        statisticEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        return statisticEntity;
    }

    private ProductEntity insertProductEntity(ProductToCreate product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(product.getTitle());
        productEntity.setDescription(product.getDescription());
        productEntity.setPrice(product.getPrice());
        productEntity.setContact(product.getContact());
        productEntity.setType(product.getType());
        productEntity.setLocation(product.getLocation());
        java.util.Date date = new java.util.Date();
        productEntity.setCreationDate(new Date(date.getTime()));
        productEntity.setIsActive(true);
        productEntity.setIsSold(false);
        productEntity.setIsFinished(false);
        productEntity.setSeller(modelMapper.map(product.getSeller(), UserEntity.class));
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(product.getCategory());
        productEntity.setCategory(categoryEntity);

        productEntity = repository.saveAndFlush(productEntity);
        entityManager.refresh(productEntity);
        return productEntity;
    }

    private void insertImages(ProductToCreate product, ProductEntity productEntity) {
        List<ImageEntity> images = product.getImages().stream().map(i -> modelMapper.map(i, ImageEntity.class)).toList();
        for (ImageEntity image : images) {
            image.setProduct(productEntity);
            imageService.insert(image);
        }
    }

    private void insertAttributes(ProductToCreate product, ProductEntity productEntity) {
        List<AttributeUserInput> attributeUserInputs = product.getAttributes();
        for (AttributeUserInput attribute : attributeUserInputs) {
            ProductAttributeEntity productAttributeEntity = new ProductAttributeEntity();
            AttributeEntity attributeEntity = new AttributeEntity();
            attributeEntity.setId(attribute.getId());

            productAttributeEntity.setProduct(productEntity);
            productAttributeEntity.setAttributeEnt(attributeEntity);
            productAttributeEntity.setAttribute(attribute.getUserInput());

            productAttributeService.insert(productAttributeEntity);
        }
    }
}
