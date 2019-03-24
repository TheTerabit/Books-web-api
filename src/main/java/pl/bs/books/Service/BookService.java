package pl.bs.books.Service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bs.books.Dao.BookDao;
import pl.bs.books.Entity.Book;

import java.util.Collection;
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public JSONArray getAllBooks(){
        return this.bookDao.getAllBooks();
    }

    public Book getBookById(String id) {
        return this.bookDao.getBookById(id);
    }

    public JSONArray getBooksByCategory(String category) {
        return this.bookDao.getBooksByCategory(category);
    }

    public JSONArray getRating() {
        return this.bookDao.getRating();
    }
}
