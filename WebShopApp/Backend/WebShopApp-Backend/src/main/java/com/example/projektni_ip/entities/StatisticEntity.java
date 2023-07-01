package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "statistics")
public class StatisticEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "date_time")
    private Timestamp dateTime;
}
