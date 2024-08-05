package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBookById() {
        // Arrange
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("Test Book");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));

        // Act
        Book book = bookService.getBookById(1L);

        // Assert
        assertNotNull(book);
        assertEquals("Test Book", book.getTitle());
    }

    @Test
    public void testCreateBook() {
        // Arrange
        Book mockBook = new Book();
        mockBook.setId(1L);
        mockBook.setTitle("New Book");

        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        // Act
        Book createdBook = bookService.createBook(mockBook);

        // Assert
        assertNotNull(createdBook);
        assertEquals("New Book", createdBook.getTitle());
    }

    @Test
    public void testDeleteBook() {
        // Arrange
        Long bookId = 1L;
        doNothing().when(bookRepository).deleteById(bookId);

        // Act
        bookService.deleteBook(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
