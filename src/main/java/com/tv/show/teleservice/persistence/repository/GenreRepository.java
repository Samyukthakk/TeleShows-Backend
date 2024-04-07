package com.tv.show.teleservice.persistence.repository;

import com.tv.show.teleservice.persistence.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
