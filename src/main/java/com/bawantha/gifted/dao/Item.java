package com.bawantha.gifted.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private LocalDate updated_date;
}
