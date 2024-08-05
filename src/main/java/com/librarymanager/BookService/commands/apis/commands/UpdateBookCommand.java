package com.librarymanager.BookService.commands.apis.commands;

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
    private String identifier;
    private Book book;

    public UpdateBookEvent genUpdateBookEvent() {
        return new UpdateBookEvent(identifier, book);
    }
}
