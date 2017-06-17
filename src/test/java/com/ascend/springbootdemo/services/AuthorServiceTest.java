package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by BiG on 6/17/2017 AD.
 */
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;

    @Mock
    private AuthorRepo authorRepo;

    private Author author1;
    private Author author2;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorService(authorRepo);

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
        when(authorRepo.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.getAllAuthor();
        assertAuthor(authors.get(0), 1L, "firstName1", "lastName1");
        assertAuthor(authors.get(1), 2L, "firstName2", "lastName2");

        verify(authorRepo).findAll();
    }

    private void assertAuthor(Author authors, Long id, String firstName, String lastName) {
        Assert.assertThat(authors.getId(), is(id));
        Assert.assertThat(authors.getFirstName(), is(firstName));
        Assert.assertThat(authors.getLastName(), is(lastName));
    }
}
