package auca.ac.rw.LibraryBookManagementAPI.controller.library;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.LibraryBookManagementAPI.modal.library.Book;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {
    List<Book> books = new ArrayList<>();
    
    public BookController(){
        //Adding 3 sample example books
        books.add(new Book(1L, "Code", "Robert", "2001", 2008));
        books.add(new Book(2L, "Programmer", "Andrew ", "2002", 1999));
        books.add(new Book(3L, "Design Patterns", "Four", "2003", 1994));
    
    }
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        System.out.println("Returning all books");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        System.out.println("Searching book by id:" + id);
        
        Book foundBook = null;
        for (Book book : books){
            if (book.getId().equals(id)){
                foundBook = book;
                break;
            }
        }
        if (foundBook != null){
            System.out.println("Book found: " + foundBook.getTitle());
            return new ResponseEntity<>(foundBook, HttpStatus.OK);
        }else{
            System.out.println("Book not found with Id:"+ id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value = "/search")
    public ResponseEntity<List<Book>> searchBookByTitle(@RequestParam String title){
        System.out.println("searching the book by the title");

        //if it match the book be stored in this list
        List<Book> matchingBooks = new ArrayList<>();
        
        //looping for the title of book
        for (Book book : books){
            if(book.getTitle().equalsIgnoreCase(title)){
                matchingBooks.add(book);
            }
        }
        System.out.println("Found "+ matchingBooks.size() + "matching books");
        return new ResponseEntity<>(matchingBooks, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        System.out.println("Adding new book: " + book.getTitle());
        
        // Auto-generate ID (next available ID)
        Long newId;
        if (books.isEmpty()) {
            newId = 1L;
        } else {
            // Get the last book's ID and add 1
            newId = books.get(books.size() - 1).getId() + 1;
        }
        book.setId(newId);
        
        // Add book to the list
        books.add(book);
        
        System.out.println("Book added with ID: " + newId);
        return new ResponseEntity<>(book, HttpStatus.CREATED);  // 201 CREATED
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        System.out.println("Attempting to delete book with ID: " + id);
        
        // Trying to remove the book with matching ID
        boolean removed = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.remove(i);
                removed = true;
                break;
            }
        }
        
        // Checking if book was removed
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 NO CONTENT
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 NOT FOUND
        }
    }

}
