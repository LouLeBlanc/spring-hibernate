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
 * @author Louis LeBlanc
 * 
 * Find all books with authors and categories.
 *
 */
public class FindBooksAuthorsAndCategories {
	private static Logger logger = LoggerFactory.getLogger(FindBooksAuthorsAndCategories.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig.class);

		PublishingDao publishingDao = ctx.getBean(PublishingDao.class);

		List<Book> books = publishingDao.findAllWithAuthorAndCategory();
		logger.info("================================");
		logger.info("Listing all books with authors and category names: ");
		listBooksWithCategoryAndAuthors(books);
		logger.info("================================");

		ctx.close();
	}
	
	public static void listBooksWithCategoryAndAuthors(List<Book> books) {

		books.forEach(b -> {
			logger.info(b.toString());
			logger.info(b.getCategory().toString());
				b.getAuthors().forEach(a -> logger.info(a.toString()));
				logger.info("-----------------");
		});
	}
}
