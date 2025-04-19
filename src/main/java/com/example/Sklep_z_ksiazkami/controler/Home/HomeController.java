package com.example.Sklep_z_ksiazkami.controler.Home;

import com.example.Sklep_z_ksiazkami.Model.dto.BestsellerDto;
import com.example.Sklep_z_ksiazkami.Model.dto.BookDto;
import com.example.Sklep_z_ksiazkami.Model.dto.CategoryDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OfferDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Category;
import com.example.Sklep_z_ksiazkami.serwisy.BookAppService;
import com.example.Sklep_z_ksiazkami.serwisy.OfferAppService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/public")
public class HomeController {
    private final BookAppService bookAppService;
    private final OfferAppService offerAppService;

    public HomeController(BookAppService bookAppService, OfferAppService offerAppService) {
        this.bookAppService = bookAppService;
        this.offerAppService = offerAppService;
    }

    @GetMapping()
    public List<Object> getHomeContent(){
        List<Object> content = new ArrayList<>();
        content.add(bookAppService.getBestsellers());
        content.add(bookAppService.getPopularAuthors());
       // content.add(bookAppService.getBestFromCategory(2));
        //content.add(bookAppService.getBestFromCategory(14));
       // content.add(bookAppService.getBestFromCategory(4));
        return content;
    }

    @GetMapping("/isLogged")
    public boolean isLogged(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return true;  // User is logged in
        } else {
            return false; // User is not logged in
        }
    }

    @GetMapping("/books/{id}")
    public BookDto getBookById(@PathVariable int id){
        return bookAppService.getBookById(id);
    }


    @GetMapping("/books")
    public List<BookDto> getBooks(){
        return bookAppService.getBooks();
    }

    @GetMapping("/offers/{id}")
    public OfferDto getOfferById(@PathVariable Integer id){
        return offerAppService.getOfferByIdWithShipping(id);
    }

    @GetMapping("/offers/isbn/{isbn}")
    public List<OfferDto> getOfferByIsbn(@PathVariable String isbn){
        return offerAppService.getOffersByIsbn(isbn);
    }
    @GetMapping("/offers")
    public List<OfferDto> getOffers(){
        return offerAppService.getOffersWithShipping();
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategoryTree(){
        return bookAppService.getCategoryTree();
    }
}
