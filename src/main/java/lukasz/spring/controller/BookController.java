package lukasz.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lukasz.spring.bean.MemoryBookService;
import lukasz.spring.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private MemoryBookService mbs;
	
	@RequestMapping("hello")
	public Book hello() {
		System.out.println("Kliknieto w p");
		return	new	Book(1L,"9788324631766","Thiniking	in	Java",
				"Bruce	Eckel","Helion","programming");
	}
	
	@GetMapping("")
	public List<Book> getBooks() {
		return mbs.getList();
	}
	
	@GetMapping("/{id}")
	public Book getBookDetail(@PathVariable long id) {
		Book book = mbs.getBookById(id);
		return book;
	}
	
	@DeleteMapping("remove/{id}")
	public void removeBook(@PathVariable long id) {
		Book book = mbs.getBookById(id);
		mbs.removeBook(book);
	}
	
	@PostMapping("/add")
	public void postForm(@RequestBody Book book) {
		long nextId = mbs.getNextId();
		book.setId(nextId);
		mbs.addBook(book);
	}
}