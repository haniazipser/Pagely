package com.example.Sklep_z_ksiazkami.controler.user;

import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
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

    @PostMapping("/addOffer/{offerId}/{orderId}")
    public void addOfferToOrder (@PathVariable Integer offerId, @PathVariable Integer orderId){
        purchaseAppService.addOfferToOrder(offerId,orderId);
    }

    @GetMapping("/shipping/{orderId}")
    public List<ShippingMethod> showShippingMethods (@PathVariable Integer orderId){
        return purchaseAppService.showShippingMethods(orderId);
    }

    @PutMapping("/addShipping/{orderId}")
    public void addShippingMethodToOrder(@RequestBody ShippingMethodId methodId, @PathVariable Integer orderId){
        purchaseAppService.addShippingMethodToOrder(methodId,orderId);
    }

    @DeleteMapping("/deleteOffer/{offerId}/{orderId}")
    public void deleteOfferFromOrder (@PathVariable Integer offerId, @PathVariable Integer orderId){
        purchaseAppService.deleteOfferFromOrder(offerId,orderId);
    }

    @PutMapping("/{id}")
    public void approveOrder(@PathVariable Integer id){
        purchaseAppService.approveOrder(id);
    }
}
