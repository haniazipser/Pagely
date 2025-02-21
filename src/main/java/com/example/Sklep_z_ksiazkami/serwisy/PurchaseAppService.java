package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.ShippingMethodId;
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

    public void addOfferToOrder(Integer offerId, Integer orderId){
        Order order =orderRepo.getById(orderId);
        Offer offer = offerRepo.getById(offerId);
        Optional<OrderDetails> temp = order.getItems().stream().findFirst();
        if (temp.isPresent() && temp.get().getOffer().getClient() != offer.getClient()){
            throw new OrderException("You can only buy offers from the same seller");
        }

        Integer buyer = order.getBuyer().getId();
        List<PriceProposal> proposals = priceProposalRepo.findByClientIdAndOfferId(buyer,offerId);
        if (proposals.isEmpty()){
            order.addOffer(offer, offer.getPrice());
        }else{
            Float negotiatedPrice = proposals.get(0).getPrice();
            order.addOffer(offer, negotiatedPrice);
        }
        orderRepo.save(order);
    }

    public void deleteOfferFromOrder(Integer offerId, Integer orderId){
        logger.info("usun {} z {}", offerId, orderId);
        Order order =orderRepo.getById(orderId);
        logger.info("order{}",order);
        boolean found = false;
        Set<OrderDetails> items = order.getItems();
        if (items == null || items.isEmpty()) {
            throw new OrderException("Order has no items");
        }
        for (OrderDetails o : items){
            logger.info("item o id{}", o.getOffer().getId());
            if (o.getOffer().getId() == offerId){
                found = true;
                order.deleteOffer(o);
                break;
            }
        }
        if (!found){
            throw new OrderException("This item is not in your order");
        }

        orderRepo.save(order);
    }


    public void addShippingMethodToOrder(ShippingMethodId id, Integer orderId){
        ShippingMethod method = shippingMethodRepo.getById(id);
        Order order = orderRepo.getById(orderId);
        Optional<OrderDetails> temp = order.getItems().stream().findFirst();
        if (temp.isPresent() && temp.get().getOffer().getClient().getId() != method.getId().getClientId()){
            throw new OrderException("This seller doesn't support this shipping method");
        }
        order.addShippingMethod(method);
    }

    public List<ShippingMethod> showShippingMethods(Integer orderId){
        Order order = orderRepo.getById(orderId);
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
        Order order = orderRepo.getById(id);
        logger.info("{}", order);
        return new OrderDto(order);
    }
}
