package org.example.repository;

import org.example.entity.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    private final List<Book> books;
    private static int balance;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    public List<Book> findAll() {
        return books.stream()
                .collect(Collectors.toList());
    }
    
    public Book findById(long id){
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Book save(Book book) {
        deleteById(book.getId());
        books.add(book);
        return book;
    }

    public void delete(Book book) {
        books.remove(book);
    }

    public void deleteById(long id) {
        books.removeIf(b -> b.getId() == id);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void reStockById(long id){
        if(findById(id) == null){
            return;
        }
        if(findById(id).getStock() > 0){
            return;
        }
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(book -> {
                    book.setStock(book.getStock() + 10);
                    save(book);
                });
    }

    public void reStockAll(){
        books.stream().filter(book -> book.getStock()==0)
                .forEach(book -> {
                    book.setStock(10);
                    save(book);
        });
    }
}
