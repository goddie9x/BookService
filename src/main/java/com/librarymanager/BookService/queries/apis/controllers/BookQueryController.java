package com.librarymanager.BookService.queries.apis.controllers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.BookService.queries.apis.requests.PaginationQueryRequest;
import com.librarymanager.CommunicationStructure.queries.queries.GetBookByIdQuery;

@RestController
@RequestMapping("api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping()
    public List<Book> getAllBookWithPagination(PaginationQueryRequest requests) {
        List<Book> response = queryGateway.query(requests.genQuery(),
                ResponseTypes.multipleInstancesOf(Book.class)).join();
                
        return response;
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable String bookId) {
        try {
            GetBookByIdQuery query = new GetBookByIdQuery(bookId);
            Book response = queryGateway.query(query, ResponseTypes.instanceOf(Book.class)).join();
    
            return response;
        } catch (Exception e) {
            return null;
        }
    }

}
