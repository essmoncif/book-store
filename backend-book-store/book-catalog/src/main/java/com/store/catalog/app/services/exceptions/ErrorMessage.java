package com.store.catalog.app.services.exceptions;

public enum ErrorMessage {

    BOOK_NOT_FOUND_SEARCH_BY_TITLE("Couldn't found book %title"),
    BOOK_NOT_FOUND_SEARCH_BY_ID("Couldn't found book with this id %id");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object ...args) {
        return String.format(message, args);
    }
}
