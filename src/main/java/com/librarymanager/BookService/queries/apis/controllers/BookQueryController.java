package com.librarymanager.BookService.queries.apis.controllers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.BookService.queries.apis.queries.GetAllBookWithPaginationQuery;
import com.librarymanager.BookService.queries.apis.queries.GetBookQuery;
import com.librarymanager.BookService.queries.apis.requests.PaginationRequest;

@RestController
@RequestMapping("api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping()
    public List<Book> getAllBook(PaginationRequest requests) {
        List<Book> response = queryGateway.query(GetAllBookWithPaginationQuery.fromPaginationRequest(requests),
                ResponseTypes.multipleInstancesOf(Book.class)).join();
        return response;
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable String bookId) {
        Book response = queryGateway.query(new GetBookQuery(bookId), ResponseTypes.instanceOf(Book.class)).join();

        return response;
    }

}
