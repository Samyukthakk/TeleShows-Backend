package com.tv.show.teleservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.tv.show.teleservice.dto.GenreDTO;
import com.tv.show.teleservice.persistence.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream().map(GenreDTO::new).collect(Collectors.toList());
    }
}
