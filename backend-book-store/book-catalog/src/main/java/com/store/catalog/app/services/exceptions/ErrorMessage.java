package com.store.catalog.app.services.exceptions;

public enum ErrorMessage {

    GENRE_NOT_FOUND_BY_NAME("Couldn't found genre with name %1$s !"),
    GENRE_NOT_FOUND_BY_ID("Couldn't found genre with id %1$s !"),
    BOOK_NOT_FOUND_SEARCH_BY_TITLE("Couldn't found book %1$s !"),
    GENRE_ALREADY_EXIST("Genre %1$s is already exist !"),
    AUTHOR_NOT_FOUND_ID("Couldn't found author with this id %1$s !"),
    BOOK_NOT_FOUND_SEARCH_BY_ID("Couldn't found book with this id %1$s !");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object ...args) {
        return String.format(message, args);
    }
}
