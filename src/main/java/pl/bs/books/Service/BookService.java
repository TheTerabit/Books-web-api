package pl.bs.books.Service;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bs.books.Dao.BookDao;
import pl.bs.books.Entity.Book;

import java.util.Collection;
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public String getAllBooks(){
        return this.bookDao.getAllBooks();
    }

    public String getBookById(String id) {
        return this.bookDao.getBookById(id);
    }

    public JSONArray getBooksByCategory(String category) {
        return this.bookDao.getBooksByCategory(category);
    }
}
