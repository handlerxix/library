package org.example.demin.controller;

import com.google.gson.Gson;
import org.example.demin.models.Book;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

  public Library(@NotNull Integer capacity, @NotNull Book[] books) {
    this.books = books;
    this.capacity = capacity;
  }

  @Nullable
  public Book book(@NotNull Integer index) {
    final var ret = books[index];
    books[index] = null;
    if (ret != null) {
      System.out.printf("Book %s was taken from cell #%d%n", ret, index);
    }
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
    private final Path libraryPath;

    @Inject
    public LibraryFactory(@NotNull Path libraryPath) {
      this.libraryPath = libraryPath;
    }

    @NotNull
    public Library library(@NotNull Integer capacity) throws IOException {
      final var folder = libraryPath.toFile();
      final var libContent = Files.readString(folder.toPath());
      final var gson = new Gson();
      final var booksFromFolder = gson.fromJson(libContent, Book[].class);
      if (booksFromFolder.length > capacity) {
        throw new IllegalStateException(
            String.format("Unable to initialize library: founded %d books, which is more than max capacity (%d).",
                booksFromFolder.length,
                capacity
            ));
      }
      final var books = new Book[capacity];
      IntStream.range(0, booksFromFolder.length).forEach(idx -> books[idx] = booksFromFolder[idx]);
      return new Library(capacity, books);
    }
  }
}
