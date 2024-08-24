package com.alejo.carrito.models.orders.purchase;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("ordenes_compras")
public class PurchaseOrder {

    @Id
    private Long id;
    private Double total;
    private String status;
    private LocalDateTime createdAt;

}
