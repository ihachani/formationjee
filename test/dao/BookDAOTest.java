package dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import models.Book;

public class BookDAOTest {
	static BookDAO bookDAO;

	@BeforeClass
	public static void setupClass() {
		try {
			bookDAO = new BookDAOJDBC();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		Book book = new Book();
		book.setName("Book1");
		book.setAuthor("Author1");
		try {
			bookDAO.create(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRetrieveAll() {
		Book book = new Book();
		book.setName("Book2");
		book.setAuthor("Author2");
		try {
			bookDAO.create(book);
			List<Book> books = bookDAO.retrieveAllBooks();
			System.out.println(books);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testRetrievebBook() {
		try {
			Book book = bookDAO.retrieve(1);
			Assert.assertEquals(book.getId(), book.getId());
			Assert.assertTrue(!book.getName().isEmpty());
			Assert.assertTrue(!book.getAuthor().isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testRetrievebBookNotFound() {
		try {
			Book book = bookDAO.retrieve(999);
			Assert.assertNull(book);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
