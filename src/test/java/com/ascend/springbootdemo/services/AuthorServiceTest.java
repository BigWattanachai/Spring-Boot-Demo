package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.constants.ErrorMsgEnum;
import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.entities.Post;
import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.repositories.AuthorRepo;
import com.ascend.springbootdemo.repositories.PostRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
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

    @Mock
    private PostRepo postRepo;

    private Author author1;
    private Author author2;
    private Post post1;
    private Post post2;
    private final String firstName1 = "firstName1";
    private final String firstName2 = "firstName2";
    private final String lastName1 = "lastName1";
    private final String lastName2 = "lastName2";
    private final String content1 = "content1";
    private final String content2 = "content2";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorService(authorRepo, postRepo);

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

    private void assertAuthor(Author authors, Long id, String firstName, String lastName) {
        Assert.assertThat(authors.getId(), is(id));
        Assert.assertThat(authors.getFirstName(), is(firstName));
        Assert.assertThat(authors.getLastName(), is(lastName));
    }

    private void assertPost(Post post, Long authorId, Long id, String content) {
        Assert.assertThat(post.getId(), is(id));
        Assert.assertThat(post.getAuthor().getId(), is(authorId));
        Assert.assertThat(post.getContent(), is(content));
    }

    @Test
    public void shouldReturnAuthorWhenGetAllExistingAuthor() throws Exception {
        when(authorRepo.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.getAllAuthor();
        assertAuthor(authors.get(0), 1L, firstName1, lastName1);
        assertAuthor(authors.get(1), 2L, firstName2, lastName2);

        verify(authorRepo).findAll();
    }


    @Test
    public void shouldReturnAuthorWhenCreateAuthorSuccessfully() throws Exception {
        when(authorRepo.saveAndFlush(Matchers.any(Author.class))).thenReturn(author1);

        Author author = authorService.createAuthor(author1);
        assertAuthor(author, 1L, firstName1, lastName1);

        verify(authorRepo).saveAndFlush(Matchers.any(Author.class));
    }


    @Test
    public void shouldReturnAuthorWhenGetExistingAuthorById() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(author1);

        Author author = authorService.getAuthorById(1L);
        assertAuthor(author, 1L, firstName1, lastName1);

        verify(authorRepo).findOne(anyLong());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetNotExistingAuthorById() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(AuthorNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), 1));
        authorService.getAuthorById(1L);

        verify(authorService).getAuthorById(anyLong());
    }

    @Test
    public void shouldReturnAuthorWhenDeleteExistingAuthorById() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(author1);
        doNothing().when(authorRepo).delete(Matchers.any(Author.class));
        doNothing().when(authorRepo).flush();

        Author author = authorService.deleteAuthorById(1L);
        assertAuthor(author, 1L, firstName1, lastName1);

        verify(authorRepo).delete(Matchers.any(Author.class));
        verify(authorRepo).findOne(anyLong());
        verify(authorRepo).flush();
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDeleteNotExistingAuthorById() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(AuthorNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), 1));
        authorService.deleteAuthorById(1L);

        verify(authorService).getAuthorById(anyLong());
        verify(authorRepo, never()).findOne(anyLong());
        verify(authorRepo, never()).flush();
    }

    @Test
    public void shouldReturnAuthorWhenUUpdateExistingAutho() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(author1);
        author1.setFirstName("update_first_name");
        author1.setLastName("update_last_name");
        when(authorRepo.saveAndFlush(Matchers.any(Author.class))).thenReturn(author1);

        Author author = authorService.updateAuthor(1L, author1);
        assertAuthor(author, 1L, "update_first_name", "update_last_name");

        verify(authorRepo).findOne(anyLong());
        verify(authorRepo).saveAndFlush(Matchers.any(Author.class));
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUpdateNotExistingAuthor() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(AuthorNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), 1));
        authorService.updateAuthor(1L, author1);

        verify(authorService).getAuthorById(anyLong());
        verify(authorRepo, never()).saveAndFlush(Matchers.any(Author.class));
    }

    @Test
    public void shouldReturnPostWhenCreatePostSuccessfully() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(author1);
        when(postRepo.saveAndFlush(Matchers.any(Post.class))).thenReturn(post1);

        Post post = authorService.createPost(1L, post1);
        assertPost(post, 1L, 1L, "content1");

        verify(authorRepo).findOne(anyLong());
        verify(postRepo).saveAndFlush(Matchers.any(Post.class));
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenCreatePostWithNotExistingAuthor() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(AuthorNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), 1));
        authorService.createPost(1L, post1);

        verify(authorRepo).findOne(anyLong());
        verify(postRepo, never()).saveAndFlush(Matchers.any(Post.class));
    }
}
