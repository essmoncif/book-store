package com.store.catalog.app.domain.repositories;

import com.store.catalog.app.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    Optional<BookEntity> findByTitle(String title);

    @Query(
            value = "SELECT b.* " +
                    "FROM book b " +
                    "JOIN book_author ba ON b.book_id = ba.book_id " +
                    "JOIN author a ON ba.author_id = a.author_id " +
                    "WHERE a.author_first_name = :authorFirstName",
            nativeQuery = true
    )
    List<BookEntity> findBooksByAuthorFirstName(@Param("authorFirstName") String authorFirstName);

    @Query(
            value = "SELECT b.* " +
                    "FROM book b " +
                    "JOIN book_author ba ON b.book_id = ba.book_id " +
                    "WHERE ba.author_id = :authorId",
            nativeQuery = true
    )
    List<BookEntity> findBooksByAuthorId(@Param("authorId") UUID authorId);

}
