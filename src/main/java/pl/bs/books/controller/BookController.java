package pl.bs.books.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import pl.bs.books.service.BookService;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) { this.bookService = bookService; }

    @RequestMapping(value="/book",method = RequestMethod.GET)
    public String getAllBooks() throws IOException {
        return bookService.getAllBooks();
    }

    @RequestMapping(value="/book/{id}",method=RequestMethod.GET)
    public String getBookById(@PathVariable("id") String id) throws JsonProcessingException {

        if (bookService.getBookById(id)==null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return bookService.getBookById(id);
    }

    @RequestMapping(value="/category/{categoryName}/books",method=RequestMethod.GET)
    public String getBookByCategory(@PathVariable("categoryName") String category) throws JsonProcessingException {
        return bookService.getBooksByCategory(category);
    }

    @RequestMapping(value="/rating",method = RequestMethod.GET)
    public String getRating() throws JsonProcessingException {
        return bookService.getRating();
    }

}
