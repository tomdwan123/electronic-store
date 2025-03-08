package com.be.electronic_store.util;

import com.be.electronic_store.model.PageableResponseModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResponseUtils {

    public static <T> ResponseEntity<PageableResponseModel<T>> get(Page<T> pageData) {

        PageableResponseModel<T> response = new PageableResponseModel<>(
                pageData.getNumber(),
                pageData.getTotalPages(),
                pageData.getTotalElements(),
                pageData.getSize(),
                pageData.getContent()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
