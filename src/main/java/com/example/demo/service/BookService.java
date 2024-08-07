package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @CachePut(value = "books", key = "#book.id")
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
