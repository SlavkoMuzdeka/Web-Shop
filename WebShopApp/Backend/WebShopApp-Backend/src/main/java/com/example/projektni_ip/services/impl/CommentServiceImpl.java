package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.entities.CommentEntity;
import com.example.projektni_ip.entities.StatisticEntity;
import com.example.projektni_ip.entities.dto.Comment;
import com.example.projektni_ip.entities.dto.Product;
import com.example.projektni_ip.repositories.CommentEntityRepository;
import com.example.projektni_ip.services.CommentService;
import com.example.projektni_ip.services.ProductService;
import com.example.projektni_ip.services.StatisticService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentEntityRepository repository;

    private final ProductService productService;

    private final ModelMapper modelMapper;

    private final StatisticService statisticService;

    @PersistenceContext
    private EntityManager entityManager;

    public CommentServiceImpl(CommentEntityRepository repository, ProductService productService, ModelMapper modelMapper, StatisticService statisticService) {
        this.repository = repository;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.statisticService = statisticService;
    }

    @Override
    public Comment insert(Integer productId, Comment comment) {
        Product product = productService.findOne(productId);
        comment.setProduct(product);
        comment.setId(null);
        comment.setCreationDate(formatDate(comment.getCreationDate()));
        CommentEntity commentEntity = modelMapper.map(comment, CommentEntity.class);
        repository.saveAndFlush(commentEntity);
        entityManager.refresh(commentEntity);
        comment = modelMapper.map(commentEntity, Comment.class);
        statisticService.insertLog(createLog(comment, product));
        return comment;
    }

    private StatisticEntity createLog(Comment comment, Product product) {
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setDescription("Korisnik " + comment.getUser().getFirstName() + " " + comment.getUser().getLastName() + " je komentarisao proizvod " + product.getTitle() + ".Komentar glasi: " + comment.getText());
        statisticEntity.setDateTime(new Timestamp(System.currentTimeMillis()));
        return statisticEntity;
    }

    private String formatDate(String dateToFormat) {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateToFormat);
        LocalDateTime localDateTime = offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDateTime.format(formatter);
    }
}
