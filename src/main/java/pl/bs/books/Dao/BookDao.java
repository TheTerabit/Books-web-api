package pl.bs.books.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import pl.bs.books.entity.Author;
import pl.bs.books.entity.Book;
import pl.bs.books.entity.JsonResponse;
import pl.bs.books.entity.ProcessedBook;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Repository
public class BookDao {

    private final Map<String, ProcessedBook> allBooks = new HashMap();
    private final Map<String, Author> authorsRates = new HashMap();

    public BookDao() throws IOException, java.text.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\books.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JsonResponse jsonResponse = new ObjectMapper().readValue(jsonObject.toString(), JsonResponse.class);

        processBooks(jsonResponse);
        processAuthors(jsonResponse);
    }

    private void processBooks(JsonResponse jsonResponse) throws java.text.ParseException {
        for (Book book:jsonResponse.getBooks()) {
            ProcessedBook processedBook = new ProcessedBook(book);
            allBooks.put(processedBook.getIsbn(), processedBook);
        }
    }

    private void processAuthors(JsonResponse jsonResponse){

        for(Book book : jsonResponse.getBooks()){
            Double averageRating = book.getVolumeInfo().getAverageRating();
            if(averageRating!=null)
                for(String author : book.getVolumeInfo().getAuthors()){
                    if (authorsRates.containsKey(author)) {
                        Author a = authorsRates.get(author);
                        a.addBook(averageRating);
                        authorsRates.replace(author, a);
                    } else
                    authorsRates.put(author,  new Author(author, averageRating));
                }
        }
    }

    public Map<String, ProcessedBook> getAllBooks(){
        return allBooks;
    }

    public Map<String, Author> getAuthorsRates(){
        return authorsRates;
    }

}
