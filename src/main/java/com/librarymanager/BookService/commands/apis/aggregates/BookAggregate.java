package com.librarymanager.BookService.commands.apis.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.librarymanager.BookService.commands.apis.commands.CreateBookCommand;
import com.librarymanager.BookService.commands.apis.commands.DeleteBookCommand;
import com.librarymanager.BookService.commands.apis.commands.UpdateBookCommand;
import com.librarymanager.CommunicationStructure.commands.aggreagtes.AggregateAbstract;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Aggregate
public class BookAggregate extends AggregateAbstract{
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
}
