package com.example.demo.scheduletask;

import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class BookCleanupScheduler {

    private final BookRepository bookRepository;

    @Autowired
    public BookCleanupScheduler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Run this task every 30 seconds
    @Scheduled(cron = "0/30 * * * * ?")
    public void cleanUpOldBooks() {
        LocalDate tenYearsAgo = LocalDate.now().minus(10, ChronoUnit.YEARS);
        bookRepository.findAll().stream()
                .filter(book -> book.getPublishedDate().isBefore(tenYearsAgo))
                .forEach(book -> bookRepository.delete(book));
        System.out.println("Old books cleaned up successfully");
    }
}
