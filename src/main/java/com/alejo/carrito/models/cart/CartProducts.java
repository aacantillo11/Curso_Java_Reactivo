package com.alejo.carrito.models.cart;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("carrito_productos")
public class CartProducts {

    @Id
    private Long id;
    private Long carritoId;
    private Long productoId;
    private Integer quantity;
}
