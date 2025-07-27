package org.example;

import lombok.Builder;
import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.example.service.BookService;

import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    private final BookRepository bookRepository = new BookRepository();
    private final BookService bookService = new BookService(bookRepository);

    public void addBook(){
        System.out.println("Enter book details");
        System.out.println("id");
        int id = sc.nextInt();
        System.out.println("isbn");
        String isbn = sc.next();
        System.out.println("price");
        int price = sc.nextInt();
        System.out.println("stock");
        int stock = sc.nextInt();
        System.out.println("title");
        String title = sc.next();
        System.out.println("author");
        String author = sc.next();
        System.out.println("publisher");
        String publisher = sc.next();
        System.out.println("publication year");
        Integer publicationYear = sc.nextInt();

        bookService.addBook(Book.builder().id(id).isbn(isbn).price(price).stock(stock).title(title).author(author).publisher(publisher).publicationYear(publicationYear).build());
    }

    public void sellBook(){
        System.out.println("Enter book id");
        int id = sc.nextInt();
        if(bookRepository.findById(id) == null){
            System.out.println("Book not found");
            return;
        }
        bookService.sellBook(bookRepository.findById(id));
    }

    public void findById(){
        System.out.println("Enter book id");
        int id = sc.nextInt();
        if(bookRepository.findById(id) == null){
            System.out.println("Book not found");
            return;
        }
        System.out.println(bookRepository.findById(id));
    }

    public void findAllBook(){
        bookService.findAllBook().forEach(System.out::println);
    }

    public void menu(){
        System.out.println("1. Add book");
        System.out.println("2. Sell book");
        System.out.println("3. findAllBook");
        System.out.println("4. findBookById");
        System.out.println("5. Exit");
        System.out.println("Enter your choice");
    }

    public static void main(String[] args) {
        Main main = new Main();
        while(true){
            main.menu();
            int choice = main.sc.nextInt();
            switch (choice){
                case 1:
                    main.addBook();
                    break;
                case 2:
                    main.sellBook();
                    main.bookService.reStockAll();
                    break;
                case 3:
                    main.bookService.findAllBook().forEach(System.out::println);
                    break;
                case 4:
                    main.findById();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    main.menu();
                    break;
            }
        }
    }
}