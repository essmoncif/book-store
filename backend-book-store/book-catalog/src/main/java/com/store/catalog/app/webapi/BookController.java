package com.store.catalog.app.webapi;

import com.store.catalog.app.domain.entities.BookEntity;
import com.store.catalog.app.domain.models.BookDetailsDTO;
import com.store.catalog.app.domain.models.CreateBookDTO;
import com.store.catalog.app.domain.models.UpdateBookDTO;
import com.store.catalog.app.services.handlers.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDetailsDTO>> getAllBooks() {
        List<BookEntity> books = this.bookService.getAllBooks();
        List<BookDetailsDTO> booksDetails = books
                .stream()
                .map(BookDetailsDTO::fromEntityToDTO)
                .toList();
        return ResponseEntity.ok(booksDetails);
    }

    @PostMapping
    public ResponseEntity<BookDetailsDTO> createBook(@RequestBody CreateBookDTO createBookDTO) {
        BookEntity book = this.bookService.createBook(createBookDTO);
        BookDetailsDTO bookDetail = BookDetailsDTO.fromEntityToDTO(book);
        return ResponseEntity.ok(bookDetail);
    }

    @PutMapping
    public ResponseEntity<BookDetailsDTO> updateBook(@RequestParam String idBook, @RequestBody UpdateBookDTO updateBookDTO) {
        BookEntity book = this.bookService.updateBook(updateBookDTO, idBook);
        BookDetailsDTO bookDetails = BookDetailsDTO.fromEntityToDTO(book);
        return ResponseEntity.ok(bookDetails);
    }

    @GetMapping("/{idBook}")
    public ResponseEntity<BookDetailsDTO> getBookById(@PathVariable("idBook") String idBook) {
        BookEntity book = this.bookService.getBookById(idBook);
        BookDetailsDTO bookDetail = BookDetailsDTO.fromEntityToDTO(book);
        return ResponseEntity.ok(bookDetail);
    }
}
