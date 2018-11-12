/**
 * @author Louis LeBlanc
 * For Homework Assignment 1
 * Expert Software Development in Java
 * Brandeis University
 * Instructed by Vitaly Yurik
 */

package edu.brandeis.rseg105.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="book")
@NamedQueries({
	@NamedQuery(name=Book.FIND_BOOK_WITH_AUTHOR_CATEGORY_BY_ID,
			query="select distinct b from Book b " +
				"left join fetch b.category c " +
				"left join fetch b.authors a " +
				"where b.id = :id"),
	@NamedQuery(name=Book.FIND_BOOKS_BY_AUTHOR_ID,
			query="select distinct b from Book b " +
				"left join fetch b.category c " +
				"left join fetch b.authors a " +
				"where a.id = :id"),
	@NamedQuery(name=Book.FIND_ALL_WITH_AUTHOR_CATEGORY,
			query="select distinct b from Book b " +
				"left join fetch b.category c " +
				"left join fetch b.authors a"),
	@NamedQuery(name=Book.FIND_ALL_BOOKS,
			query="select distinct b from Book b ")
})
/**
 * 
 * Book Entity Class.
 * The id and version fields are defined by the superclass.
 *
 */
public class Book extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_BOOK_WITH_AUTHOR_CATEGORY_BY_ID =
			"Book.findBookWithAuthorCategoryById";
	public static final String FIND_BOOKS_BY_AUTHOR_ID =
			"Book.findBooksByAuthorId";
	public static final String FIND_ALL_WITH_AUTHOR_CATEGORY =
			"Book.findAllWithAuthorCategory";
	public static final String FIND_ALL_BOOKS =
			"Book.FindAllBooks";

	// Book ID is inherited from AbstractEntity, as is version.
	
	/*
	@Column(name="category_id")
	private long   category_id;
	*/

	@Column(name="isbn")
	private String isbn;

	@Column(name="title")
	private String title;
	
	@Column(name="price")
	private float  price;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToMany
	@JoinTable(name = "author_book",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors = new HashSet<>();
	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @return the authors
	 */
	public Set<Author> getAuthors() {
		return authors;
	}
	
	/**
	 * @return the category name
	 */
	public String getCategoryName() {
		return category.getName();
	}
	
	/**
	 * @return the category_id
	 */
	/*
	public long getCategory_id() {
		return category_id;
	}
	*/

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	/**
	 * @param author
	 * @return true / false from Set.add() method.
	 */
	public boolean addAuthor(Author author) {
		return authors.add(author);
	}

	/*
	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}
	*/

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Book - Id: %d, Category: %s, ISBN: %s, Title: %s, Price: %f",
				this.id, this.category.getName(), this.isbn, this.title, this.price);
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Book book = (Book) o;
		if (isbn != null ? !isbn.equals(book.isbn) : book.title != null )
			return false;
		return title != null ? !title.equals(book.title) : book.title != null;
	}
	
	@Override public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
		return result;
	}

}