package com.tv.show.teleservice.dto;

import com.tv.show.teleservice.persistence.entity.Show;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowDTO {

    private Long id;

    @NotNull(message = "Name of the TV show is a mandatory field")
    @Size(min = 3, max = 25, message = "Length of the name should be 3 to 25 characters")
    private String name;

    @NotNull(message = "Description of the TV show is a mandatory field")
    private String description;

    @NotNull(message = "Duration of the TV show is a mandatory field")
    private Integer duration;

    private Float rating;

    @NotNull(message = "Genre of the TV show is a mandatory field")
    private GenreDTO genre;

    public ShowDTO(Show show) {
        this.setId(show.getId());
        this.setDescription(show.getDescription());
        this.setDuration(show.getDuration());
        this.setRating(show.getRating());
        this.setName(show.getName());
        this.setGenre(new GenreDTO(show.getGenre()));
    }
}
