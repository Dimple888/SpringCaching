package com.springCache.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springCache.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book,Integer>{
	
    List<Book> findByName(String name);
	
	List<Book> findAll();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE BOOK U SET U.NAME = :name WHERE U.ID = :id",nativeQuery=true)//A native query is a query written as text in the database's query language (usually SQL).
	int updatebook(@Param("name") String name,@Param("id") Integer id);//update retrns void/integer
	
	
	
	

}
