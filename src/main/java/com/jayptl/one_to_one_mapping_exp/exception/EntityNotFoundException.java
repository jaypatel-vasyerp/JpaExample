package com.jayptl.one_to_one_mapping_exp.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException() {
        super("Entity Not Found");
    }
}
