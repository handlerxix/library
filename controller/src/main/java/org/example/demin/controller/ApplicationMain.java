package org.example.demin.controller;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Objects;

public final class ApplicationMain {
  public static void main(@NotNull String[] args) throws IOException, URISyntaxException {
    final var path = Objects.requireNonNull(ApplicationMain.class.getClassLoader().getResource("library.json")).toURI();
    final var library = new Library.LibraryFactory(Path.of(path).normalize().toAbsolutePath()).library(3);
    library.printLibrary();
    library.book(3);
    library.printLibrary();
  }
}