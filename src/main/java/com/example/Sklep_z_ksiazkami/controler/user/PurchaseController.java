package com.example.Sklep_z_ksiazkami.controler.user;

import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
import com.example.Sklep_z_ksiazkami.Model.dto.OfferDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDetailsDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.Model.entity.ShippingMethod;
import com.example.Sklep_z_ksiazkami.serwisy.ClientAppService;
import com.example.Sklep_z_ksiazkami.serwisy.PurchaseAppService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {

    private final PurchaseAppService purchaseAppService;
    private final ClientAppService clientAppService;

    public PurchaseController(PurchaseAppService purchaseAppService, ClientAppService clientAppService) {
        this.purchaseAppService = purchaseAppService;
        this.clientAppService = clientAppService;
    }
    @PostMapping("/new/{clientId}")
    public Integer createNewOrder(@PathVariable Integer clientId){
        return purchaseAppService.createNewOrder(clientId);
    }

    @GetMapping("/myOrder")
    public OrderDto showMyOrder(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer id = clientAppService.getClientByUser(authentication.getName()).getId();
       OrderDto orderDto = purchaseAppService.showOrder(id);
       return orderDto;
    }


    /*@PostMapping("/addOffer/{offerId}/{orderId}")
    public void addOfferToOrder (@PathVariable Integer offerId, @PathVariable Integer orderId){
        purchaseAppService.addOfferToOrder(offerId,orderId);
    }*/
    @CrossOrigin
    @GetMapping("/addOffer/{offerId}")
    public OrderDto addOffer(@PathVariable Integer offerId){
        System.out.println("W controllerze");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer clientId = clientAppService.getClientByUser(authentication.getName()).getId();
        return purchaseAppService.addOffer(offerId, clientId);
    }

    @GetMapping("/shipping")
    public List<ShippingMethod> showShippingMethods (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer clientId = clientAppService.getClientByUser(authentication.getName()).getId();
        return purchaseAppService.showShippingMethods(clientId);
    }

    @CrossOrigin
    @PostMapping("/addShipping")
    public void addShippingMethodToOrder(@RequestBody ShippingMethodId methodId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer clientId = clientAppService.getClientByUser(authentication.getName()).getId();
        purchaseAppService.addShippingMethodToOrder(methodId,clientId);
    }

    @DeleteMapping("/deleteOffer/{offerId}")
    public void deleteOfferFromOrder (@PathVariable Integer offerId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer clientId = clientAppService.getClientByUser(authentication.getName()).getId();
        purchaseAppService.deleteOfferFromOrder(offerId, clientId);
    }

    @PutMapping("/{id}")
    public void approveOrder(@PathVariable Integer id){
        purchaseAppService.approveOrder(id);
    }

    @GetMapping("/offers/{id}")
    public List<OfferDto> offersFromTheSameSeller(@PathVariable Integer clientId){
        return purchaseAppService.offersFromTheSameSeller(clientId);
    }
}
