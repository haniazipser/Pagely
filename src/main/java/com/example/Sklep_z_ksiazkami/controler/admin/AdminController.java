package com.example.Sklep_z_ksiazkami.controler.admin;


import com.example.Sklep_z_ksiazkami.Model.Status;
import com.example.Sklep_z_ksiazkami.Model.dto.BookDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OfferDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Model.entity.ISBN;
import com.example.Sklep_z_ksiazkami.Model.entity.Offer;

import com.example.Sklep_z_ksiazkami.serwisy.BookAppService;
import com.example.Sklep_z_ksiazkami.serwisy.ClientAppService;
import com.example.Sklep_z_ksiazkami.serwisy.OfferAppService;
import com.example.Sklep_z_ksiazkami.serwisy.UserAppService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final BookAppService bookAppService;
    private final UserAppService userAppService;
    private final OfferAppService offerAppService;

    private final ClientAppService clientAppService;

    public AdminController(BookAppService bookAppService, UserAppService userAppService, OfferAppService offerAppService, ClientAppService clientAppService) {
        this.bookAppService = bookAppService;
        this.userAppService = userAppService;
        this.offerAppService = offerAppService;
        this.clientAppService = clientAppService;
    }
    @GetMapping("/offers")
    public List<OfferDto> getOffers(){
        return offerAppService.getOffersWithShipping();
    }

    @GetMapping("/offers/{id}")
    public Offer getOfferById(@PathVariable int id){
        return offerAppService.getOfferById(id);
    }

    @GetMapping("/books/ISBN")
    public List<ISBN> getISBNs(){
        return bookAppService.getAllISBNs();
    }

    @GetMapping("/clients")
    public List<Client> getClients(){
        return clientAppService.getAllClients();
    }

    @PostMapping("/books")
    public void createBook (@Valid @RequestBody Book book){
        bookAppService.CreateBook(book);
    }

    @PostMapping("/books/{bookId}/ISBN/{isbn}")
    public void addISBNToBook (@PathVariable int bookId, @Valid @PathVariable String isbn){
        System.out.println("id:" + bookId + " isbn :" + isbn);
        bookAppService.addISBNToBook(bookId, isbn);
    }

    @PutMapping("/users/{login}/{status}")
    public void updateUserStatus (@PathVariable String login, @Valid @PathVariable Status status){
        userAppService.updateStatus(login, status);
    }

    @PutMapping("/clients/{id}/{status}")
    public void updateClientStatus (@PathVariable Integer id, @Valid @PathVariable Status status){
        clientAppService.updateStatus(id, status);
    }

    @PutMapping("/offers/{id}/{status}")
    public void updateOfferStatus (@PathVariable Integer id, @Valid @PathVariable Status status){
        offerAppService.updateStatus(id, status);
    }

    @DeleteMapping("/books/ISBN/{isbn}")
    public void deleteISBN (@Valid @PathVariable String isbn){
        bookAppService.deleteISBN ( isbn);
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable int bookId){
        bookAppService.deleteBook ( bookId);
    }
}
