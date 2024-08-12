package com.librarymanager.BookService.commands.apis.eventhandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.librarymanager.BookService.commands.apis.commands.CreateBookCommand;
import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.eventhandler.CreateBookEventHandler;
import com.librarymanager.BookService.commands.apis.events.CreateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

public class CreateBookEventHandlerTests {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private CreateBookEventHandler createBookEventHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenCreateBookEventFireThenItShouldSaveTheBook() {
        Book book = new Book(null, "Book name", "Book author", true);
        CreateBookEvent createBookEvent = book.genCreateBookCommand().genEvent();

        createBookEventHandler.on(createBookEvent);
        verify(bookRepository,times(1)).save(book);
    }
}
