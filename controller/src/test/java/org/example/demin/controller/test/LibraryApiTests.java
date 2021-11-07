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

import static junit.framework.TestCase.assertEquals;

public final class LibraryApiTests {
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
  public void assertIfCellIsEmptyTest() throws IOException {
    Mockito.when(factory.library()).thenReturn(new Book[3]);
    final var library = new Library(3, factory);
    library.book(0);
  }

  @Test
  public void correctBookFromCellTest() throws IOException {
    final var factoryBooks = new Book[3];
    final var bookPlacement = 1;
    factoryBooks[bookPlacement] = book;
    Mockito.when(factory.library()).thenReturn(factoryBooks.clone());

    final var library = new Library(3, factory);
    assertEquals(library.book(bookPlacement), book);
  }

  @Test
  public void bookPlacesInFirstFreeCellTest() throws IOException {
    Mockito.when(factory.library()).thenReturn(new Book[3]);
    final var library = new Library(3, factory);
    library.insertBook(book);
    assertEquals(library.book(0), book);

    library.insertBook(book);
    try {
      library.book(1);
    } catch (IllegalArgumentException e) {
      assertEquals("Unable to take book: 1 cell is empty", e.getMessage());
    }
  }

  @Test(expected = IllegalStateException.class)
  public void assertWhenLibraryOverburdenTest() throws IOException {
    final var maxCapacity = 1;
    Mockito.when(factory.library()).thenReturn(new Book[maxCapacity]);
    final var library = new Library(maxCapacity, factory);
    library.insertBook(book);
    library.insertBook(book);
  }
}
