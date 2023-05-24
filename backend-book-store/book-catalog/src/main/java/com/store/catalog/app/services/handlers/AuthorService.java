package com.store.catalog.app.services.handlers;

import com.store.catalog.app.domain.entities.AuthorEntity;
import com.store.catalog.app.domain.repositories.AuthorRepository;
import com.store.catalog.app.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.store.catalog.app.services.exceptions.ErrorMessage.AUTHOR_NOT_FOUND_ID;

@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<AuthorEntity> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    public AuthorEntity getAuthorById(String id) {
        UUID uuid = UUID.fromString(id);
        return this.authorRepository
                .findById(uuid)
                .orElseThrow(() -> new NotFoundException(AUTHOR_NOT_FOUND_ID.getMessage(id)));
    }

    public List<AuthorEntity> getAuthorOfBook(String bookId) {
        UUID uuid = UUID.fromString(bookId);
        return this.authorRepository.findAuthorsOfBook(uuid);
    }
}
