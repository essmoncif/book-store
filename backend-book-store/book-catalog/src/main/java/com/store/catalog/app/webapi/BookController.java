package com.store.catalog.app.webapi;

import com.store.catalog.app.domain.models.BookDetailsRecord;
import com.store.catalog.app.services.exceptions.BadRequestException;
import com.store.catalog.app.services.handlers.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.store.catalog.app.services.exceptions.ErrorMessage.INCORRECT_REQUEST_PARAM;

@Controller
@RequestMapping("api/v1/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDetailsRecord>> getAll() {
        return ResponseEntity.ok(this.bookService.getAllBooks());
    }

    @GetMapping("search")
    public ResponseEntity<BookDetailsRecord> getBookById(@RequestParam(required = false) String id, @RequestParam(required = false) String title) {
        if (!(id == null || id.isEmpty() || id.isBlank())) {
            return ResponseEntity.ok(this.bookService.getBookById(id));
        }
        if (!(title == null || title.isEmpty() || title.isBlank())) {
            return ResponseEntity.ok(this.bookService.getBookByTitle(title));
        }
        throw new BadRequestException(INCORRECT_REQUEST_PARAM.getMessage());
    }

}
