package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testCreateBook() throws Exception {
        String newBookJson = "{\"title\":\"Integration Book\",\"author\":\"Integration Author\",\"isbn\":\"123-1234567890\",\"publishedDate\":\"2023-01-01\"}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBookJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Integration Book"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDeleteBook() throws Exception {
        // Arrange
        Book book = new Book();
        book.setTitle("Delete Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("123-1234567890");
        book.setPublishedDate(LocalDate.now());
        book = bookRepository.save(book);

        // Act & Assert
        mockMvc.perform(delete("/books/" + book.getId()))
                .andExpect(status().isOk());
    }
}
