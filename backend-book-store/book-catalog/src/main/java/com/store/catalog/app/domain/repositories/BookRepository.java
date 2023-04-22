package com.store.catalog.app.domain.repositories;

import com.store.catalog.app.domain.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    public Optional<BookEntity> findByTitle(String title);

}
