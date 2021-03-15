package com.ent.cross.platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Empty Directory")
public class NoContentExceptionHandler extends RuntimeException {}
