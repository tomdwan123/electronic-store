package com.be.electronic_store.model;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class PageableResponseModel<T> {

    int currentPage;
    int totalPages;
    long totalItems;
    int pageSize;
    @NotNull
    List<T> content;
}
