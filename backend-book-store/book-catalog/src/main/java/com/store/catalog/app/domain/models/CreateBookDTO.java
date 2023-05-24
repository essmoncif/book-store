package com.store.catalog.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookDTO {

    private String title;

    private String shortDescription;

    private int edition;

    private List<String> authorsID;

    private List<String> genres;
}
