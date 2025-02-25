package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.dto.AuthorDto;
import com.example.Sklep_z_ksiazkami.Model.dto.BestsellerDto;
import com.example.Sklep_z_ksiazkami.Model.dto.BookDto;
import com.example.Sklep_z_ksiazkami.Model.dto.CategoryDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import com.example.Sklep_z_ksiazkami.Model.entity.Category;
import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import com.example.Sklep_z_ksiazkami.Repozytorium.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookAppService {
    private final BookRepo repo;
    private final ISBNRepo isbnRepo;
    private final AuthorRepo authorRepo;

    private final CategoryRepo categoryRepo;

    public BookAppService(BookRepo repo, ISBNRepo isbnRepo, AuthorRepo authorRepo, CategoryRepo categoryRepo) {
        this.repo = repo;
        this.isbnRepo = isbnRepo;
        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
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

    public BookDto getBookById(int id) {
        return new BookDto(repo.getById(id) ); }

    public List<BookDto> getBooks() {
        return  repo.findAll().stream().map(b -> new BookDto(b)).collect(Collectors.toList());
    }

    public List<BestsellerDto> getBestsellers() {
        return  repo.findBestsellers().subList(0,5);
    }

    public List <BestsellerDto> getBestFromCategory(Integer id){
       // Category category = categoryRepo.getById(id);
        return repo.findByCategoryId(id);
    }

    public List <AuthorDto> getPopularAuthors(){
        return authorRepo.getPopularAuthors().subList(0,4).stream().map(a -> new AuthorDto(a)).collect(Collectors.toList());
    }

    public List<ISBN> getAllISBNs() {
        return isbnRepo.findAll();
    }

    public List<CategoryDto> getCategoryTree(){
        return categoryRepo.findByParentCategoryId(1).stream().map(c -> new CategoryDto(c)).collect(Collectors.toList());
    }
}
