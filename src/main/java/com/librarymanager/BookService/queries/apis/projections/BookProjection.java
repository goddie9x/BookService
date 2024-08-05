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
        try {
            Book response = bookRepository.findById(bookQuery.getBookId()).orElseThrow();

            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @QueryHandler
    public List<Book> handle(GetAllBookWithPaginationQuery query) {
        try {
            List<Book> books = bookRepository.findAll(query.toPageable()).toList();

            return books;
        } catch (Exception e) {
            return null;
        }
    }
}
