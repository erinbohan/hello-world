package com.frankc.hellomongo.shortlink;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements interface from RESTful HTTP/JSON to ShortLink Repository.
 *
 * @author Frank Callaly
 */
@RestController
public class ShortLinkController {

    @Autowired
    private ShortLinkRepo shortLinkRepo;

    /**
     * Get a collection of all ShortLinks in the repository.
     *
     * @return a List of ShortLinks
     */
    @GetMapping("/links")
    public List<ShortLink> getAllLinks() {
        return shortLinkRepo.findAll();
    }

    /**
     * Get a single particular ShortLink from the repository.
     *
     * @param id of ShortLink to return, taken from path
     * @return a Resource<ShortLink> corresponding to the given id
     */
    @GetMapping("/links/{id}")
    public Resource<ShortLink> getLink(@PathVariable("id") final String id)
                                            throws LinkNotFoundException {
        Resource<ShortLink> resource;

        try {
            resource = new Resource<ShortLink>(shortLinkRepo.find(id));
        } catch (NoSuchElementException ex) {
            throw new LinkNotFoundException();
        }
        resource.add(linkTo(ShortLinkController.class).slash(id).withSelfRel());
        resource.add(linkTo(methodOn(this.getClass()).getAllLinks())
                            .withRel("all-links"));

        return resource;
    }

    /**
     * Create a new ShortLink and add to the repository.
     *
     * @param newLink ShortLink corresponding to data sent in request body
     * @return a Resource<ShortLink> for the object that has been added
     */
    @PostMapping("/links")
    public Resource<ShortLink> createLink(@RequestBody final ShortLink newLink,
                                          final HttpServletResponse response) {
        ShortLink createdLink = shortLinkRepo.save(newLink);
        response.setStatus(HttpServletResponse.SC_CREATED);

        Resource<ShortLink> resource = new Resource<ShortLink>(createdLink);
        resource.add(linkTo(ShortLinkController.class)
                     .slash(createdLink.getId()).withSelfRel());
        resource.add(linkTo(methodOn(this.getClass())
                     .getAllLinks()).withRel("all-links"));

        return resource;
    }

    /**
     * Redirect to the destLink of a ShortLink.
     *
     * @param id       of link to by redirected to
     * @param response object to set status and header
     * @throws LinkNotFoundException
     */
    @GetMapping("/{id}")
    public void execShortLink(@PathVariable("id") final String id,
                              final HttpServletResponse response)
            throws LinkNotFoundException {
        ShortLink shortLink = shortLinkRepo.find(id);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", shortLink.getSrcLink());
        return;
    }
}
