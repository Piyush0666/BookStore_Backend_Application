package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.CartDTO;
import com.example.Bookstore.Application.dto.ResponseDTO;
import com.example.Bookstore.Application.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    ResponseDTO getCartDetails();


    Optional<Cart> getCartDetailsById(Integer cartId);

    Optional<Cart> deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);

    Cart insertItems(CartDTO cartdto);

    Cart updateQuantity(Integer id, Integer quantity);
}