package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.OrderStatus;
import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
import com.example.Sklep_z_ksiazkami.Model.dto.OfferDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDetailsDto;
import com.example.Sklep_z_ksiazkami.Model.dto.OrderDto;
import com.example.Sklep_z_ksiazkami.Model.entity.*;
import com.example.Sklep_z_ksiazkami.Repozytorium.*;
import com.example.Sklep_z_ksiazkami.controler.user.PurchaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class PurchaseAppService {
    Logger logger = LoggerFactory.getLogger(PurchaseAppService.class);
    private final OfferRepo offerRepo;
    private final OrderRepo orderRepo;
    private final ShippingMethodRepo shippingMethodRepo;
    private final ClientRepo clientRepo;
    private final PriceProposalRepo priceProposalRepo;

    public PurchaseAppService(OfferRepo offerRepo, OrderRepo orderRepo, ShippingMethodRepo shippingMethodRepo, ClientRepo clientRepo, PriceProposalRepo priceProposalRepo) {
        this.offerRepo = offerRepo;
        this.orderRepo = orderRepo;
        this.shippingMethodRepo = shippingMethodRepo;
        this.clientRepo = clientRepo;
        this.priceProposalRepo = priceProposalRepo;
    }

    public OrderDto addOffer(Integer offerId, Integer buyerId){
        logger.info("w metodzie");
        Order order =orderRepo.findByBuyerIdAndStatus(buyerId, OrderStatus.DRAFT);
        if (order == null){
            logger.info("tworzymy nowe zamowienie");
            Integer orderId = createNewOrder(buyerId);
            logger.info("Id:" + orderId);
            order = orderRepo.getById(orderId);
        }
            Offer offer = offerRepo.getById(offerId);
            Optional<OrderDetails> temp = order.getItems().stream().findFirst();
            if (temp.isPresent() && temp.get().getOffer().getClient() != offer.getClient()) {
                throw new OrderException("You can only buy offers from the same seller");
            }

            Integer buyer = order.getBuyer().getId();
            List<PriceProposal> proposals = priceProposalRepo.findByClientIdAndOfferId(buyer, offerId);
            if (proposals.isEmpty()) {
                order.addOffer(offer, offer.getPrice());
            } else {
                Money negotiatedPrice = proposals.get(0).getPrice();
                order.addOffer(offer, negotiatedPrice);
            }
            orderRepo.save(order);
            return new OrderDto(order);
    }

    public OrderDto deleteOfferFromOrder(Integer itemId, Integer buyerId){
        logger.info("in delete method");
        Order order = orderRepo.findByBuyerIdAndStatus(buyerId, OrderStatus.DRAFT);
        boolean found = false;
        Set<OrderDetails> items = order.getItems();
        if (items == null || items.isEmpty()) {
            throw new OrderException("Order has no items");
        }
        for (OrderDetails o : items){
            if (o.getId() == itemId){
                found = true;
                order.deleteOffer(o);
                break;
            }
        }
        if (!found){
            throw new OrderException("This item is not in your order");
        }
        if(order.getItems().isEmpty()){
            order.removeShippingMethod();
        }

        orderRepo.save(order);
        return new OrderDto(order);
    }


    public void addShippingMethodToOrder(ShippingMethodId id, Integer clientId){
        ShippingMethod method = shippingMethodRepo.getById(id);
        Order order = orderRepo.findByBuyerIdAndStatus(clientId, OrderStatus.DRAFT);
        /*Optional<OrderDetails> temp = order.getItems().stream().findFirst();
        if (temp.isPresent() && temp.get().getOffer().getClient().getId() != method.getId().getClientId()){
            throw new OrderException("This seller doesn't support this shipping method");
        }*/
        if (order.getShippingMethod()==null) {
            order.addShippingMethod(method);
        } else {
            order.changeShippingMethod(method);
        }
    }

    public List<ShippingMethod> showShippingMethods(Integer clientId){

        Order order = orderRepo.findByBuyerIdAndStatus(clientId, OrderStatus.DRAFT);
        Optional<OrderDetails> temp = order.getItems().stream().findFirst();
        if (temp.isPresent()){
            Integer sellerId = temp.get().getOffer().getClient().getId();
            return shippingMethodRepo.findByIdClientId(sellerId);
        } else {
            throw new OrderException("You need to add offers first");
        }
    }

    public void approveOrder(Integer orderId){
        Order order =orderRepo.getById(orderId);
        if(order.getItems().isEmpty()){
            throw new OrderException("This order is empty");
        } else if (order.getShippingMethod() == null){
            throw new OrderException("You must choose shipping method");
        }else {
            order.submit();
        }
        orderRepo.save(order);
    }

    public Integer createNewOrder(Integer clientId) {
        Client client = clientRepo.getById(clientId);
        Order order = new Order(client);
        orderRepo.save(order);
        return order.getId();
    }

    public OrderDto showOrder(Integer id) {
        logger.info("id:{}", id);
        Order order = orderRepo.findByBuyerIdAndStatus(id, OrderStatus.DRAFT);
        logger.info("{}", order);
        return new OrderDto(order);
    }

    public List <OfferDto> offersFromTheSameSeller(Integer clientId) {
       return offerRepo.findByClientId(clientId).stream().map(o -> new OfferDto(o)).collect(Collectors.toList());

    }
}
