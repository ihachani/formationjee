package services;

import java.util.List;

import models.Book;

public interface IBookService {

	/**
	 * 
	 * @param name
	 * @param author
	 * @throws eception when name is empty.
	 */
	void addBook(String name, String author);

	Book retrieveBook(int id);
	
	List<Book> retrieveAll();

}