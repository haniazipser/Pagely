package com.example.Sklep_z_ksiazkami.controler.user;


import com.example.Sklep_z_ksiazkami.Model.dto.*;
import com.example.Sklep_z_ksiazkami.Model.entity.*;
import com.example.Sklep_z_ksiazkami.serwisy.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/client")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final BookAppService bookAppService;
   // private final UserAppService userAppService;
    private final OfferAppService offerAppService;

    private final ClientAppService clientAppService;

    private final OrderAppService orderAppService;


    public ClientController(BookAppService bookAppService, OfferAppService offerAppService, ClientAppService clientAppService, OrderAppService orderAppService) {
        this.bookAppService = bookAppService;
        this.offerAppService = offerAppService;
        this.clientAppService = clientAppService;

        this.orderAppService = orderAppService;
    }

    @GetMapping("/offers/{id}")
    public List<Offer> getClientsOffers(@PathVariable int id){
        logger.info("id:{}", id);
        return offerAppService.getClientsOffers(id);
    }

    @GetMapping("/info")
    public ClientDto getClientInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ClientDto(clientAppService.getClientByUser(authentication.getName()));
    }

    @GetMapping("/orders/{id}")
    public List<OrderDto> getClientsOrders(@PathVariable int id){
        return orderAppService.getClientsOrders(id);
    }

    @PostMapping("/offers/proposal/{offerId}/{clientId}/{price}")
    public void newPriceProposal(@PathVariable Integer offerId, @PathVariable Integer clientId, @PathVariable BigDecimal price){
        offerAppService.createPriceProposal(offerId,clientId,new Money(price));
    }

}
