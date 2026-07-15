package com.codingshuttle.razorpay.common.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName;
    private final UUID identifier;

    public ResourceNotFoundException(String resourceName, UUID identifier) {
        super(resourceName +"NOT_FOUND");
        this.resourceName = resourceName;
        this.identifier = identifier;
    }
}
