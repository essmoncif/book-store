package com.store.catalog.app.services.exceptions;

public enum ErrorMessage {

    INCORRECT_REQUEST_PARAM("Should format one of this id or title !"),
    BOOK_NOT_FOUND_SEARCH_BY_TITLE("Couldn't found book %1$s !"),
    BOOK_NOT_FOUND_SEARCH_BY_ID("Couldn't found book with this id %1$s !");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object ...args) {
        return String.format(message, args);
    }
}
