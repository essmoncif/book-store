package com.store.catalog.app.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorDTO {

    private String firstName;

    private String lastName;

    private String description;


}
