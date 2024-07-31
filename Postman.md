### 1. GET All Books

**Request:**

- **Method:** GET
- **URL:** `http://localhost:8080/books`

**Postman Configuration:**

1. Open Postman and create a new request.
2. Set the request method to `GET`.
3. Set the URL to `http://localhost:8080/books`.
4. Click `Send`.

### 2. GET a Book by ID

**Request:**

- **Method:** GET
- **URL:** `http://localhost:8080/books/{id}`

**Postman Configuration:**

1. Open Postman and create a new request.
2. Set the request method to `GET`.
3. Set the URL to `http://localhost:8080/books/{id}` (replace `{id}` with the actual ID of the book).
4. Click `Send`.

### 3. POST Create a New Book

**Request:**

- **Method:** POST
- **URL:** `http://localhost:8080/books`
- **Body Type:** raw
- **Body Content:**
  ```json
  {
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "isbn": "9781617292545",
    "publishedDate": "2016-01-01"
  }
  ```

**Postman Configuration:**

1. Open Postman and create a new request.
2. Set the request method to `POST`.
3. Set the URL to `http://localhost:8080/books`.
4. Go to the `Body` tab.
5. Select `raw` and set the format to `JSON`.
6. Enter the JSON body content as shown above.
7. Click `Send`.

### 4. PUT Update an Existing Book

**Request:**

- **Method:** PUT
- **URL:** `http://localhost:8080/books/{id}`
- **Body Type:** raw
- **Body Content:**
  ```json
  {
    "title": "Spring Boot in Practice",
    "author": "John Doe",
    "isbn": "9781617292552",
    "publishedDate": "2022-01-01"
  }
  ```

**Postman Configuration:**

1. Open Postman and create a new request.
2. Set the request method to `PUT`.
3. Set the URL to `http://localhost:8080/books/{id}` (replace `{id}` with the actual ID of the book you want to update).
4. Go to the `Body` tab.
5. Select `raw` and set the format to `JSON`.
6. Enter the JSON body content as shown above.
7. Click `Send`.

### 5. DELETE a Book by ID

**Request:**

- **Method:** DELETE
- **URL:** `http://localhost:8080/books/{id}`

**Postman Configuration:**

1. Open Postman and create a new request.
2. Set the request method to `DELETE`.
3. Set the URL to `http://localhost:8080/books/{id}` (replace `{id}` with the actual ID of the book you want to delete).
4. Click `Send`.

### Summary of Endpoints:

- **GET** `http://localhost:8080/books`: Retrieve all books.
- **GET** `http://localhost:8080/books/{id}`: Retrieve a book by its ID.
- **POST** `http://localhost:8080/books`: Create a new book.
- **PUT** `http://localhost:8080/books/{id}`: Update an existing book by its ID.
- **DELETE** `http://localhost:8080/books/{id}`: Delete a book by its ID.

Using these configurations in Postman will allow you to fully test your API's functionality.
