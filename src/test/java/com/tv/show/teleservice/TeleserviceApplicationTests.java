package com.tv.show.teleservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tv.show.teleservice.api.exception.RestErrorResponse;
import com.tv.show.teleservice.dto.GenreDTO;
import com.tv.show.teleservice.dto.ShowDTO;
import com.tv.show.teleservice.persistence.entity.Genre;
import com.tv.show.teleservice.persistence.entity.Show;
import com.tv.show.teleservice.persistence.repository.ShowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class TeleserviceApplicationTests {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	ShowRepository showRepository;

	@Test
	void testGetAllTeleServices_SuccessResponse() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/teleservice")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		List<ShowDTO> listShowDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<ShowDTO>>() {});
		assertEquals(200, mvcResult.getResponse().getStatus(), "Unexpected response code");
		assertNotEquals(0, listShowDTO.size());
	}

	@Test
	void testTeleServiceByID_SuccessResponse() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/teleservice/1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		ShowDTO showDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ShowDTO.class);
		assertEquals(200, mvcResult.getResponse().getStatus(), "Unexpected response code");
		assertEquals(1, showDTO.getId());
	}

	@Test
	void testAllGenres_SuccessResponse() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/api/teleservice/genre")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		List<Genre> genreList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
				new TypeReference<List<Genre>>() {});
		assertEquals(200, mvcResult.getResponse().getStatus(), "Unexpected response code");
		assertEquals(6, genreList.size());
	}

	@Test
	void testDeleteShowById_SuccessResponse() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/api/teleservice?showId=1")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus(), "Unexpected response code");
		assertEquals(0, showRepository.findAllByName("Breaking Bad").size());
	}

	@Test
	void testAddNewShow_SuccessResponse() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/teleservice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getShowDTO())))
				.andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus(), "Unexpected response code");
		assertEquals(7, showRepository.findAll().size());
	}

	@Test
	void testUpdateShow_SuccessResponse() throws Exception {
		ShowDTO showDTO = getShowDTO();
		showDTO.setId(1L);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/teleservice")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(showDTO))).andReturn();
		assertEquals(200, mvcResult.getResponse().getStatus(), "Unexpected response code");
		Optional<Show> show = showRepository.findById(1L);
		assertEquals("Amazon Original", show.get().getDescription());
	}

	@Test
	void testAddNewShow_ValidationFailure_ErrorResponse() throws Exception {
		ShowDTO showDTO = getShowDTO();
		showDTO.setName("A");
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/api/teleservice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(showDTO))).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus(), "Unexpected response code");
		RestErrorResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestErrorResponse.class);
		assertEquals("Length of the name should be 3 to 25 characters", response.getMessages().get(0));
	}

	@Test
	void testDeleteShowById_NotFound_ErrorResponse() throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/api/teleservice?showId=9")
				.contentType(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(400, mvcResult.getResponse().getStatus(), "Unexpected response code");
		RestErrorResponse response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestErrorResponse.class);
		assertEquals("Show not found!", response.getMessages().get(0));
	}

	private ShowDTO getShowDTO() {
		ShowDTO showDTO = new ShowDTO();
		showDTO.setName("Jack Ryan");
		showDTO.setDescription("Amazon Original");
		showDTO.setDuration(13);
		showDTO.setRating(3.5F);
		GenreDTO genreDTO = new GenreDTO();
		genreDTO.setId(1L);
		showDTO.setGenre(genreDTO);
		return showDTO;
	}

}
