package models;

public class Book {
	private int id;
	private String author;
	private String name;

	public int getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Book id=" + id + ", author=" + author + ", name=" + name;
	}
}
