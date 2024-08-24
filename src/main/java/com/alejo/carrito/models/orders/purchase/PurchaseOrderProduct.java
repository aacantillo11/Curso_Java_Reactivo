package com.alejo.carrito.models.orders.purchase;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("ordenes_compras_productos")
public class PurchaseOrderProduct {

    @Id
    private Long id;
    private Long ordenCompraId;
    private Long productoId;
    private Integer quantity;
    private Double price;

}
