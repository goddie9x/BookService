package com.librarymanager.BookService.commands.apis.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.concurrent.Callable;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.librarymanager.CommunicationStructure.commands.commands.ICommand;

public class BookControllerTest {
    private MockMvc mockMvc;
    @Mock
    private CommandGateway commandGateway;
    @InjectMocks
    private BookCommandController bookCommandController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookCommandController).build();
    }

    private void performMockMvcTest(Callable<Void> callback, boolean shouldSucceed) throws Exception {
        if (shouldSucceed) {
            when(commandGateway.sendAndWait(any(ICommand.class))).thenReturn(null);
        } else {
            when(commandGateway.sendAndWait(any(ICommand.class))).thenThrow(new RuntimeException());
        }
        callback.call();
        verify(commandGateway, times(1)).sendAndWait(any());
    }

    @Test
    public void whenPostNewBookThenTheBookShouldBeSavedAndReturnAddedBookString() throws Exception {
        performMockMvcTest(() -> {
            mockMvc.perform(post("/api/v1/books/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"book name\",\"author\":\"book author\",\"isReady\":true}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Added book"));
            return null;
        }, true);
    }

    @Test
    public void whenPostNewBookFailedThenTheBookShouldNotBeSavedAndReturnAddBookFailedString() throws Exception {
        performMockMvcTest(() -> {
            mockMvc.perform(post("/api/v1/books/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"book name\",\"author\":\"book author\",\"isReady\":true}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Add book failed, please check the book info and try again later"));
            return null;
        }, false);
    }

    @Test
    public void whenPutUpdateBookThenTheBookShouldBeUpdatedAndReturnUpdatedBookString() throws Exception {
        performMockMvcTest(() -> {
            mockMvc.perform(
                    put("/api/v1/books/update")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(
                                    "{\"id\":\"1\",\"name\":\"Updated Book\",\"author\":\"Updated Author\",\"isReady\":true}"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Updated book"));
            return null;
        }, true);
    }

    @Test
    public void whenPutUpdateBookFailedThenTheBookShouldBeSavedAndReturnUpdateFailedBookString() throws Exception {
        performMockMvcTest(
                () -> {
                    mockMvc.perform(
                            put("/api/v1/books/update")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(
                                            "{\"id\":\"1\",\"name\":\"Updated Book\",\"author\":\"Updated Author\",\"isReady\":true}"))
                            .andExpect(status().isOk())
                            .andExpect(content()
                                    .string("Update failed please check the book info and try again later"));
                    return null;
                }, false);
    }

    @Test
    public void whenDeleteBookByIdSuccessThenTheBookShouldBeDeleteAndReturnDeletedBookMessage() throws Exception {
        performMockMvcTest(() -> {
            String bookId = "bookId";
            mockMvc.perform(delete("/api/v1/books/{bookId}", bookId))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Deleted book"));
            return null;
        }, true);
    }

    @Test
    public void whenDeleteBookByIdFailedThenReturnTheDeleteBookFailedString() throws Exception {
        performMockMvcTest(() -> {
            String bookId = "bookId";
            mockMvc.perform(delete("/api/v1/books/{bookId}", bookId))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Delete book failed, please check the bookId and try again later"));
            return null;
        }, false);
    }
}
