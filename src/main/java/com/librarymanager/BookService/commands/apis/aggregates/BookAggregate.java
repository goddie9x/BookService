package com.librarymanager.BookService.commands.apis.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.librarymanager.BookService.commands.apis.commands.CreateBookCommand;
import com.librarymanager.BookService.commands.apis.commands.DeleteBookCommand;
import com.librarymanager.BookService.commands.apis.commands.UpdateBookCommand;
import com.librarymanager.CommunicationStructure.commands.commands.ICommand;
import com.librarymanager.CommunicationStructure.commands.events.IEvent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Aggregate
public class BookAggregate {
    @AggregateIdentifier
    private String aggregateIdentifier;

    @CommandHandler
    public BookAggregate(CreateBookCommand createBookCommand) {
        applyEventToAggregateLifecycleFromCommand(createBookCommand);
    }

    @CommandHandler
    public BookAggregate(UpdateBookCommand updateBookCommand) {
        applyEventToAggregateLifecycleFromCommand(updateBookCommand);
    }

    @CommandHandler
    public BookAggregate(DeleteBookCommand deleteBookCommand) {
        applyEventToAggregateLifecycleFromCommand(deleteBookCommand);
    }

    @EventSourcingHandler
    public void on(IEvent event) {
        aggregateIdentifier = event.getAggregateIdentifier();
    }

    private void applyEventToAggregateLifecycleFromCommand(ICommand command){
        AggregateLifecycle.apply(command.genEvent());
    }
}
