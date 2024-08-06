package com.librarymanager.BookService.commands.apis.commands;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.librarymanager.BookService.commands.apis.events.CreateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBookCommand implements ICommand{
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private Book book;

    public CreateBookCommand(Book book) {
        this.aggregateIdentifier = UUID.randomUUID().toString();
        book.setBookId(aggregateIdentifier);
        this.book = book;
    }

    public CreateBookEvent genEvent() {
        return new CreateBookEvent(aggregateIdentifier,book);
    }
}
