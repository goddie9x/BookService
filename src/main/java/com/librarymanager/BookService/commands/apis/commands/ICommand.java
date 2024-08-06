package com.librarymanager.BookService.commands.apis.commands;

import com.librarymanager.BookService.commands.apis.events.IEvent;

public interface ICommand {
    public IEvent genEvent();
}
