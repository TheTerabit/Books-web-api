package pl.bs.books.Dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import pl.bs.books.Entity.Author;
import pl.bs.books.Entity.Book;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@Repository
public class BookDao {

    public BookDao(){
        insertBooks();
    }
    private static Map<String, Book> books;
    private static Map<String, Author> authorsRates;

    private void insertBooks(){
        books = new HashMap<String, Book>() ;
        authorsRates = new HashMap<String, Author>() ;
                        JSONParser parser = new JSONParser();
                        Gson gson = new Gson();
                        Object object = null;
                        try {
                            object = parser.parse(new FileReader("C:\\Users\\Bartek\\IntelliJ\\books\\src\\main\\resources\\static\\books.json"));


                            //convert Object to JSONObject
                            JSONObject jsonObject = (JSONObject)object;

                            //Reading the String
                            //String name = (String) jsonObject.get("Name");
                            //Long age = (Long) jsonObject.get("Age");

                            //Reading the array
                            JSONArray items = (JSONArray)jsonObject.get("items");
                            for (int i = 0; i < items.size(); i++) {
                                JSONObject x= (JSONObject) items.get(i);
                                JSONObject j = (JSONObject) x.get("volumeInfo");
                                //JSONObject c = items.getJSONObject(i);
                                String isbn = "";
                                JSONObject imageLinks=(JSONObject) j.get("imageLinks");
                                boolean check=true;
                                try {
                                    org.json.simple.JSONArray industryIdentifiers = (org.json.simple.JSONArray) j.get("industryIdentifiers");
                                    for (int k = 0; k < industryIdentifiers.size(); k++) {
                                        JSONObject y= (JSONObject) industryIdentifiers.get(k);
                                        if((y.get("type").toString().equals("ISBN_13"))) {
                                            isbn = y.get("identifier").toString();
                                            check =false;
                                            break;
                                        }
                                        else if(check)
                                            isbn=x.get("id").toString();



                                    }
                                }catch(NullPointerException e){
                                    isbn=x.get("id").toString();
                                }


                                String title;
                                String subtitle;
                                String publisher;
                                String publishedDate;
                                String description;
                                int pageCount;
                                String thumbnailUrl;
                                String language;
                                String previewLink;
                                double avarageRating;
                                String[] authors;
                                String[] categories;



                                //isbn="isbn"+i;
                                try {
                                    title = j.get("title").toString();//
                                }catch(NullPointerException e){
                                    title =null;
                                }
                                try{
                                    subtitle=j.get("subtitle").toString();//
                                }catch(NullPointerException e){
                                    subtitle =null;
                                }
                                try{
                                    publisher=j.get("publisher").toString();//
                                }catch(NullPointerException e){
                                    publisher =null;
                                }
                                try{
                                    publishedDate=j.get("publishedDate").toString();//
                                }catch(NullPointerException e){
                                    publishedDate=null;
                                }
                                try{
                                    description=j.get("description").toString();//
                                }catch(NullPointerException e){
                                    description =null;
                                }
                                try{
                                    pageCount=Integer.parseInt(j.get("pageCount").toString());//
                                }catch(NullPointerException e){
                                    pageCount = 0;
                                }
                                try{
                                    thumbnailUrl=imageLinks.get("thumbnail").toString();
                                }catch(NullPointerException e){
                                    thumbnailUrl=null;
                                }
                                try{
                                    language=j.get("language").toString();//
                                }catch(NullPointerException e){
                                    language =null;
                                }
                                try{
                                    previewLink=j.get("previewLink").toString();//
                                }catch(NullPointerException e){
                                    previewLink=null;
                                }
                                try{
                                    avarageRating=Double.parseDouble(j.get("averageRating").toString());//
                                }catch(NullPointerException e){
                                    avarageRating= 0.0;
                                }
                                try{
                                    authors = gson.fromJson(j.get("authors").toString() , String[].class);
                                    //authors=j.get("authors");//
                                }catch(NullPointerException e){
                                    authors =null;
                                }
                                try{
                                    categories = gson.fromJson(j.get("categories").toString() , String[].class);
                                    //categories=(String[])j.get("categories");//
                                }catch(NullPointerException e) {
                                    categories = null;
                                }


                                books.put(isbn, new Book(isbn,title,
                                        subtitle,
                                        publisher,
                                        publishedDate,
                                        description,
                                        pageCount,
                                        thumbnailUrl,
                                        language,
                                        previewLink,
                                        avarageRating,
                                        authors,
                                        categories));


                                if(avarageRating!=0.0)
                                    try {
                                        for (int k = 0; k < authors.length; k++) {
                                            if (authorsRates.containsValue(authors[k])) {
                                                Author a = authorsRates.get(authors[k]);
                                                a.addBook(avarageRating);
                                                authorsRates.replace(authors[k], a);
                                            } else
                                                authorsRates.put(authors[k], new Author(authors[k], avarageRating));
                                        }
                                    }catch (NullPointerException e){}


                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }




    }

    public String getAllBooks(){
        Gson gson = new Gson();
        Type personType = new TypeToken<HashMap<String, Book>>(){}.getType();
        String json = gson.toJson(books, personType);
        json=json.replaceAll("]}","]}\n");
        json=json.replaceAll(",",",\n");
        return json;
    }

    public String getBookById(String id) {
        Gson gson = new Gson();
        String json = gson.toJson(this.books.get(id));
        json=json.replaceAll("]}","]}\n");
        json=json.replaceAll(",",",\n");
        return json;
    }

    public JSONArray getBooksByCategory(String category) {
        JSONArray json = new JSONArray();
        JSONArray result = new JSONArray();
        Gson gson = new Gson();
        json.addAll(books.values());
        for(int i=0;i<json.size();i++){
            Book j = (Book) json.get(i);
            try {
                String[] categories = j.getCategories();
                if (contain(categories,category))
                    result.add(j);
            }catch(Exception e){ }
        }
        return result;
    }

    public static boolean contain(String[] arr, String targetValue) {
        for (String s: arr) {
            if (s.equals(targetValue))
                return true;
        }
        return false;
    }

    public String getRating() {
         return "Rating";
    }
}
