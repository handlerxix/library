package org.example.demin.controller;

import com.google.inject.Guice;
import org.jetbrains.annotations.NotNull;

public final class ApplicationMain {
  public static void main(@NotNull String[] args) {
    final var libCapacity = 40;
    final var injector = Guice.createInjector(new LibraryGuiceModule(libCapacity));
    final var lib = injector.getInstance(Library.class);
    lib.printLibrary();
  }
}