package com.librarymanager.BookService.commands.apis.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeleteBookEvent implements IEvent{
    private String aggregateIdentifier;
    private String bookId;
}
