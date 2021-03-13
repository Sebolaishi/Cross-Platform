package com.ent.cross.platform.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class BaseExceptionsHandler
{
    private String errorType;
    private String message;

    public static BaseExceptionsHandler of(String errorType, String message) {
        BaseExceptionsHandler baseExceptionsHandler = new BaseExceptionsHandler();
        baseExceptionsHandler.setErrorType( errorType);
        baseExceptionsHandler.setMessage( message);
        return baseExceptionsHandler;
    }
}
