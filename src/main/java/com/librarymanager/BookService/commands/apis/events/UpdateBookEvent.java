package com.librarymanager.BookService.commands.apis.events;

import com.librarymanager.CommunicationStructure.commands.events.IEvent;
import com.librarymanager.CommunicationStructure.queries.responses.BookResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateBookEvent implements IEvent{
    private String aggregateIdentifier;
    private BookResponse book;
}
