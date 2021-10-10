package org.example.models;

import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Data
public final class Author {
  @NotNull
  @NonNull
  private final Long authorId;
  @NotNull
  @NonNull
  private final String name;
  @NotNull
  @NonNull
  private final Date birthday;
}
