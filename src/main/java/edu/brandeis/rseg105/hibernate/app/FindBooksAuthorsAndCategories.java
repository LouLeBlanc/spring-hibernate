/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.hibernate.app;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import edu.brandeis.rseg105.hibernate.config.AppConfig;
import edu.brandeis.rseg105.hibernate.dao.PublishingDao;
import edu.brandeis.rseg105.hibernate.domain.Author;
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

        logger.info("================================");
        logger.info("Listing books with authors and category names: ");

        List<Book> books = publishingDao.findAllWithAuthorAndCategory();
        logger.info("---------------- Listing Books Start ----------------");
        books.forEach(book -> {
        	logger.info(book.toString());
        	logger.info(book.getCategory().toString());
        	Set<Author> authors = book.getAuthors();
        	authors.forEach(author -> {
        		logger.info(author.toString());
        	});
			logger.info("-----------------");
        });
        logger.info("----------------- Listing Books End -----------------");

        ctx.close();

        logger.info("================================");

	}

}
