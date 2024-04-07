package com.tv.show.teleservice.persistence.entity;

import com.tv.show.teleservice.dto.GenreDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    public Genre(GenreDTO genreDTO) {
        this.description = genreDTO.getDescription();
        this.name = genreDTO.getName();
        this.id = genreDTO.getId();
    }
}
