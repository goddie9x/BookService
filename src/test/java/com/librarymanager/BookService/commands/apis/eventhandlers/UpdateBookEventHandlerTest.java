package com.librarymanager.BookService.commands.apis.eventhandlers;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.eventhandler.UpdateBookEventHandler;
import com.librarymanager.BookService.commands.apis.events.UpdateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

public class UpdateBookEventHandlerTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private UpdateBookEventHandler updateBookEventHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void whenUpdateBookEventFireThenSaveTheNewInfoForTheBook() {
        String bookId = UUID.randomUUID().toString();
        Book existingBook = new Book(bookId, "old book name", "old book author", false);
        Book updatedBook = new Book(bookId, "new book name", "new book author", true);
        UpdateBookEvent updateBookEvent = new UpdateBookEvent("id", updatedBook.genBookResponse());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));

        updateBookEventHandler.on(updateBookEvent);

        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(argThat(book -> book.getBookId().equals(updatedBook.getBookId())
                && book.getAuthor().equals(updatedBook.getAuthor())
                && book.getName().equals(updatedBook.getName())
                && book.getIsReady() == updatedBook.getIsReady()));
    }
    @Test
    public void whenUpdateBookThatNotExistThenShouldFireException(){
        Book book = new Book("id not exist","book name","book author",false);
        UpdateBookEvent updateBookEvent = new UpdateBookEvent("id", book.genBookResponse());

        assertThrows(NoSuchElementException.class, ()->{
            updateBookEventHandler.on(updateBookEvent);
        });

        verify(bookRepository,times(1)).findById(any());
        verify(bookRepository,never()).save(any());
    }
}
