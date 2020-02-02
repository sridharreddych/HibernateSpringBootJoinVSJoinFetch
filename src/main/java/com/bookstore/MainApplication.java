package com.bookstore;

import com.bookstore.service.BookstoreService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;

    public MainApplication(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("\n\nfetchBooksAuthorsInnerJoinBad: ");
            bookstoreService.fetchBooksAuthorsInnerJoinBad();
            
            System.out.println("\n\nfetchBooksAuthorsInnerJoinGood: ");
            bookstoreService.fetchBooksAuthorsInnerJoinGood();

            System.out.println("\n\nfetchBooksAuthorsJoinFetch: ");
            bookstoreService.fetchBooksAuthorsJoinFetch();

            System.out.println("\n\nfetchAuthorsBooksByPriceInnerJoin (notice that we fetch all books of each author): ");
            bookstoreService.fetchAuthorsBooksByPriceInnerJoin();

            System.out.println("\n\nfetchAuthorsBooksByPriceJoinFetch: ");
            bookstoreService.fetchAuthorsBooksByPriceJoinFetch();
        };
    }
}
/*JOIN VS. JOIN FETCH

See also:

How To Avoid LazyInitializationException Via JOIN FETCH
LEFT JOIN FETCH
Description: This is an application meant to reveal the differences between JOIN and JOIN FETCH. The important thing to keep in mind is that, in case of LAZY fetching, JOIN will not be capable to initialize the associated collections along with their parent objects using a single SQL SELECT. On the other hand, JOIN FETCH is capable to accomplish this kind of task. But, don't underestimate JOIN, because JOIN is the proper choice when we need to combine/join the columns of two (or more) tables in the same query, but we don't need to initialize the associated collections on the returned entity (e.g., very useful for fetching DTO).

Key points:

define two related entities (e.g., Author and Book in a one-to-many lazy-bidirectional relationship)
write a JPQL JOIN and JOIN FETCH to fetch an author including his books
write a JPQL JOIN to fetch a book (1)
write a JPQL JOIN to fetch a book including its author (2)
write a JOIN FETCH to fetch a book including its author
Notice that:

via JOIN, fetching Book of Author requires additional SELECT statements being prone to N+1 performance penalty
via JOIN (1), fetching Author of Book requires additional SELECT statements being prone to N+1 performance penalty
via JOIN (2), fetching Author of Book works exactly as JOIN FETCH (requires a single SELECT)
via JOIN FETCH, fetching each Author of a Book requires a single SELECT
 * 
 * 
 * 
 */
