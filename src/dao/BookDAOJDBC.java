package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Book;

public class BookDAOJDBC implements BookDAO {
	private Statement statement;
	// private ResultSet rs;
	private Connection connector;

	public BookDAOJDBC() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		connector = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "formation", "formation");
		statement = connector.createStatement();
	}

	@Override
	public void create(Book book) throws SQLException {
		String query = "INSERT INTO Books (name,author)" + "VALUES (?,?)";
		PreparedStatement preparedStatement = createInsertPreparedStatement(book, query);
		preparedStatement.executeUpdate();
	}

	private PreparedStatement createInsertPreparedStatement(Book book, String query) throws SQLException {
		PreparedStatement preparedStatement = connector.prepareStatement(query);
		preparedStatement.setString(1, book.getName());
		preparedStatement.setString(2, book.getAuthor());
		return preparedStatement;
	}

	public List<Book> retrieveAllBooks() throws SQLException {
		ResultSet result = createRetrieveAllBooksResultSet();

		int count = 0;
		List<Book> books = new ArrayList<>();
		while (result.next()) {
			Book book = createBookFromResultSet(result);

			String output = "Book #%d: %s - %s";
			System.out.println(String.format(output, ++count, book.getName(), book.getAuthor()));

			books.add(book);
		}
		return books;
	}

	private ResultSet createRetrieveAllBooksResultSet() throws SQLException {
		String sql = "SELECT * FROM Books";

		ResultSet result = statement.executeQuery(sql);
		return result;
	}

	private Book createBookFromResultSet(ResultSet result) throws SQLException {
		int id = result.getInt(1);
		String name = result.getString("Name");
		String author = result.getString("Author");
		Book book = new Book();
		book.setId(id);
		book.setName(name);
		book.setAuthor(author);
		return book;
	}

	@Override
	public Book retrieve(int id) throws SQLException {
		String query = "SELECT * FROM Books where idBooks = ?";

		PreparedStatement preparedStatement = connector.prepareStatement(query);
		preparedStatement.setInt(1, id);
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			Book book = createBookFromResultSet(result);

			String output = "Book %d %s - %s";
			System.out.println(String.format(output, book.getId(), book.getName(), book.getAuthor()));

			return book;
		}
		return null;
	}

}
