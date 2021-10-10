package org.example.demin.controller;

import lombok.Getter;
import org.example.demin.models.Author;
import org.example.demin.models.Book;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Library {
  @NotNull
  private final Map<Long, List<Book>> books;
  @NotNull
  @Getter
  private final List<Author> authors;

  public Library(@NotNull List<Author> authors, @NotNull List<Book> books) {
    this.books = books.stream().collect(Collectors.groupingBy(Book::getAuthorId));
    this.authors = authors;
  }

  @NotNull
  public List<Book> booksByAuthor(@NotNull String authorName) {
    final var author = authors.stream().filter(a -> a.getName().equals(authorName)).findFirst();
    if (author.isPresent()) {
      return books.getOrDefault(author.get().getAuthorId(), List.of());
    }
    return List.of();
  }
}
