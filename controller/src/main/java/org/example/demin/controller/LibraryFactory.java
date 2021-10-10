package org.example.demin.controller;

import org.example.demin.models.Author;
import org.example.demin.models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class LibraryFactory {
  @NotNull
  private static ThreadLocalRandom random = ThreadLocalRandom.current();

  @NotNull
  private static Date randomDate() {
    return new Date(random.nextLong());
  }

  @NotNull
  public static Library library() {
    List<Author> authors = List.of(
        new Author(0L, "Keks Ukropovish", randomDate()),
        new Author(1L, "Morkovka V Trusikah", randomDate()),
        new Author(2L, "Lev Tolstoi (na slovah)", randomDate()),
        new Author(3L, "Random Author", randomDate()),
        new Author(4L, "Ukrop Keksovish", randomDate())
    );

    List<Book> books = List.of(
        new Book("Book from Keks Ukropovish 1", 0L, "Some edition", randomDate()),
        new Book("Book from Keks Ukropovish 2", 0L, "Some edition", randomDate()),
        new Book("Book from Keks Ukropovish 3", 0L, "Some edition", randomDate()),
        new Book("Book from Keks Ukropovish 4", 0L, "Some edition", randomDate()),
        new Book("Book from Morkovka 1", 1L, "Test edition", randomDate()),
        new Book("Book from Morkovka 2", 1L, "Test edition", randomDate()),
        new Book("Book from Morkovka 3", 1L, "Test edition", randomDate()),
        new Book("Book from Morkovka 4", 1L, "Test edition", randomDate()),
        new Book("Book from Tolstoi 1", 2L, "Edition", randomDate()),
        new Book("Book from Tolstoi 2", 2L, "Edition", randomDate()),
        new Book("Book from Tolstoi 3", 2L, "Edition", randomDate()),
        new Book("Book from Tolstoi 4", 2L, "Edition", randomDate()),
        new Book("Book from Random Author 1", 3L, "Random Edition", randomDate()),
        new Book("Book from Random Author 2", 3L, "Random Edition", randomDate())
    );

    return new Library(authors, books);
  }
}
