package com.example.Sklep_z_ksiazkami.controler.user;


import com.example.Sklep_z_ksiazkami.Model.dto.OrderDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Book;
import com.example.Sklep_z_ksiazkami.Model.entity.Client;
import com.example.Sklep_z_ksiazkami.Model.entity.Offer;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.serwisy.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final BookAppService bookAppService;
   // private final UserAppService userAppService;
    private final OfferAppService offerAppService;

    private final OrderAppService orderAppService;


    public ClientController(BookAppService bookAppService, OfferAppService offerAppService, OrderAppService orderAppService) {
        this.bookAppService = bookAppService;
        this.offerAppService = offerAppService;

        this.orderAppService = orderAppService;
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable int id){
        return bookAppService.getBookById(id);
    }

    @GetMapping("/books")
    public List<Book> getBookById(){
        return bookAppService.getBooks();
    }

    @GetMapping("/offers/{id}")
    public List<Offer> getClientsOffers(@PathVariable int id){
        logger.info("id:{}", id);
        return offerAppService.getClientsOffers(id);
    }

    @GetMapping("/orders/{id}")
    public List<OrderDto> getClientsOrders(@PathVariable int id){
        return orderAppService.getClientsOrders(id);
    }

    @PostMapping("/offers/proposal/{offerId}/{clientId}/{price}")
    public void newPriceProposal(@PathVariable Integer offerId, @PathVariable Integer clientId, @PathVariable Float price){
        offerAppService.createPriceProposal(offerId,clientId,price);
    }

}
