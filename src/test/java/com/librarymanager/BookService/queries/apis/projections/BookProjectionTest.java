package com.librarymanager.BookService.queries.apis.projections;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.BookService.queries.apis.queries.GetAllBookWithPaginationQuery;
import com.librarymanager.CommunicationStructure.queries.queries.GetBookByIdQuery;
import com.librarymanager.CommunicationStructure.queries.responses.BookResponse;

public class BookProjectionTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookProjection bookProjection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenGetBookByIdQueryFiredThenReturnResponseBook() {
        String bookId = "id";
        Book book = new Book(bookId, "book name", "book author", true);
        GetBookByIdQuery getBookByIdQuery = new GetBookByIdQuery(bookId);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        BookResponse response = bookProjection.handle(getBookByIdQuery);

        assertAll("BookResponse",
                () -> assertNotNull(response, "The response should not be null"),
                () -> assertEquals(bookId, response.getBookId(), "Book ID should match"),
                () -> assertEquals(book.getName(), response.getName(), "Book name should match"),
                () -> assertEquals(book.getAuthor(), response.getAuthor(), "Author should match"),
                () -> assertEquals(book.getIsReady(), response.getIsReady(), "The state isReady should match"));
    }

    @Test
    public void whenGetBookByIdQueryAndBookNotFoundThenReturnNull() {
        String bookId = "bookId";
        GetBookByIdQuery query = new GetBookByIdQuery(bookId);
        BookResponse response = bookProjection.handle(query);

        assertNull(response, "The response should be null when the book is not found");
    }
     @Test
    public void whenGetAllBooksWithPaginationQueryThenReturnBooks() {
        GetAllBookWithPaginationQuery query = new GetAllBookWithPaginationQuery(0, 2);
        List<Book> books = Arrays.asList(
            new Book("id1", "Name1", "Author1", true),
            new Book("id2", "Name2", "Author2", false)
        );
        Page<Book> bookPage = new PageImpl<>(books);

        when(bookRepository.findAll(query.toPageable())).thenReturn(bookPage);

        List<Book> response = bookProjection.handle(query);

        assertAll("Books List",
            () -> assertNotNull(response, "The response should not be null"),
            () -> assertEquals(2, response.size(), "The size of the book list should match"),
            () -> {
                Book firstBook = response.get(0);
                assertAll("First Book",
                    () -> assertEquals(books.get(0).getBookId(), firstBook.getBookId(), "First book ID should match"),
                    () -> assertEquals(books.get(0).getName(), firstBook.getName(), "First book title should match"),
                    () -> assertEquals(books.get(0).getAuthor(), firstBook.getAuthor(), "First book author should match"),
                    () -> assertEquals(books.get(0).getIsReady(), firstBook.getIsReady(), "First book IsReady should be match")
                );
            },
            () -> {
                Book secondBook = response.get(1);
                assertAll("Second Book",
                    () -> assertEquals(books.get(1).getBookId(), secondBook.getBookId(), "Second book ID should match"),
                    () -> assertEquals(books.get(1).getName(), secondBook.getName(), "Second book title should match"),
                    () -> assertEquals(books.get(1).getAuthor(), secondBook.getAuthor(), "Second book author should match"),
                    () -> assertEquals(books.get(1).getIsReady(), secondBook.getIsReady(), "Second book IsReady should be match")
                );
            }
        );
    }
}
