package com.codingshuttle.razorpay.common.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName;
    private final String identifier;

    public ResourceNotFoundException(String resourceName, String identifier) {
        super(resourceName +"NOT_FOUND");
        this.resourceName = resourceName;
        this.identifier = identifier;
    }
}
