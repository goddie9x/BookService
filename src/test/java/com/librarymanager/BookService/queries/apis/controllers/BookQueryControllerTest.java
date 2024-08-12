package com.librarymanager.BookService.queries.apis.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.BookService.queries.apis.requests.PaginationQueryRequest;
import com.librarymanager.CommunicationStructure.queries.queries.GetBookByIdQuery;

public class BookQueryControllerTest {
    private MockMvc mockMvc;
    @Mock
    private QueryGateway queryGateway;
    @InjectMocks
    private BookQueryController bookQueryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    }

    @Test
    public void whenRequestGetBookByIdThenShouldSendQueryAndReturnBook() throws Exception {
        String bookId = "bookId";
        Book book = new Book(bookId, "book name", "book author", true);

        when(queryGateway.query(any(GetBookByIdQuery.class), eq(ResponseTypes.instanceOf(Book.class))))
                .thenReturn(CompletableFuture.completedFuture(book));

        mockMvc.perform(get("/api/v1/books/{bookId}", bookId))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.bookId").value(bookId),
                        jsonPath("$.name").value(book.getName()),
                        jsonPath("$.author").value(book.getAuthor()),
                        jsonPath("$.isReady").value(true));

        verify(queryGateway, times(1)).query(any(GetBookByIdQuery.class), eq(ResponseTypes.instanceOf(Book.class)));
    }

    @Test
    public void whenRequestGetBookByIdNotExistThenShouldSendQueryAndReturnNull() throws Exception {
        String bookId = "bookId";

        mockMvc.perform(get("/api/v1/books/{bookId}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void whenRequestGetBooksWithPage0AndSize2ThenShouldSendQueryAndReturnTheBooks() throws Exception {
        Book book1 = new Book(UUID.randomUUID().toString(), "name1", "Author1", true);
        Book book2 = new Book(UUID.randomUUID().toString(), "name2", "Author2", false);
        List<Book> books = Arrays.asList(book1, book2);

        when(queryGateway.query(any(), eq(ResponseTypes.multipleInstancesOf(Book.class))))
                .thenReturn(CompletableFuture.completedFuture(books));

        mockMvc.perform(get("/api/v1/books")
                .param("page", "0")
                .param("size", "2"))
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$[0].bookId").value(book1.getBookId()),
                        jsonPath("$[0].name").value("name1"),
                        jsonPath("$[0].author").value("Author1"),
                        jsonPath("$[0].isReady").value(true),
                        jsonPath("$[1].bookId").value(book2.getBookId()),
                        jsonPath("$[1].name").value("name2"),
                        jsonPath("$[1].author").value("Author2"),
                        jsonPath("$[1].isReady").value(false));

        verify(queryGateway, times(1)).query(any(), eq(ResponseTypes.multipleInstancesOf(Book.class)));
    }
}
