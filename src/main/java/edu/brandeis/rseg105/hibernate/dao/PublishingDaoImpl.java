/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */
package edu.brandeis.rseg105.hibernate.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.brandeis.rseg105.hibernate.domain.Book;

@SuppressWarnings("unchecked")
@Transactional
@Repository("publishingDao")
public class PublishingDaoImpl implements PublishingDao {
	
	private static Logger logger = LoggerFactory.getLogger(PublishingDaoImpl.class);
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Book b").list();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findAllWithAuthorAndCategory() {
		return sessionFactory.getCurrentSession().
				getNamedQuery("Book.findAllWithAuthorCategory").list();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> findBooksByAuthorId(Long id) {
		return sessionFactory.getCurrentSession().
				getNamedQuery("Book.findBooksByAuthorId").
				setParameter("id", id).list();
	}

	@Override
	@Transactional(readOnly = true)
	public Book findBookWithAuthorAndCategoryById(Long id) {
		return (Book) sessionFactory.getCurrentSession().
				getNamedQuery("Book.findBookWithAuthorCategoryById").
				setParameter("id", id).uniqueResult();
	}

	@Override
	public Book save(Book book) {
		sessionFactory.getCurrentSession().saveOrUpdate(book);
		logger.info("Book saved with id: " + book.getId());
		return book;
	}

	@Override
	public void delete(Book book) {
		sessionFactory.getCurrentSession().delete(book);
		logger.info("Book deleted with id: " + book.getId());
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
