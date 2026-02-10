package auca.ac.rw.LibraryBookManagementAPI.modal.library;

public class Book {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    
    //constructor creation

    public Book(Long id, String title, String author, String isbn, int publicationYear) {
        this.id = id;                          // Assign the values!
        this.title = title;                    //  i use  'this.' keyword
        this.author = author;                  //  To set the fields
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }


}
