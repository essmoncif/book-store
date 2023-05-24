package com.store.catalog.app.services.handlers;

import com.store.catalog.app.domain.entities.BookEntity;
import com.store.catalog.app.domain.models.CreateBookDTO;
import com.store.catalog.app.domain.models.UpdateBookDTO;
import com.store.catalog.app.domain.repositories.BookRepository;
import com.store.catalog.app.services.exceptions.BadRequestException;
import com.store.catalog.app.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.store.catalog.app.services.exceptions.ErrorMessage.BOOK_NOT_FOUND_SEARCH_BY_ID;
import static com.store.catalog.app.services.exceptions.ErrorMessage.BOOK_NOT_FOUND_SEARCH_BY_TITLE;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;

    public List<BookEntity> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public BookEntity getBookById(final String id) {
        try {
            UUID uuid = UUID.fromString(id);
            Optional<BookEntity> optionalBookEntity = this.bookRepository.findById(uuid);
            return optionalBookEntity.orElseThrow(() -> new NotFoundException(BOOK_NOT_FOUND_SEARCH_BY_ID.getMessage(id)));
        }catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public BookEntity getBookByTitle(final String title) {
        Optional<BookEntity> optionalBookEntity = this.bookRepository.findByTitle(title);
        return optionalBookEntity.orElseThrow( () -> new NotFoundException(BOOK_NOT_FOUND_SEARCH_BY_TITLE.getMessage(title)));
    }

    public List<BookEntity> getBooksByAuthorFirstName(final String authorFirstName) {
        return this.bookRepository.findBooksByAuthorFirstName(authorFirstName);
    }

    public List<BookEntity> getBooksByAuthorId(final String authorId) {
        UUID uuid = UUID.fromString(authorId);
        return this.bookRepository.findBooksByAuthorId(uuid);
    }

    public BookEntity createBook(CreateBookDTO book) {
        BookEntity bookEntity = BookEntity
                .builder()
                .title(book.getTitle())
                .shortDescription(book.getShortDescription())
                .edition(book.getEdition())
                .editionDate(LocalDateTime.now())
                .genres(book.getGenres()
                        .stream()
                        .map(this.genreService::createOrGet)
                        .toList())
                .authors(book.getAuthorsID()
                        .stream()
                        .map(this.authorService::getAuthorById)
                        .toList())
                .build();
        return this.bookRepository.saveAndFlush(bookEntity);
    }

    public BookEntity updateBook(UpdateBookDTO updateBookDTO, String bookID) {
        BookEntity book = this.getBookById(bookID);
        book.setAuthors(updateBookDTO.getAuthorsID().stream().map(this.authorService::getAuthorById).toList());
        book.setGenres(updateBookDTO.getGenres().stream().map(this.genreService::createOrGet).toList());
        book.setEdition(updateBookDTO.getEdition());
        book.setShortDescription(updateBookDTO.getShortDescription());
        book.setTitle(updateBookDTO.getTitle());
        return this.bookRepository.saveAndFlush(book);
    }

}
