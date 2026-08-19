package com.akm.client.model;

public record Book(
        Long id,
        String isbn,
        String title,
        String author,
        Integer publicationYear
) {
}