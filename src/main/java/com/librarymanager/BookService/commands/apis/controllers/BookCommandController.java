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
        try {
            commandGateway.sendAndWait(entity.genCreateBookCommand());
            return "Added book";
        } catch (Exception e) {
            return "Add book failed, please check the book info and try again later";
        }
    }

    @PutMapping("update")
    public String updateBook(@RequestBody Book entity) {
        try {
            commandGateway.sendAndWait(entity.genUpdateBookCommand());
            return "Updated book";
        } catch (Exception e) {
            return "Update failed please check the book info and try again later";
        }
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable String bookId) {
        try {
            commandGateway.sendAndWait(new DeleteBookCommand(bookId));
            return "Deleted book";
        } catch (Exception e) {
            return "Delete book failed, please check the bookId and try again later";
        }
    }
}
