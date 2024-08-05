package com.librarymanager.BookService.commands.apis.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanager.BookService.commands.apis.models.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
