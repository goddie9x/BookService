package com.librarymanager.BookService.commands.apis.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.librarymanager.BookService.commands.apis.events.CreateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookCommand implements ICommand{
    @TargetAggregateIdentifier
    private String identifier;
    private Book book;

    public CreateBookEvent genCreateBookEvent(){
        book.setBookId(identifier);
        return new CreateBookEvent(identifier,book);
    }
}
