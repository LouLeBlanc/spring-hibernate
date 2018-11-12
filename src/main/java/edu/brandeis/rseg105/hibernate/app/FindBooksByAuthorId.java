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
 * Find all Books and Categories by Author ID.
 */
public class FindBooksByAuthorId {
	private static Logger logger = LoggerFactory.getLogger(FindBooksByAuthorId.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long authorId = 7L;

        GenericApplicationContext ctx =
        		new AnnotationConfigApplicationContext(AppConfig.class);

        PublishingDao publishingDao = ctx.getBean(PublishingDao.class); 

        List<Book> books = publishingDao.findBooksByAuthorId(authorId);

        logger.info("================================");
        logger.info("Listing books by author id " + authorId + ": ");
        FindBooksAuthorsAndCategories.listBooksWithCategoryAndAuthors(books);
        logger.info("================================");

	}

}
