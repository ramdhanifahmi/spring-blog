package com.blog.fahmi;

import com.blog.fahmi.domain.Post;
import com.blog.fahmi.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PostService postService;

    @Test
    void shouldGetAllPosts() throws Exception {
        List<Post> posts = List.of(
                new Post(1L, "First Post", "This is my first post", LocalDateTime.now(), null),
                new Post(2L, "Second Post", "Another post", LocalDateTime.now(), null)
        );

        Mockito.when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("First Post"));
    }

    @Test
    void shouldGetPostById() throws Exception {
        Post post = new Post(1L, "My Post", "Post content", LocalDateTime.now(), null);

        Mockito.when(postService.getPostById(1L)).thenReturn(Optional.of(post));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("My Post"));
    }

    @Test
    void shouldCreatePost() throws Exception {
        Post post = new Post(1L, "New Post", "Content", LocalDateTime.now(), null);

        Mockito.when(postService.createPost(Mockito.any(Post.class)))
                .thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/posts") 
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"New Post\", \"content\": \"Content\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    

    @Test
    void shouldDeletePost() throws Exception {
        Mockito.doNothing().when(postService).deletePost(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/posts/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

