package com.ascend.springbootdemo.services;

import com.ascend.springbootdemo.constants.ErrorMsgEnum;
import com.ascend.springbootdemo.entities.Author;
import com.ascend.springbootdemo.entities.Post;
import com.ascend.springbootdemo.exceptions.AuthorNotFoundException;
import com.ascend.springbootdemo.exceptions.PostNotFoundException;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by BiG on 6/24/2017 AD.
 */
public class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private AuthorRepo authorRepo;

    @Mock
    private PostRepo postRepo;

    private Author author1;
    private Post post1;

    private final String content1 = "content1";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        postService = new PostService(authorRepo, postRepo);

        author1 = new Author();
        author1.setId(1L);

        post1 = new Post();
        post1.setAuthor(author1);
        post1.setContent(content1);
        post1.setId(1L);

    }

    private void assertPost(Post post, Long authorId, Long postId, String content) {
        Assert.assertThat(post.getId(), is(postId));
        Assert.assertThat(post.getAuthor().getId(), is(authorId));
        Assert.assertThat(post.getContent(), is(content));
    }

    @Test
    public void shouldReturnPostWhenCreatePostSuccessfully() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(author1);
        when(postRepo.saveAndFlush(Matchers.any(Post.class))).thenReturn(post1);

        Post post = postService.createPost(1L, post1);
        assertPost(post, 1L, 1L, content1);

        verify(authorRepo).findOne(anyLong());
        verify(postRepo).saveAndFlush(Matchers.any(Post.class));
    }

    @Test
    public void shouldThrowAuthorNotFoundExceptionWhenCreatePostWithNotExistingAuthor() throws Exception {
        when(authorRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(AuthorNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.AUTHOR_NOT_FOUND.getMsg(), 1));
        postService.createPost(1L, post1);

        verify(authorRepo).findOne(anyLong());
        verify(postRepo, never()).saveAndFlush(Matchers.any(Post.class));
    }

    @Test
    public void shouldReturnPostWhenGetExistingPostSuccessfully() throws Exception {
        post1.setId(2L);
        when(postRepo.findOne(anyLong())).thenReturn(post1);

        Post post = postService.getPostById(2L);
        assertPost(post, 1L, 2L, content1);

        verify(postRepo).findOne(anyLong());
    }

    @Test
    public void shouldThrowPostNotFoundExceptionWhenGetPostNotExisting() throws Exception {
        when(postRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(PostNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), 1));
        postService.getPostById(1L);

        verify(postRepo).findOne(anyLong());
    }

    @Test
    public void shouldReturnPostWhenUpdateExistingPost() throws Exception {
        when(postRepo.findOne(anyLong())).thenReturn(post1);
        post1.setContent("update_content");
        when(postRepo.saveAndFlush(Matchers.any(Post.class))).thenReturn(post1);

        Post post = postService.updatePost(1L, post1);
        assertPost(post, 1L, 1L, "update_content");

        verify(postRepo).findOne(anyLong());
        verify(postRepo).saveAndFlush(Matchers.any(Post.class));
    }

    @Test
    public void shouldThrowPostNotFoundExceptionWhenUpdateNotExistingPost() throws Exception {
        when(postRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(PostNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), 1));
        postService.updatePost(1L, post1);

        verify(postRepo).findOne(anyLong());
        verify(postRepo, never()).saveAndFlush(Matchers.any(Post.class));
    }

    @Test
    public void shouldReturnPostWhenDeleteExistingPostSuccessfully() throws Exception {
        post1.setId(2L);
        when(postRepo.findOne(anyLong())).thenReturn(post1);
        doNothing().when(postRepo).delete(Matchers.any(Post.class));
        doNothing().when(postRepo).flush();

        Post post = postService.deletePostById(2L);
        assertPost(post, 1L, 2L, content1);

        verify(postRepo).findOne(anyLong());
        verify(postRepo).delete(Matchers.any(Post.class));
        verify(postRepo).flush();
    }

    @Test
    public void shouldThrowPostNotFoundExceptionWhenDeleteNotExistingPostById() throws Exception {
        when(postRepo.findOne(anyLong())).thenReturn(null);

        exception.expect(PostNotFoundException.class);
        exception.expectMessage(String.format(ErrorMsgEnum.POST_NOT_FOUND.getMsg(), 1));
        postService.deletePostById(1L);

        verify(postRepo).findOne(anyLong());
        verify(postRepo, never()).delete(Matchers.any(Post.class));
        verify(postRepo, never()).flush();
    }
}
