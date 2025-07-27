package org.example.service;

import org.example.entity.Book;
import org.example.repository.BookRepository;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        Book existing = bookRepository.findById(book.getId());
        if (existing == null) {
            bookRepository.save(book);
        } else {
            existing.setStock(existing.getStock() + book.getStock());
            bookRepository.save(existing);
        }
        return book;
    }

    public void sellBook(Book book){
        if(bookRepository.findById(book.getId()) != null){
            int stock = bookRepository.findById(book.getId()).getStock();
            stock -= book.getStock();
            book.setStock(stock);
            bookRepository.save(book);
        }
    }

    public Book findById(long id){
        return bookRepository.findById(id);
    }

    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    public void reStockById(long id){
        bookRepository.reStockById(id);
    }

    public void reStockAll(){
        bookRepository.reStockAll();
    }
}