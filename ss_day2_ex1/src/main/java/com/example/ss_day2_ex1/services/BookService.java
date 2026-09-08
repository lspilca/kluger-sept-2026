package com.example.ss_day2_ex1.services;

import com.example.ss_day2_ex1.dto.Book;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class BookService {

    @PreAuthorize("true")
    public void addBook(@RequestBody Book book) {
        IO.println("ADDING BOOK " + book);
    }

    @PreFilter("filterObject.user() == authentication.principal.username") // bob
    public void addBooks(List<Book> books) { // ["bob", "john", "bob"]
        // ["bob", "bob"]
    }

    @PostFilter("filterObject.user() == authentication.principal.username")
    public List<Book> findBooks() {
        return List.of();
    }
}
