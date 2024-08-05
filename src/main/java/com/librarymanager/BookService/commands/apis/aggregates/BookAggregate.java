package com.librarymanager.BookService.commands.apis.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.librarymanager.BookService.commands.apis.commands.CreateBookCommand;
import com.librarymanager.BookService.commands.apis.commands.DeleteBookCommand;
import com.librarymanager.BookService.commands.apis.commands.UpdateBookCommand;
import com.librarymanager.BookService.commands.apis.events.CreateBookEvent;
import com.librarymanager.BookService.commands.apis.events.DeleteBookEvent;
import com.librarymanager.BookService.commands.apis.events.IEvent;
import com.librarymanager.BookService.commands.apis.events.UpdateBookEvent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String aggregateIdentifier;

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        AggregateLifecycle.apply(createBookCommand.genCreateBookEvent());
    }

    @EventSourcingHandler
    public void on(IEvent event) {
        aggregateIdentifier = event.getIdentifier();
    }

    @CommandHandler
    public BookAggregate(UpdateBookCommand updateBookCommand) {
        AggregateLifecycle.apply(updateBookCommand.genUpdateBookEvent());
    }

    @CommandHandler
    public BookAggregate(DeleteBookCommand deleteBookCommand) {
        AggregateLifecycle.apply(deleteBookCommand.getDeleteBookEvent());
    }
}
