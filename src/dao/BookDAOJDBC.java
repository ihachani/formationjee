package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import models.Book;

public class BookDAOJDBC implements BookDAO {
	private Statement statement;
	private Connection connector;

	public BookDAOJDBC() throws ClassNotFoundException, SQLException {
		loadMysqlJDBCDriver();
		Map<String, String> connectionParams = createConnectionParamatersFromPropertiesFile();
		initializeConnection(connectionParams);
		createStatement();
	}

	private void loadMysqlJDBCDriver() throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	}

	private Map<String, String> createConnectionParamatersFromPropertiesFile() {
		Properties connectionProperties = new Properties();
		try {
			connectionProperties.load(getClass().getClassLoader().getResourceAsStream("mysqlconfig.properties"));
		} catch (IOException e) {
			System.err.println("Can't find properties file");
			e.printStackTrace();
		}
		Map<String, String> connectionParams = new HashMap<>();
		connectionParams.put("host", connectionProperties.getProperty("host"));
		connectionParams.put("database", connectionProperties.getProperty("database"));
		connectionParams.put("user", connectionProperties.getProperty("user"));
		connectionParams.put("password", connectionProperties.getProperty("password"));
		return connectionParams;
	}

	private void initializeConnection(Map<String, String> connectionParams) throws SQLException {
		connector = DriverManager.getConnection(connectionParams.get("host") + connectionParams.get("database"),
				connectionParams.get("user"), connectionParams.get("password"));
	}

	private void createStatement() throws SQLException {
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
