package com.librarymanager.BookService.commands.apis.eventhandler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.events.UpdateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;
import com.librarymanager.CommunicationStructure.queries.responses.BookResponse;

@Component
public class UpdateBookEventHandler {
    @Autowired
    BookRepository bookRepository;

    @EventHandler
    public void on(UpdateBookEvent event) {
        BookResponse bookNeedToUpdateInfo = event.getBook();
        Book target = bookRepository.findById(bookNeedToUpdateInfo.getBookId()).orElseThrow();

        target.copyAttributesFromBookResponseIfValid(bookNeedToUpdateInfo);
        bookRepository.save(target);
    }
}
