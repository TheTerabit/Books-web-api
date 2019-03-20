package pl.bs.books.Service;

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

    public Book getBookById(String id) {
        return this.bookDao.getBookById(id);
    }
}
