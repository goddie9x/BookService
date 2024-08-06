package com.librarymanager.BookService.commands.apis.events;

import com.librarymanager.BookService.commands.apis.models.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBookEvent implements IEvent{
    private String aggregateIdentifier;
    private Book book;
}
