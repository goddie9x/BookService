package com.librarymanager.BookService.commands.apis.eventhandler;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanager.BookService.commands.apis.data.BookRepository;
import com.librarymanager.BookService.commands.apis.events.UpdateBookEvent;
import com.librarymanager.BookService.commands.apis.models.Book;

@Component
public class UpdateBookEventHandler {
    @Autowired
    BookRepository bookRepository;

    @EventHandler
    public void on(UpdateBookEvent event) {
        Book bookNeedToUpdateInfo = event.getBook();
        Book target = bookRepository.findById(bookNeedToUpdateInfo.getBookId()).orElseThrow();

        target.Copy(bookNeedToUpdateInfo);
        bookRepository.save(target);
    }
}
