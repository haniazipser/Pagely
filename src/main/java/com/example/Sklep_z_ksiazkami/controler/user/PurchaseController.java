package com.example.Sklep_z_ksiazkami.controler.user;

import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
import com.example.Sklep_z_ksiazkami.Model.dto.OfferDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDetailsDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.Model.entity.ShippingMethod;
import com.example.Sklep_z_ksiazkami.serwisy.PurchaseAppService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {

    private final PurchaseAppService purchaseAppService;

    public PurchaseController(PurchaseAppService purchaseAppService) {
        this.purchaseAppService = purchaseAppService;
    }
    @PostMapping("/new/{clientId}")
    public Integer createNewOrder(@PathVariable Integer clientId){
        return purchaseAppService.createNewOrder(clientId);
    }

    @GetMapping("/{id}")
    public OrderDto showOrder(@PathVariable Integer id){
       OrderDto orderDto = purchaseAppService.showOrder(id);
       return orderDto;
    }

    /*@PostMapping("/addOffer/{offerId}/{orderId}")
    public void addOfferToOrder (@PathVariable Integer offerId, @PathVariable Integer orderId){
        purchaseAppService.addOfferToOrder(offerId,orderId);
    }*/

    @PostMapping("/addOffer/{offerId}/{clientId}")
    public OrderDto addOffer(@PathVariable Integer offerId, @PathVariable Integer clientId){
        return purchaseAppService.addOffer(offerId, clientId);
    }

    @GetMapping("/shipping/{clientId}")
    public List<ShippingMethod> showShippingMethods (@PathVariable Integer clientId){
        return purchaseAppService.showShippingMethods(clientId);
    }

    @PutMapping("/addShipping/{clientId}")
    public void addShippingMethodToOrder(@RequestBody ShippingMethodId methodId, @PathVariable Integer clientId){
        purchaseAppService.addShippingMethodToOrder(methodId,clientId);
    }

    @DeleteMapping("/deleteOffer/{offerId}/{clientId}")
    public void deleteOfferFromOrder (@PathVariable Integer offerId, @PathVariable Integer clientId){
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
