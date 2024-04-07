package com.tv.show.teleservice.api;

import java.util.List;

import com.tv.show.teleservice.dto.GenreDTO;
import com.tv.show.teleservice.dto.ShowDTO;
import com.tv.show.teleservice.service.GenreService;
import com.tv.show.teleservice.service.ShowService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/teleservice")
@CrossOrigin
public class TeleController {

    @Autowired
    GenreService genreService;

    @Autowired
    ShowService showService;

    @GetMapping("/genre")
    public List<GenreDTO> getShowGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping
    public List<ShowDTO> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{id}")
    public ShowDTO getShowById(@PathVariable("id") Long showId) {
        return showService.getShowById(showId);
    }

    @DeleteMapping
    public Long removeShow(@PathParam("showId") Long showId) {
        return showService.removeShow(showId);
    }

    @PostMapping
    public ShowDTO updateShow(@Valid @RequestBody ShowDTO showDTO) throws Exception {
        return showService.updateShow(showDTO);
    }
}
