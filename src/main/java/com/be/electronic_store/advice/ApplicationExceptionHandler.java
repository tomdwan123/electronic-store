package com.be.electronic_store.advice;

import com.be.electronic_store.constant.ExceptionEnum;
import com.be.electronic_store.exception.ConflictException;
import com.be.electronic_store.exception.ObjectNotFoundException;
import com.be.electronic_store.exception.PropertiesProducer;
import com.be.electronic_store.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ProblemDetail handleException(ObjectNotFoundException ex){

        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle(ExceptionEnum.OBJECT_NOT_FOUND_EXCEPTION.name());
        consumePropertiesIfPresent(problem, ex);
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleException(MethodArgumentNotValidException ex){

        log.error("MethodArgumentNotValidException error", ex);
        assert ex != null;
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));

        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorMessage);
        problem.setTitle(ExceptionEnum.VALIDATION_EXCEPTION.name());
        consumePropertiesIfPresent(problem, ex);
        return problem;
    }

    @ExceptionHandler({ValidationException.class})
    public ProblemDetail handleExceptionValidationException(Exception ex){

        String errorMessage = unwrapException(ex);
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorMessage);
        problem.setTitle(ExceptionEnum.VALIDATION_EXCEPTION.name());
        consumePropertiesIfPresent(problem, ex);
        return problem;
    }

    @ExceptionHandler(ConflictException.class)
    public ProblemDetail handleException(ConflictException ex){

        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problem.setTitle(ExceptionEnum.CONFLICT_EXCEPTION.name());
        consumePropertiesIfPresent(problem, ex);
        return problem;
    }

    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleException(BadRequestException ex) {

        log.error("An unexpected error occurred", ex);
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problem.setTitle(ExceptionEnum.BAD_REQUEST_EXCEPTION.name());
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception ex) {

        log.error("An unexpected error occurred", ex);
        String DEFAULT_MESSAGE_ERROR = "There was an error. Please try again later!";
        var problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE_ERROR);
        problem.setTitle(ExceptionEnum.INTERNAL_SERVER_ERROR_EXCEPTION.name());
        return problem;
    }

    private static void consumePropertiesIfPresent(ProblemDetail problemDetail, Exception exception) {

        Optional.ofNullable(exception)
                .filter(PropertiesProducer.class::isInstance)
                .map(PropertiesProducer.class::cast)
                .map(PropertiesProducer::getProperties)
                .filter(MapUtils::isNotEmpty)
                .ifPresent(problemDetail::setProperties);
    }

    private String unwrapException(Throwable t) {
        StringBuilder sb = new StringBuilder();
        unwrapException(sb, t);
        return sb.toString();
    }

    private void unwrapException(StringBuilder sb, Throwable t) {
        if (t == null) {
            return;
        }
        sb.append(t.getMessage());
        if (t.getCause() != null && t != t.getCause()) {
            sb.append('[');
            unwrapException(sb, t.getCause());
            sb.append(']');
        }
    }
}
