package com.frankc.hellomongo.shortlink;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implements interface from HTTP/JSON to ShortLink Repository.
 *
 * @author frankcallaly@gmail.com
 */
@RestController
public class ShortLinkController {

	@Autowired
	private ShortLinkRepo shortLinkRepo;

	/**
	 * Get a collection of all ShortLinks in the repository.
	 *
	 * @return Collection of ShortLinks
	 */
	@GetMapping("/links")
	public List<ShortLink> getAllLinks() {
		return shortLinkRepo.getAll();
	}

	/**
	 * Get a single particular ShortLink from the repository
	 *
	 * @param id of ShortLink to return, taken from path
	 * @return ShortLink corresponding to the given id
	 */
	@GetMapping("/links/{id}")
	public Resource<ShortLink> getLink(@PathVariable("id") String id) throws LinkNotFoundException {
		Resource<ShortLink> resource = new Resource<ShortLink>(shortLinkRepo.get(id));
		resource.add(linkTo(ShortLinkController.class).slash(id).withSelfRel());
		resource.add(linkTo(methodOn(this.getClass()).getAllLinks()).withRel("all-links"));

		return resource;
	}

	/**
	 * Create a new ShortLink and add to the repository
	 *
	 * @param newLink ShortLink object given in request body
	 * @return ShortLink object that has been added
	 */
	@PostMapping("/links")
	public Resource<ShortLink> createLink(@RequestBody ShortLink newLink,
			                              HttpServletResponse response) {
		shortLinkRepo.add(newLink);
		response.setStatus(HttpServletResponse.SC_CREATED);

		Resource<ShortLink> resource = new Resource<ShortLink>(newLink);
		resource.add(linkTo(ShortLinkController.class).slash(newLink.getId()).withSelfRel());
		resource.add(linkTo(methodOn(this.getClass()).getAllLinks()).withRel("all-links"));

		return resource;
	}

	/**
	 * Redirect to the destLink of a ShortLink
	 * 
	 * @param id
	 * @param response
	 * @throws LinkNotFoundException
	 */
	@GetMapping("/{id}")
	public void execShortLink(@PathVariable("id") String id,
			                  HttpServletResponse response) throws LinkNotFoundException {
		ShortLink shortLink = shortLinkRepo.get(id);
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", shortLink.getSrcLink());
		return;
	}
}