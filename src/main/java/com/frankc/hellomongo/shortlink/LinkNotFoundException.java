package com.frankc.hellomongo.shortlink;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "link not found")
public class LinkNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 8784771960248183750L;
}
