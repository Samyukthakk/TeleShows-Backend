package com.tv.show.teleservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tv.show.teleservice.dto.ShowDTO;
import com.tv.show.teleservice.persistence.entity.Genre;
import com.tv.show.teleservice.persistence.entity.Show;
import com.tv.show.teleservice.persistence.repository.GenreRepository;
import com.tv.show.teleservice.persistence.repository.ShowRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    GenreRepository genreRepository;

    public List<ShowDTO> getAllShows() {
        return showRepository.findAll().stream().map(ShowDTO::new).collect(Collectors.toList());
    }

    public ShowDTO getShowById(Long id) {
        Optional<Show> show = showRepository.findById(id);
        if (show.isPresent()) {
            return new ShowDTO(show.get());
        } else {
            throw new EntityNotFoundException("Show not found");
        }
    }

    public Long removeShow(Long showId) {
        Optional<Show> show = showRepository.findById(showId);
        if (show.isPresent()) {
            showRepository.deleteById(showId);
            return showId;
        } else {
            throw new EntityNotFoundException("Show not found!");
        }
    }

    public ShowDTO updateShow(ShowDTO showDTO) throws Exception {
        Genre genre = getGenreById(showDTO.getGenre().getId());
        if (showDTO.getId() == null) {
            return updateShowDetails(showDTO, new Show(), genre);
        } else {
            Optional<Show> show = showRepository.findById(showDTO.getId());
            if (show.isPresent()) {
                return updateShowDetails(showDTO, show.get(), genre);
            } else {
                throw new EntityNotFoundException("Show not found!");
            }
        }
    }

    private Genre getGenreById(Long genreId) {
        return genreRepository.findById(genreId).orElseThrow(
                () -> new EntityNotFoundException("Genre ID: " + genreId +
                        " not found")
        );
    }

    private ShowDTO updateShowDetails(ShowDTO showDTO, Show show, Genre genre) {
        show.setName(showDTO.getName());
        show.setDescription(showDTO.getDescription());
        show.setGenre(genre);
        show.setDuration(showDTO.getDuration());
        show.setRating(showDTO.getRating());
        return new ShowDTO(showRepository.save(show));
    }
}
