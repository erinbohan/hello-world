package com.frankc.hellomongo.apikey;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to return HTTP 404 when api key is not found.
 *
 * @author Frank Callaly
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "key not found")
public class KeyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -8746864239692402612L;
}
