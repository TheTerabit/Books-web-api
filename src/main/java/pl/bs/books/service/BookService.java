package pl.bs.books.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bs.books.dao.BookDao;
import pl.bs.books.entity.ProcessedBook;


@Service
public class BookService {

    private final BookDao bookDao;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BookService(BookDao bookDao){ this.bookDao=bookDao; }

    public String getAllBooks() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this.bookDao.getAllBooks().values());
    }

    public String getBookById(String id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(this.bookDao.getAllBooks().get(id));
    }

    public String getBooksByCategory(String category) throws JsonProcessingException {
        JSONArray result = new JSONArray();
        for(ProcessedBook processedBook : this.bookDao.getAllBooks().values()){
            if(processedBook.getCategories()!=null)
                if(processedBook.getCategories().contains(category))
                    result.add(processedBook);
        }
        return objectMapper.writeValueAsString(result);
    }

    public String getRating() throws JsonProcessingException {
        return objectMapper.writeValueAsString(this.bookDao.getAuthorsRates().values());
    }

}
