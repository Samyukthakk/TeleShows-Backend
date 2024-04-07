package com.tv.show.teleservice.persistence.repository;

import java.util.List;

import com.tv.show.teleservice.persistence.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findAllByName(String name);
}
