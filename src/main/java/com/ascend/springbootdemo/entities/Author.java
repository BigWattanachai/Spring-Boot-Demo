package com.ascend.springbootdemo.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by BiG on 6/17/2017 AD.
 */
@Data
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;
}
