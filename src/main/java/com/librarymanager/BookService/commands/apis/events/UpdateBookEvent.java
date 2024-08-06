package com.librarymanager.BookService.commands.apis.events;

import com.librarymanager.BookService.commands.apis.models.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateBookEvent implements IEvent{
    private String aggregateIdentifier;
    private Book book;
}
