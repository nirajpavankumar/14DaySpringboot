package com.example.demo.scheduletask;

import com.example.demo.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Logger;

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
          long count=bookRepository.findAll().stream()
                .filter(book -> book.getPublishedDate().isBefore(tenYearsAgo))
                .peek(book -> bookRepository.delete(book))
                 .count();
     if(count>0){
        logger.info("old books cleaned up successfully.Total deleted:{}",count);

      } else{ 
      Logger.info("No old books found to be cleaned up");
    }

        
    }
}
