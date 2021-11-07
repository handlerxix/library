package org.example.demin.controller;

import com.google.inject.AbstractModule;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Objects;

public final class LibraryGuiceModule extends AbstractModule {
  @NotNull
  private final Integer libCapacity;

  public LibraryGuiceModule(@NotNull Integer libCapacity) {
    this.libCapacity = libCapacity;
  }

  @SneakyThrows
  @Override
  protected void configure() {
    var path = Objects.requireNonNull(ApplicationMain.class.getClassLoader().getResource("library.json")).toURI().normalize();
    bind(Path.class).toInstance(Path.of(path));
    bind(Integer.class).toInstance(libCapacity);
  }
}
