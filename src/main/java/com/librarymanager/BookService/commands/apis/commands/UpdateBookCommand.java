package com.librarymanager.BookService.commands.apis.commands;

import com.librarymanager.BookService.commands.apis.events.UpdateBookEvent;
import com.librarymanager.CommunicationStructure.queries.responses.BookResponse;

public class UpdateBookCommand extends com.librarymanager.CommunicationStructure.commands.commands.UpdateBookCommand {
    public UpdateBookCommand(BookResponse book) {
        super(book);
    }

    @Override
    public UpdateBookEvent genEvent() {
        return new UpdateBookEvent(aggregateIdentifier, book);
    }
}
