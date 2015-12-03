package services;

import java.sql.SQLException;
import java.util.List;

import dao.BookDAO;
import dao.BookDAOJDBC;
import models.Book;

public class BookService implements IBookService {
	BookDAO bookDAO;

	public BookService() {
		try {
			bookDAO = new BookDAOJDBC();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.IBookService#addBook(java.lang.String, java.lang.String)
	 */
	@Override
	public void addBook(String name, String author) {
		Book book = createBook(name, author);

		try {
			bookDAO.create(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Book createBook(String name, String author) {
		Book book = new Book();

		if (name == null && name.isEmpty())
			throw new RuntimeException("Name is empty");

		book.setName(name);
		book.setAuthor(author);
		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see services.IBookService#retrieveBook(int)
	 */
	@Override
	public Book retrieveBook(int id) {
		Book book = null;
		try {
			book = bookDAO.retrieve(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (book == null) {
			book = new Book();
			book.setId(-1);
			book.setName("Not Found");
			book.setAuthor("Not Found");
		}
		return book;
	}

	@Override
	public List<Book> retrieveAll() {
		// TODO Auto-generated method stub
		try {
			return bookDAO.retrieveAllBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
