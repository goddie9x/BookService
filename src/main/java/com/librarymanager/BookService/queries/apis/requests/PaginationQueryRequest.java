package com.librarymanager.BookService.queries.apis.requests;

import com.librarymanager.BookService.queries.apis.queries.GetAllBookWithPaginationQuery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PaginationQueryRequest {
    private int page = 0;
    private int size = 10;

    public GetAllBookWithPaginationQuery genQuery() {
        return new GetAllBookWithPaginationQuery(page, size);
    }
}
