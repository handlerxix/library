package org.example.demin.controller.test;

import org.example.demin.controller.Library;
import org.example.demin.models.Book;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.stream.IntStream;

import static org.junit.Assert.assertArrayEquals;

public final class CreateLibraryTests {
  @Mock
  @NotNull
  private Library.LibraryFactory factory;

  @Mock
  @NotNull
  private Book book;

  @Before
  public void before() {
    MockitoAnnotations.openMocks(this);
  }

  @Test(expected = IllegalArgumentException.class)
  public void unableCreateLibTest() throws IOException {
    final var capacity = 3;
    Mockito.when(factory.library()).thenReturn(new Book[capacity + 1]);
    new Library(capacity, factory);
  }

  @Test
  public void booksOrderAndFreeCellsTest() throws IOException {
    final var capacity = 3;
    final var factoryBooks = new Book[capacity];
    factoryBooks[0] = book;
    Mockito.when(factory.library()).thenReturn(factoryBooks.clone());

    final var lib = new Library(capacity, factory);
    final var libBooks = new Book[capacity];
    IntStream.range(0, capacity).forEach(idx -> {
          try {
            libBooks[idx] = lib.book(idx);
          } catch (IllegalArgumentException e) {
            libBooks[idx] = null;
          }
        }
    );
    assertArrayEquals(factoryBooks, libBooks);
  }
}
