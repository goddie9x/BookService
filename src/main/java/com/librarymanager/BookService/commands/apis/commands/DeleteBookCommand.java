package com.librarymanager.BookService.commands.apis.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.librarymanager.BookService.commands.apis.events.DeleteBookEvent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DeleteBookCommand implements ICommand {
    @TargetAggregateIdentifier
    private String identifier;
    private String bookId;

    public DeleteBookEvent getDeleteBookEvent() {
        return new DeleteBookEvent(identifier, bookId);
    }
}
