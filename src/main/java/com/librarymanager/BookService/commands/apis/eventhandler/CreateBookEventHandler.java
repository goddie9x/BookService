package com.librarymanager.BookService.commands.apis.eventhandler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.events.CreateBookEvent;

@Component
public class CreateBookEventHandler {
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(CreateBookEvent event) {
        bookRepository.save(event.getBook());
    }
}
