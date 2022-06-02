package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.OrderDTO;
import com.example.Bookstore.Application.dto.ResponseDTO;
import com.example.Bookstore.Application.exception.BookStoreException;
import com.example.Bookstore.Application.model.Book;
import com.example.Bookstore.Application.model.Order;
import com.example.Bookstore.Application.model.UserRegistration;
import com.example.Bookstore.Application.repository.BookStoreRepository;
import com.example.Bookstore.Application.repository.OrderRepository;
import com.example.Bookstore.Application.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private BookStoreRepository bookRepo;
    @Autowired
    private UserRegistrationRepository userRepo;

    //  book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
    public Order insertOrder(OrderDTO orderdto) {
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() <= book.get().getQuantity()) {
                System.out.println("The BOOK PRICE IS :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + book.get().getPrice());
                Order newOrder = new Order(book.get().getPrice() * orderdto.getQuantity(), orderdto.getQuantity(), orderdto.getAddress(), book.get(), user.get());
                orderRepo.save(newOrder);
                System.out.println(newOrder);
                book.get().setQuantity(book.get().getQuantity() - orderdto.getQuantity());
                bookRepo.save(book.get());
               // System.out.println(newOrder);
//                System.out.println(book.get());
                return newOrder;
            } else {
                throw new BookStoreException("Requested quantity is not available");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepo.findAll();
        return orderList;
    }


    public Order getOrderRecord(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isEmpty()) {
            throw new BookStoreException("Order Record doesn't exists");
        } else {
            return order.get();
        }
    }


    @Override
    public ResponseDTO orderStatusToCancel(Integer orderId, Integer userId) {
        Optional<Order> order = orderRepo.findById(userId);
        if (order.isPresent() && userId == order.get().getUser().getUserId())
        {
            order.get().setCancel(true);
        orderRepo.save(order.get());
        return new ResponseDTO("Order is cancelled successfully", order);
    } else {
            return new ResponseDTO("Order can not be canceled", "userId or orderId is incorrect");
        }
    }

    @Override
    public Order updateOrderRecord(Integer id, OrderDTO dto) {
        Optional<Order> order = orderRepo.findById(id);
        Optional<Book> book = bookRepo.findById(dto.getBookId())
                ;
        Optional<UserRegistration> user = userRepo.findById(dto.getUserId());
        if (order.isEmpty()) {
            throw new BookStoreException("Order Record doesn't exists");
        } else {
            if (book.isPresent() && user.isPresent()) {
                if (dto.getQuantity() < book.get().getQuantity()) {
                    Order newOrder = new Order(id, dto.getQuantity(), dto.getAddress(), book.get(), user.get());
                    orderRepo.save(newOrder);
                    return newOrder;
                } else {
                    throw new BookStoreException("Requested quantity is not available");
                }
            } else {
                throw new BookStoreException("Book or User doesn't exists");

            }
        }
    }

    @Override
    public Order deleteOrderRecord(Integer id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isEmpty()) {
            throw new BookStoreException("Order Record doesn't exists");
        } else {
            orderRepo.deleteById(id);
            return order.get();
        }
    }
}
//updateOrderRecord
//deleteOrderRecord