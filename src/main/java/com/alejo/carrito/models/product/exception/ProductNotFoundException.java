package com.alejo.carrito.models.product.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{

    private String message;

    public ProductNotFoundException(){
    }

    public ProductNotFoundException(String message){
        super(message);
        this.message = message;
    }


}
