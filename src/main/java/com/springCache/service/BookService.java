package com.springCache.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springCache.entity.Book;
import com.springCache.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookService {

	@Autowired
	BookRepository bookRespository;

	@Cacheable(cacheNames = "books", key = "#id")
	public Book getBookById(Integer id) {
		log.info("fetch from db"); //if there's no entry in db we will get java.util.NoSuchElementException 
		return bookRespository.findById(id).isPresent() ? bookRespository.findById(id).get() : new Book();

	}

	public Book saveBook(Book book) {

		return bookRespository.save(book);

	}

	@CachePut(cacheNames = "books", key = "#book.id") // update the value to cache as well along with db so when
														// cacheable get is called it will get from cache with crrct
														// information
	public Book updateBook(Book book, Integer id) {
		log.info("update into db");
		//Book updatebook = bookRespository.findById(id).get();

//		if (Objects.nonNull(book.getAuthorName()) && !"".equalsIgnoreCase(book.getAuthorName())) {
//			updatebook.setAuthorName(book.getAuthorName());
//		}
//
//		if (Objects.nonNull(book.getName()) && !"".equalsIgnoreCase(book.getName())) {
//			updatebook.setName(book.getName());
//		}-with save method of repo you can manually writes this logic to update
		bookRespository.updatebook(book.getName(), id);
		return book;
	}

	@CacheEvict(cacheNames = "books", key = "#id")
	public void deleteBook(Integer id) {
		log.info("delete from db");
		bookRespository.deleteById(id);
	}

}
