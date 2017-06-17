package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.services.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by BiG on 6/17/2017 AD.
 */
public class AuthorControllerTest {
    @InjectMocks
    private AuthorController controller;
    @Mock
    private AuthorService authorService;
    private MockMvc mockMvc;
    private Author author1;
    private Author author2;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        author1 = new Author();
        author1.setId(1L);
        author1.setFirstName("firstName1");
        author1.setLastName("lastName1");
        author2 = new Author();
        author2.setId(2L);
        author2.setFirstName("firstName2");
        author2.setLastName("lastName2");
    }

    @Test
    public void shouldReturnAuthorWhenGetAllExistingAuthor() throws Exception {
        when(authorService.getAllAuthor()).thenReturn(Arrays.asList(author1, author2));

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].first_name", is("firstName1")))
                .andExpect(jsonPath("$[0].last_name", is("lastName1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].first_name", is("firstName2")))
                .andExpect(jsonPath("$[1].last_name", is("lastName2")))
                .andExpect(status().isOk());

        verify(authorService).getAllAuthor();
    }

    @Test
    public void shouldReturnAuthorWhenCreateAuthorSuccessfully() throws Exception {
        when(authorService.createAuthor(Matchers.any(Author.class))).thenReturn(author1);

        mockMvc.perform(post("/api/v1/authors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(author1)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.first_name", is("firstName1")))
                .andExpect(jsonPath("$.last_name", is("lastName1")))
                .andExpect(status().isCreated());

        verify(authorService).createAuthor(Matchers.any(Author.class));
    }
}
