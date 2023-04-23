package com.store.catalog.app.services.handlers;

import com.store.catalog.app.domain.entities.BookEntity;
import com.store.catalog.app.domain.models.BookDetailsRecord;
import com.store.catalog.app.domain.repositories.BookRepository;
import com.store.catalog.app.services.exceptions.BadRequestException;
import com.store.catalog.app.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.store.catalog.app.services.exceptions.ErrorMessage.BOOK_NOT_FOUND_SEARCH_BY_ID;
import static com.store.catalog.app.services.exceptions.ErrorMessage.BOOK_NOT_FOUND_SEARCH_BY_TITLE;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDetailsRecord> getAllBooks() {
        return this.bookRepository.findAll()
                .stream()
                .map(BookDetailsRecord::bookEntityToBookDetailsRecord)
                .collect(Collectors.toList());
    }

    public BookDetailsRecord getBookById(final String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<BookEntity> optionalBookEntity = this.bookRepository.findById(uuid);
            BookEntity bookEntity = optionalBookEntity.orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_SEARCH_BY_ID.getMessage(id)));
            return BookDetailsRecord.bookEntityToBookDetailsRecord(bookEntity);
        }catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    public BookDetailsRecord getBookByTitle(final String title) {
        Optional<BookEntity> optionalBookEntity = this.bookRepository.findByTitle(title);
        BookEntity bookEntity = optionalBookEntity.orElseThrow( () -> new NotFoundException(BOOK_NOT_FOUND_SEARCH_BY_TITLE.getMessage(title)));
        return BookDetailsRecord.bookEntityToBookDetailsRecord(bookEntity);
    }



}
