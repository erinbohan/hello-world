package com.frankc.hellomongo.shorturl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Short URL Not Found")
public class ShortUrlNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8784771960248183750L;
}
