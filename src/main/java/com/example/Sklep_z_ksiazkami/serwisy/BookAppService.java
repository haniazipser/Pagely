package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.dto.BookDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import com.example.Sklep_z_ksiazkami.Repozytorium.BookRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.ISBNRepo;
import com.example.Sklep_z_ksiazkami.Repozytorium.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookAppService {
    private final BookRepo repo;
    private final ISBNRepo isbnRepo;

    public BookAppService(BookRepo repo, ISBNRepo isbnRepo) {
        this.repo = repo;
        this.isbnRepo = isbnRepo;
    }

    public void CreateBook (Book book){
        repo.save(new Book());
    }

    public void addISBNToBook(int bookId, String newIsbn) {

        Book book = repo.getById(bookId);

        ISBN isbn = new ISBN();
        isbn.setBook(book);
        isbn.setISBN(newIsbn);

        isbnRepo.save(isbn);

        //wywolac connectIsbn?


    }


    public void deleteISBN(String isbn) {
        isbnRepo.deleteById(isbn);
    }

    public void deleteBook(int bookId) {
        repo.deleteById(bookId);
    }

    public Book getBookById(int id) {
        return repo.getById(id) ; }

    public List<Book> getBooks() {
        return  repo.findAll();
    }

    public List<ISBN> getAllISBNs() {
        return isbnRepo.findAll();
    }
}
