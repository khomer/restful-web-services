package com.javagal.rest.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javagal.rest.webservices.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
