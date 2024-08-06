package com.librarymanager.BookService.commands.apis.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.librarymanager.BookService.commands.apis.commands.CreateBookCommand;
import com.librarymanager.BookService.commands.apis.commands.UpdateBookCommand;

@Entity
@Table(name = "Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;

    public CreateBookCommand genCreateBookCommand() {
        this.bookId = UUID.randomUUID().toString();
        return new CreateBookCommand(this);
    }

    public UpdateBookCommand genUpdateBookCommand() {
        return new UpdateBookCommand(this);
    }

    public void copy(Book book) {
        this.author = book.getAuthor();
        this.name = book.getName();
        this.isReady = book.getIsReady();
    }
}
