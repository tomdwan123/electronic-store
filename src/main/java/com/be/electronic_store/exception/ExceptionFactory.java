package com.be.electronic_store.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionFactory {

    public static ObjectNotFoundException notFoundException(String message) {
        return new ObjectNotFoundException(message);
    }

    public static ConflictException conflictException(String message) {
        return new ConflictException(message);
    }

    public static ValidationException validationException(String message) {
        return new ValidationException(message);
    }

    public static BadRequestException badRequestException(String message) {
        return new BadRequestException(message);
    }

    public static ForbiddenException forbiddenException(String message) {
        return new ForbiddenException(message);
    }
}
