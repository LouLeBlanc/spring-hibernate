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
import edu.brandeis.rseg105.hibernate.domain.Author;
import edu.brandeis.rseg105.hibernate.domain.Book;
import edu.brandeis.rseg105.hibernate.domain.Category;

/**
 * 
 * @author Louis LeBlanc
 *
 * Delete a record from the database
 */
public class DeleteBookWithAuthor {
	private static Logger logger = LoggerFactory.getLogger(DeleteBookWithAuthor.class);

	public static void main(String[] args) {

		GenericApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig.class);

		PublishingDao publishingDao = ctx.getBean(PublishingDao.class);

		Book deleteBook = publishingDao.findBookWithAuthorAndCategoryById(8L);
		logger.info("================================");
		logger.info("Removing book " + deleteBook.getTitle() + ":");

		publishingDao.delete(deleteBook);

		List<Book> books = publishingDao.findAllWithAuthorAndCategory();
		logger.info("================================");
		logger.info("Listing all books with authors and category names:");
		FindBooksAuthorsAndCategories.listBooksWithCategoryAndAuthors(books);
		logger.info("================================");

		ctx.close();
	}
}
