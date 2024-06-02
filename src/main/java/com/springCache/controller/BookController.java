package com.springCache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springCache.entity.Book;
import com.springCache.service.BookService;

@RestController
@RequestMapping("/v1")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getAllBooks(@PathVariable("id") Integer bookId) {

		return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<Book> saveBook(@RequestBody Book book) {

		return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);

	}

	@PutMapping("/update/book/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") Integer bookId) {

		return new ResponseEntity<>(bookService.updateBook(book, bookId), HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") Integer bookId) {

		bookService.deleteBook(bookId);

		return new ResponseEntity<>("Deleted succesfully", HttpStatus.OK);

	}
}
