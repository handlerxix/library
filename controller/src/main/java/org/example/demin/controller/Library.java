package org.example.demin.controller;

import com.google.gson.Gson;
import org.example.demin.models.Book;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.IntStream;

public final class Library {

  @NotNull
  private final Book[] books;

  @NotNull
  private final Integer capacity;

  @Inject
  public Library(@NotNull Integer capacity, @NotNull LibraryFactory factory) throws IOException {
    final var booksFromFile = factory.library();
    if (booksFromFile.length > capacity) {
      throw new IllegalArgumentException(
          String.format("Unable to initialize library: founded %d books, which is more than max capacity (%d).",
              booksFromFile.length,
              capacity
          ));
    }
    final var books = new Book[capacity];
    IntStream.range(0, booksFromFile.length).forEach(idx -> books[idx] = booksFromFile[idx]);
    this.books = books;
    this.capacity = capacity;
  }

  @NotNull
  public Book book(@NotNull Integer index) {
    final var ret = books[index];
    books[index] = null;
    if (ret == null) {
      throw new IllegalArgumentException(String.format("Unable to take book: %s cell is empty", index));
    }
    System.out.printf("Book %s was taken from cell #%d%n", ret, index);
    return ret;
  }

  public void insertBook(@NotNull Book book) {
    final var freeCell = freeCell();
    if (freeCell == -1) {
      throw new IllegalStateException("Unable insert new book: not found free cells");
    }
    books[freeCell] = book;
  }

  public void printLibrary() {
    Arrays.stream(books).forEach(b -> System.out.printf("Book: %s\n", b));
  }

  @NotNull
  private Integer freeCell() {
    final var cell = IntStream.range(0, capacity).filter(idx -> books[idx] == null).findFirst();
    return cell.isPresent() ? cell.getAsInt() : -1;
  }

  public static final class LibraryFactory {

    @NotNull
    private final Path path;

    @Inject
    public LibraryFactory(@NotNull Path path) {
      this.path = path;
    }

    @NotNull
    public Book[] library() throws IOException {
      final var folder = path.toFile();
      final var libContent = Files.readString(folder.toPath());
      return new Gson().fromJson(libContent, Book[].class);
    }
  }
}
