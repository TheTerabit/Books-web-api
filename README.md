# Books
Java / Spring Boot
-

This WEB API provides several endpoints and serves the relevant information about books based on data read from a JSON file.

Endpoints 
-

- All books

  Returns entire data set in the form of a JSON document.

  Endpoint url: /api/book

- Book by ISBN 

  Returns a book identified by the given ISBN number in the form of a JSON document or 
return a 404 if the book does not exists in the data set.

  Endpoint url: /api/book/{isbn}

- Book by category 

  Lists all books that are assigned to the requested category (empty list if no books belong to the category).

  Endpoint url: /api/category/{categoryName}/books

- Author rating 

  Lists all authors and their rating in descending order of the average rating of their books. 
If a book is not rated, it should be not taken into account in the calculation of its author’s rating.

  Endpoint url: /api/rating

Those endpoints are going to be finished at 100% in the next few days 
