package com.ent.cross.platform.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class CrossPlatformError
{
    private String errorHeader;
    private String errorDetail;

    public static CrossPlatformError of( String errorHeader, String errorDetail) {
        CrossPlatformError crossPlatformError = new CrossPlatformError();
        crossPlatformError.setErrorHeader( errorHeader);
        crossPlatformError.setErrorDetail( errorDetail);
        return crossPlatformError;
    }
}
