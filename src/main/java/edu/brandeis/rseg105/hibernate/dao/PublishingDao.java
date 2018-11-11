/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.hibernate.dao;

import java.util.List;

import edu.brandeis.rseg105.hibernate.domain.Book;

public interface PublishingDao {
	
	List<Book> findAll();
	
	List<Book> findAllWithAuthorAndCategory();
	
	Book findBookWithAuthorAndCategoryById(Long id);

	Book save(Book book);
	
	void delete(Book book);
}
