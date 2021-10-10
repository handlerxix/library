package org.example.demin.controller;

import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public final class ApplicationMain {
  public static void main(@NotNull String[] args) {
    final var library = LibraryFactory.library();

    System.out.println("Known authors:");
    library.getAuthors().forEach(auth -> System.out.println(auth.getName()));

    final var reader = new Scanner(System.in);

    System.out.print("\nEnter author's name: ");
    final var authorName = reader.nextLine();

    final var gson = new GsonBuilder().create();
    final var books = library.booksByAuthor(authorName);
    if (books.isEmpty()) {
      System.out.println("This author have no books yet.");
      return;
    }
    books.forEach(book -> System.out.println(gson.toJson(book)));
  }
}