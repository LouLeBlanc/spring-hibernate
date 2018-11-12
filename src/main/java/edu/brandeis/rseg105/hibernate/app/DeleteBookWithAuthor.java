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
		Author newAuthor = new Author();
		newAuthor.setId(9L);
		newAuthor.setFirstName("Joshua");
		newAuthor.setLastName("Bloch");
		newAuthor.setDescription("Professor, Carnegie Mellon University");

		Book newBook = new Book();
		newBook.setId(8L);
		newBook.setIsbn("9780134685997");
		newBook.setTitle("Effective Java");
		newBook.setPrice((float) 54.99);
		newBook.addAuthor(newAuthor);


		GenericApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig.class);

		PublishingDao publishingDao = ctx.getBean(PublishingDao.class);

		logger.info("================================");
		logger.info("Removing book " + newBook.getTitle() + ":");
		publishingDao.delete(newBook);

        List<Book> books = publishingDao.findAllWithAuthorAndCategory();
        logger.info("================================");
        logger.info("Listing all books with authors and category names:");
        FindBooksAuthorsAndCategories.listBooksWithCategoryAndAuthors(books);
        logger.info("================================");

        ctx.close();
	}

}
