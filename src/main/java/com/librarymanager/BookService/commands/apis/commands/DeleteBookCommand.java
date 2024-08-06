package com.librarymanager.BookService.commands.apis.commands;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.librarymanager.BookService.commands.apis.events.DeleteBookEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteBookCommand implements ICommand{
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String bookId;

    public DeleteBookCommand(String bookId){
        this.aggregateIdentifier = UUID.randomUUID().toString();
        this.bookId = bookId;
    }

    public DeleteBookEvent genEvent() {
        return new DeleteBookEvent(aggregateIdentifier,bookId);
    }
}
