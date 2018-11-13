/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.hibernate.app;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import edu.brandeis.rseg105.hibernate.config.AppConfig;
import edu.brandeis.rseg105.hibernate.dao.PublishingDao;
import edu.brandeis.rseg105.hibernate.domain.Book;

/**
 * 
 * @author Louis LeBlanc
 *
 * Delete a record from the database
 */
public class DeleteBookWithAuthor {
	private static Logger logger = LoggerFactory.getLogger(DeleteBookWithAuthor.class);

	public static void main(String[] args) {
		/*
		 * This ID will only be valid the first time this application is run.
		 * The CreateBookWithAuthor app can be run again, but it will increment
		 * the ID given to the new record.
		 *
		 */
		Long bookId = 8L;

		GenericApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig.class);

		PublishingDao publishingDao = ctx.getBean(PublishingDao.class);

		Book deleteBook = publishingDao.findBookWithAuthorAndCategoryById(8L);
		logger.info("================================");

		if (deleteBook != null) {
			logger.info("Removing book Id " + deleteBook.getId() +
				       " (" + deleteBook.getTitle() + "):");
			publishingDao.delete(deleteBook);
		}

		List<Book> books = publishingDao.findAllWithAuthorAndCategory();
		logger.info("================================");
		logger.info("Listing all books with authors and category names:");
		FindBooksAuthorsAndCategories.listBooksWithCategoryAndAuthors(books);
		logger.info("================================");

		ctx.close();
	}
}
