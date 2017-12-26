package com.javagal.rest.webservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javagal.rest.webservices.domain.Book;
import com.javagal.rest.webservices.domain.Testimonial;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	

	public List<Book> findAllByOrderByTitleAsc();

}
