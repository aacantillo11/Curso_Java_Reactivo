package com.alejo.carrito.models.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("productos")
public class Product {

    @Id
    private Long id;
    private String name;
    private Double price;
    private String description;
    @Column(value = "imageurl")
    private String imageUrl;
    private Integer stock;

}
