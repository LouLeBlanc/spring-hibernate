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
 *	Find all books - not authors or categories.
 */
public class FindAllBooksApp {
	private static Logger logger = LoggerFactory.getLogger(FindAllBooksApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		GenericApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig.class);

		PublishingDao publishingDao = ctx.getBean(PublishingDao.class); 

		List<Book> books = publishingDao.findAll();

		logger.info("================================");
		logger.info("Listing books without authors and category names: ");
		books.forEach(book -> {
			logger.info(book.toString());
				logger.info("-----------------");
			});
		logger.info("================================");

		ctx.close();
	}
}
