package com.librarymanager.BookService.queries.apis.projections;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.BookService.queries.apis.queries.GetAllBookWithPaginationQuery;
import com.librarymanager.BookService.queries.apis.queries.GetBookQuery;

@Component
public class BookProjection {
    @Autowired
    BookRepository bookRepository;

    @QueryHandler
    public Book handle(GetBookQuery bookQuery) {
        Book response = bookRepository.findById(bookQuery.getBookId()).orElse(null);
        return response;
    }

    @QueryHandler
    public List<Book> handle(GetAllBookWithPaginationQuery query) {
        List<Book> books = bookRepository.findAll(query.toPageable()).toList();

        return books;
    }
}
