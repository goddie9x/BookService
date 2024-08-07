package com.librarymanager.BookService.commands.apis.eventhandler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.events.DeleteBookEvent;

@Component
public class DeleteBookEventHandler {
    @Autowired
    BookRepository bookRepository;

    @EventHandler
    public void on(DeleteBookEvent event) {
        bookRepository.deleteById(event.getBookId());
    }
}
