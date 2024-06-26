package com.theophilusgordon.guestlogixserver.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String resource, String id) {
        super(resource + " with id " + id + " not found");
    }
}
