package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.OrderDTO;
import com.example.Bookstore.Application.dto.ResponseDTO;
import com.example.Bookstore.Application.model.Order;

import java.util.List;

public interface IOrderService
{
    public Order insertOrder(OrderDTO orderdto);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    ResponseDTO orderStatusToCancel(Integer orderId, Integer userId);

    Order updateOrderRecord(Integer id, OrderDTO orderdto);

    Order deleteOrderRecord(Integer id);
}
