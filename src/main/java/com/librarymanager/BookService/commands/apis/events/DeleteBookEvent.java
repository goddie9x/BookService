package com.librarymanager.BookService.commands.apis.events;

import com.librarymanager.CommunicationStructure.commands.events.IEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteBookEvent implements IEvent{
    private String aggregateIdentifier;
    private String bookId;
}
