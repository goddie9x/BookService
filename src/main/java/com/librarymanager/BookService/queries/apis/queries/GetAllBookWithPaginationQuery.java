package com.librarymanager.BookService.queries.apis.queries;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetAllBookWithPaginationQuery {
    private int page;
    private int size;

    public Pageable toPageable() {
        return PageRequest.of(page, size);
    }
}
