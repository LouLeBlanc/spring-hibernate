/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.hibernate.app;

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
 * Find a book with authors and category by Book ID.
 */
public class FindBookWithAuthorsCategoryById {
	private static Logger logger = LoggerFactory.getLogger(FindBookWithAuthorsCategoryById.class);

	public static void main(String[] args) {
		Long bookID = 9L;
        logger.info("================================");
        logger.info("Listing book with authors and category by book's ID: ");

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        PublishingDao publishingDao = ctx.getBean("publishingDao", PublishingDao.class); 

        Book book = publishingDao.findBookWithAuthorAndCategoryById(bookID);
        	logger.info(book.toString());
        	logger.info(book.getCategory().toString());
        	Set<Author> authors = book.getAuthors();
        	authors.forEach(author -> {
        		logger.info(author.toString());
        	});

        ctx.close();

        logger.info("================================");

	}
}
