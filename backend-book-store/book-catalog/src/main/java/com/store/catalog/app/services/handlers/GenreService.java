package com.store.catalog.app.services.handlers;

import com.store.catalog.app.domain.entities.GenreEntity;
import com.store.catalog.app.domain.repositories.GenreRepository;
import com.store.catalog.app.services.exceptions.BadRequestException;
import com.store.catalog.app.services.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.store.catalog.app.services.exceptions.ErrorMessage.*;

@AllArgsConstructor
@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public List<GenreEntity> getAllGenres() {
        return this.genreRepository.findAll();
    }

    public GenreEntity getGenreByName(final String genreName) {
        return this.genreRepository.findByGenreName(genreName.toUpperCase())
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_BY_NAME.getMessage(genreName)));
    }

    public GenreEntity getGenreById(final String id) {
        UUID uuid = UUID.fromString(id);
        return this.genreRepository.findById(uuid)
                .orElseThrow(() -> new NotFoundException(GENRE_NOT_FOUND_BY_ID.getMessage(id)));
    }

    public GenreEntity createGenre(final String genreName) {
        GenreEntity genre = GenreEntity.builder()
                .genreName(genreName.toUpperCase())
                .build();
        boolean exist = this.genreRepository
                .existsByGenreName(genreName.toUpperCase());
        if(!exist) {
            return this.genreRepository.saveAndFlush(genre);
        }
        throw new BadRequestException(GENRE_ALREADY_EXIST.getMessage(genreName));
    }

    public GenreEntity createOrGet(final String genreName) {
        try {
            return this.getGenreByName(genreName);
        }catch (NotFoundException e) {
            return this.createGenre(genreName);
        }
    }
}
