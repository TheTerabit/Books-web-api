package pl.bs.books.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bs.books.Entity.Book;
import pl.bs.books.Service.BookService;

import java.util.Collection;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllStudents(){
        return bookService.getAllBooks();
    }

    @RequestMapping(value="/{category}",method=RequestMethod.GET)
    public String getBookById(@PathVariable("category") String category){
        return bookService.getBookById(category);
    }
}
