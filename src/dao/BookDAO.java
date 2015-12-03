package dao;

import java.sql.SQLException;
import java.util.List;

import models.Book;

public interface BookDAO {
	void create(Book book) throws SQLException;

	Book retrieve(int id) throws SQLException;

	List<Book> retrieveAllBooks() throws SQLException;
}
