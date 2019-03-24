package pl.bs.books.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.bs.books.Entity.Book;
import pl.bs.books.Service.BookService;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value="/book",method = RequestMethod.GET)
    public JSONArray getAllStudents(){
        return bookService.getAllBooks();
    }

    @RequestMapping(value="/book/{id}",method=RequestMethod.GET)
    public Book getBookById(@PathVariable("id") String id){
        return bookService.getBookById(id);
    }


    @RequestMapping(value="/category/{categoryName}/books",method=RequestMethod.GET)
    public JSONArray getBookByCategory(@PathVariable("categoryName") String category){
        return bookService.getBooksByCategory(category);
    }

    @RequestMapping(value="/rating",method = RequestMethod.GET)
    public JSONArray getRating(){
        return bookService.getRating();
    }
}
