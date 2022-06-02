package com.hb.blogapi;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TagControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetTags() throws Exception {

		mockMvc.perform(get("/api/tag")).andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("tag1")))
				.andExpect(jsonPath("$[1].name", is("tag2")));

	}

}
