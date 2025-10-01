package com.jiksoft.library.exceptions;

import java.io.Serial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class BaseException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    private String message;

    protected BaseException(String message, Throwable err) {
        super(message, err);
        this.message = message;
    }

    protected BaseException(String message) {
        this.message = message;
    }

}

