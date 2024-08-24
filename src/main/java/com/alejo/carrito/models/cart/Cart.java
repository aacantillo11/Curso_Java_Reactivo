package com.alejo.carrito.models.cart;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("carritos")
public class Cart {

    @Id
    private Long id;
    private Integer userId;
    private LocalDateTime createdAt;
}
