package com.ent.cross.platform.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseExceptionsResponse
{
    private String errorType;
    private String message;

    /**
     * Builds exception response body or message
     * @param errorType http status
     * @param message exception details
     * @return
     */
    public static BaseExceptionsResponse of(String errorType, String message) {
        BaseExceptionsResponse baseExceptionsResponse = new BaseExceptionsResponse();
        baseExceptionsResponse.setErrorType( errorType);
        baseExceptionsResponse.setMessage( message);
        return baseExceptionsResponse;
    }
}
