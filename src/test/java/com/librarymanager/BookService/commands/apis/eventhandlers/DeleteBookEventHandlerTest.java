package com.librarymanager.BookService.commands.apis.eventhandlers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.eventhandler.DeleteBookEventHandler;
import com.librarymanager.BookService.commands.apis.events.DeleteBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

public class DeleteBookEventHandlerTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private DeleteBookEventHandler deleteBookEventHandler;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void whenDeleteBookEventFiredAndTheBookExistThenDeleteIt(){
        String fakeBookId = "fakeBookId";
        DeleteBookEvent deleteBookEvent = new DeleteBookEvent(fakeBookId, fakeBookId);

        deleteBookEventHandler.on(deleteBookEvent);

        verify(bookRepository,times(1)).deleteById(fakeBookId);
    }
}
