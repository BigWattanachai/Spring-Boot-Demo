package com.ascend.springbootdemo.controllers;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.entities.Post;
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
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    private Post post1;
    private Post post2;

    private ObjectMapper mapper = new ObjectMapper();

    private final String firstName1 = "firstName1";
    private final String firstName2 = "firstName2";
    private final String lastName1 = "lastName1";
    private final String lastName2 = "lastName2";
    private final String content1 = "content1";
    private final String content2 = "content2";

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        author1 = new Author();
        author1.setId(1L);
        author1.setFirstName(firstName1);
        author1.setLastName(lastName1);
        author2 = new Author();
        author2.setId(2L);
        author2.setFirstName(firstName2);
        author2.setLastName(lastName2);

        post1 = new Post();
        post1.setAuthor(author1);
        post1.setContent(content1);
        post1.setId(1L);
        post2 = new Post();
        post2.setAuthor(author1);
        post2.setContent(content2);
        post2.setId(2L);
    }

    @Test
    public void shouldReturnAuthorWhenGetAllExistingAuthor() throws Exception {
        when(authorService.getAllAuthor()).thenReturn(Arrays.asList(author1, author2));

        mockMvc.perform(get("/api/v1/authors"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].first_name", is(firstName1)))
                .andExpect(jsonPath("$[0].last_name", is(lastName1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].first_name", is(firstName2)))
                .andExpect(jsonPath("$[1].last_name", is(lastName2)))
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
                .andExpect(jsonPath("$.first_name", is(firstName1)))
                .andExpect(jsonPath("$.last_name", is(lastName1)))
                .andExpect(status().isCreated());

        verify(authorService).createAuthor(Matchers.any(Author.class));
    }

    @Test
    public void shouldReturnAuthorWhenGetExistingAuthorById() throws Exception {
        when(authorService.getAuthorById(anyLong())).thenReturn(author1);

        mockMvc.perform(get("/api/v1/authors/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.first_name", is(firstName1)))
                .andExpect(jsonPath("$.last_name", is(lastName1)))
                .andExpect(status().isOk());

        verify(authorService).getAuthorById(anyLong());
    }

    @Test
    public void shouldReturnAuthorWhenUpdateExistingAuthor() throws Exception {
        Author authorUpdate = new Author();
        authorUpdate.setFirstName("updated_first_name");
        authorUpdate.setLastName("updated_last_name");
        author1.setFirstName("updated_first_name");
        author1.setLastName("updated_last_name");
        when(authorService.updateAuthor(anyLong(), Matchers.any(Author.class))).thenReturn(author1);
        mockMvc.perform(put("/api/v1/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(authorUpdate)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.first_name", is("updated_first_name")))
                .andExpect(jsonPath("$.last_name", is("updated_last_name")))
                .andExpect(status().isOk());

        verify(authorService).updateAuthor(anyLong(), Matchers.any(Author.class));
    }

    @Test
    public void shouldReturnAuthorWhenDeleteExistingAuthorById() throws Exception {
        when(authorService.deleteAuthorById(anyLong())).thenReturn(author1);

        mockMvc.perform(delete("/api/v1/authors/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.first_name", is(firstName1)))
                .andExpect(jsonPath("$.last_name", is(lastName1)))
                .andExpect(status().isOk());

        verify(authorService).deleteAuthorById(anyLong());
    }
}
