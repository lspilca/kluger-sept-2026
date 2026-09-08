package com.example.ss_day2_ex1.security.rules;

import com.example.ss_day2_ex1.dto.Book;
import com.example.ss_day2_ex1.security.adapters.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationRule {

    public boolean acceptBookAdd(Book book) {
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername().equals(book.user());
    }
}
