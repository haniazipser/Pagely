package com.example.Sklep_z_ksiazkami.serwisy;

import com.example.Sklep_z_ksiazkami.Model.dto.OrderDto;
import com.example.Sklep_z_ksiazkami.Model.entity.Order;
import com.example.Sklep_z_ksiazkami.Repozytorium.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderAppService {
    private final OrderRepo repo;

    public OrderAppService(OrderRepo repo) {
        this.repo = repo;
    }


    public List<OrderDto> getClientsOrders(int id) {
        return repo.findByBuyerId(id).stream().map(o -> new OrderDto(o)).collect(Collectors.toList());
    }
}
