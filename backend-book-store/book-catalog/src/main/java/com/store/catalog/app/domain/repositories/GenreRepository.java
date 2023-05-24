package com.store.catalog.app.domain.repositories;

import com.store.catalog.app.domain.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, UUID> {

    @Query(
            value = "SELECT id, genre_name FROM public.genre WHERE genre_name = :genreName",
            nativeQuery = true
    )
    Optional<GenreEntity> findByGenreName(@Param("genreName") String genreName);

    boolean existsByGenreName(String genreName);


}
