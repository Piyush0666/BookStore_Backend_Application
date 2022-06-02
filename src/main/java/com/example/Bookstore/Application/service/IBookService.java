package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.BookDTO;
import com.example.Bookstore.Application.model.Book;

import java.util.List;

public interface IBookService {
    Book createBook(BookDTO bookDTO);

    Book getBookDataById(int BookId);

    List<Book> getAllBookData();
    Book updateRecordById(Integer BookId, BookDTO bookDTO);
    List<Book> getBookByName(String bookName);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();

    Book updateQuantity(Integer id, Integer quantity);

    Object deleteRecordByToken(int bookId);
}