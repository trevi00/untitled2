package org.example.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String isbn;
    private int price;
    private int stock;
    private String title;
    private String author;
    private String publisher;
    private Integer publicationYear;
}
