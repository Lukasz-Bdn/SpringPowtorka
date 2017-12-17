package lukasz.spring.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lukasz.spring.model.Book;

@Component
public	class	MemoryBookService	{
	private	List<Book>	list;
	public	MemoryBookService()	{
		list = new	ArrayList<>();
		list.add(new Book(1L, "9788324631766", "Thiniking in Java", "Bruce Eckel",
			"Helion", "programming"));
		list.add(new Book(2L, "9788324627738", "Rusz glowa Java.",
			"Sierra	Kathy, Bates Bert", "Helion", "programming"));
		list.add(new Book(3L, "9780130819338", "Java 2.	Podstawy",
			"Cay Horstmann,	Gary Cornell", "Helion", "programming"));
		list.add(new Book(3L, "645645619338", "Mongo Db",
				"John Johnson", "Helion", "programming"));
		list.add(new Book(3L, "3481130844444", "Java script dla idiotow",
				"Gary Cornell", "Helion", "programming"));
	}
	public List<Book> getList() {return	list;}
	public void setList(List<Book> list) {
		this.list = list;
	}
	
	public Book getBookById(long id) {
		for(Book book : this.list) {
			if(book.getId()==id) {
				return book;
			}
		}
		return null;
	}
	
	public void addBook(Book book) {
		this.list.add(book);
	}
	
	public void removeBook(Book book) {
		list.remove(book);
	}
	public long getNextId() {
		long maxId = 0L;
		for (Book book : this.list) {
			if(book.getId()>maxId) {
				maxId = book.getId();
			}
		}
		return maxId+1;
	}
}