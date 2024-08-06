package com.librarymanager.BookService.commands.apis.controllers;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.librarymanager.BookService.commands.apis.commands.DeleteBookCommand;
import com.librarymanager.BookService.commands.apis.models.Book;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/v1/books")
public class BookCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("add")
    public String addBook(@RequestBody Book entity) {
        commandGateway.sendAndWait(entity.genCreateBookCommand());

        return "Added book";
    }

    @PutMapping("update")
    public String updateBook(@RequestBody Book entity) {
        try {
            commandGateway.sendAndWait(entity.genUpdateBookCommand());
            return "Updated book";
        } catch (Exception e) {
            return "update failed please try again";
        }
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        commandGateway.sendAndWait(new DeleteBookCommand(bookId));
        return "Deleted book";
    }
}
