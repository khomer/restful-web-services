package com.javagal.rest.webservices.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.javagal.rest.webservices.domain.Testimonial;
import com.javagal.rest.webservices.exception.TestimonialNotFoundException;
import com.javagal.rest.webservices.repository.TestimonialRepository;


@RestController
public class TestimonialResource {
	
	@Autowired
	TestimonialRepository testimonialRepository;
	
	@GetMapping("/rest/testimonials")
	List<Testimonial> retrieveAllTestimonials() {
		return testimonialRepository.findAllByOrderByCustomerNameAsc();
	}
	
	@GetMapping("/rest/testimonials/{id}")
	public Resource<Testimonial> retrieveTestimonial (@PathVariable int id) {
		Optional<Testimonial> testimonial = testimonialRepository.findById(id);

		if (!testimonial.isPresent())
			throw new TestimonialNotFoundException("Sorry, testimonial id " + id + " is not found!");

		// Return all recommended books
		Resource<Testimonial> resource = new Resource<Testimonial>(testimonial.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllTestimonials());

		resource.add(linkTo.withRel("A list of testimonials"));

		return resource;
	}

}
