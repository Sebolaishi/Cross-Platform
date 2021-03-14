package com.ent.cross.platform.exceptions;

import com.ent.cross.platform.contants.ExceptionMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.webjars.NotFoundException;

import java.nio.file.NotDirectoryException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler implements ExceptionMessages {

    /**
     * Exception Handler responsible for service illegal exceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class, NotDirectoryException.class})
    protected ResponseEntity<Object> handleConflict( Exception exception, WebRequest request) {
        BaseExceptionsHandler error = BaseExceptionsHandler.of(HttpStatus.NOT_FOUND.name(), DIRECTORY_NOT_FOUND);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(exception, error, headers, HttpStatus.NOT_FOUND, request);
    }

    /**
     * Exception Handler responsible for service not found exceptions
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> notFoundExceptions( Exception exception, WebRequest request) {
        BaseExceptionsHandler error = BaseExceptionsHandler.of(HttpStatus.NO_CONTENT.name(), NO_CONTENT);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(exception, error, headers, HttpStatus.NO_CONTENT, request);
    }

}
