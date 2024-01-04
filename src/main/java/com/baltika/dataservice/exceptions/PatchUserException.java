package com.baltika.dataservice.exceptions;

public class PatchUserException extends IllegalArgumentException {
    public PatchUserException() {
    }

    public PatchUserException(String s) {
        super(s);
    }
}
