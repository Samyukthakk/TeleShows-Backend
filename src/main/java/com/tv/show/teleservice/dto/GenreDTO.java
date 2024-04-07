package com.tv.show.teleservice.dto;

import com.tv.show.teleservice.persistence.entity.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreDTO {

    private Long id;

    private String name;

    private String description;

    public GenreDTO(Genre genre) {
        this.description = genre.getDescription();
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
