package com.store.catalog.app.domain.repositories;

import com.store.catalog.app.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    @Query(
            value = "SELECT id, description, first_name, last_name FROM public.author author " +
                    "INNER JOIN public.book_author b_author ON b_author.author_id = author.id" +
                    "WHERE b_author.book_id = :bookId",
            nativeQuery = true
    )
    List<AuthorEntity> findAuthorsOfBook(@Param("bookId") UUID bookId);
}
