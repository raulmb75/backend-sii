package com.sii.aspirantes.aspirantes.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityDataNotFoundException extends Exception {
    public EntityDataNotFoundException() {
        super();
    }

    public EntityDataNotFoundException(String message) {
        super(message);
    }

    public EntityDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityDataNotFoundException(Throwable cause) {
        super(cause);
    }

    protected EntityDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
