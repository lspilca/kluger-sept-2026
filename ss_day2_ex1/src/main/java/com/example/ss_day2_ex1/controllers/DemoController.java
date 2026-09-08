package com.example.ss_day2_ex1.controllers;

import com.example.ss_day2_ex1.dto.Book;
import com.example.ss_day2_ex1.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DemoController {

    private final BookService bookService;

//    @PreAuthorize()  @PostAuthorize() @PreFilter()  @PostFilter()

    @GetMapping("/demo")
    public String demo() {
        return "Demo";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @PostMapping("/book")
//    @PreAuthorize("@authorizationRule.acceptBookAdd(#book)") // SpEL
    @PostAuthorize("returnObject.user() == authentication.principal.username")
    public Book addBook(@RequestBody Book book) {
        IO.println("Controller...");
        bookService.addBook(book);
        return book;
    }

}
