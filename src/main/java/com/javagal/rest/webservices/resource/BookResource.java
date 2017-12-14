package com.javagal.rest.webservices.resource;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.javagal.rest.webservices.domain.Book;
import com.javagal.rest.webservices.exception.BookNotFoundException;
import com.javagal.rest.webservices.repository.BookRepository;

@RestController
public class BookResource {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/rest/books")
	List<Book> retrieveAllBooks() {
		return bookRepository.findAll();
	}
	
	@GetMapping("/rest/books/{id}")
	public Resource<Book> retrieveBook(@PathVariable int id) {
		Optional<Book> book = bookRepository.findById(id);

		if (!book.isPresent())
			throw new BookNotFoundException("Sorry, book id " + id + " is not found!");

		// Return all recommended books
		Resource<Book> resource = new Resource<Book>(book.get());

		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllBooks());

		resource.add(linkTo.withRel("A list of recommended books by Dr. Allyn Krieger-Fiedler"));

		return resource;
	}
	
	@PostMapping("/rest/books")
	public ResponseEntity<Object> createBook(@Valid @RequestBody Book book) {
		Book savedBook = bookRepository.save(book);
		
		// Return a newly created book uri along with 'CREATED' staus code
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedBook.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/rest/books/{id}")
	public void deleteBook(@PathVariable int id) {
		bookRepository.deleteById(id);
	}
}
