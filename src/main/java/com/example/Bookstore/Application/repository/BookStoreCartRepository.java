package com.example.Bookstore.Application.repository;

import com.example.Bookstore.Application.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreCartRepository extends JpaRepository<Cart,Integer> {
}
