package com.librarymanager.BookService.commands.apis.commands;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.librarymanager.BookService.commands.apis.events.UpdateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateBookCommand implements ICommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private Book book;

    public UpdateBookCommand(Book book) {
        this.aggregateIdentifier = UUID.randomUUID().toString();
        this.book = book;
    }

    public UpdateBookEvent genEvent() {
        return new UpdateBookEvent(aggregateIdentifier, book);
    }
}
