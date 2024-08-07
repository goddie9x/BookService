package com.librarymanager.BookService.commands.apis.events;

import com.librarymanager.BookService.commands.apis.models.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBookEvent implements IEvent{
    private String aggregateIdentifier;
    private Book book;
}
