package com.librarymanager.BookService.commands.apis.aggregates;

import java.util.UUID;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.librarymanager.BookService.commands.apis.commands.CreateBookCommand;
import com.librarymanager.BookService.commands.apis.commands.DeleteBookCommand;
import com.librarymanager.BookService.commands.apis.commands.UpdateBookCommand;
import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.CommunicationStructure.commands.commands.ICommand;
import com.librarymanager.CommunicationStructure.commands.events.IEvent;
import com.librarymanager.CommunicationStructure.queries.responses.BookResponse;

public class BookAggregateTest {
    private AggregateTestFixture<BookAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(BookAggregate.class);
    }

    private void testCommand(ICommand command) {
        IEvent event = command.genEvent();

        fixture
                .givenNoPriorActivity()
                .when(command)
                .expectSuccessfulHandlerExecution()
                .expectEvents(event);
    }

    @Test
    public void whenCreateBookCommandFireThenShouldFireCreateBookEvent() {
        String bookId = UUID.randomUUID().toString();
        Book book = new Book(bookId, bookId, bookId, true);
        CreateBookCommand createBookCommand = new CreateBookCommand(book);

        testCommand(createBookCommand);
    }

    @Test
    public void whenUpdateBookCommandFireThenShouldFireUpdateBookEvent() {
        String bookId = UUID.randomUUID().toString();
        BookResponse bookDataForUpdate = new BookResponse(bookId, "test1", "test1", false);
        UpdateBookCommand updateBookCommand = new UpdateBookCommand(bookDataForUpdate);

        testCommand(updateBookCommand);
    }

    @Test
    public void whenDeleteBookCommandFireThenShouldFireDeleteBookEvent() {
        String bookId = UUID.randomUUID().toString();
        DeleteBookCommand deleteBookCommand = new DeleteBookCommand(bookId);

        testCommand(deleteBookCommand);
    }
}
