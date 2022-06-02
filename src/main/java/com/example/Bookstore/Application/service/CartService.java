package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.CartDTO;
import com.example.Bookstore.Application.dto.ResponseDTO;
import com.example.Bookstore.Application.exception.BookStoreException;
import com.example.Bookstore.Application.model.Book;
import com.example.Bookstore.Application.model.Cart;
import com.example.Bookstore.Application.model.UserRegistration;
import com.example.Bookstore.Application.repository.BookStoreCartRepository;
import com.example.Bookstore.Application.repository.BookStoreRepository;
import com.example.Bookstore.Application.repository.UserRegistrationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService implements ICartService{

    @Autowired
    BookStoreRepository bookStoreRepository;
    @Autowired
    UserRegistrationRepository userRegistrationRepository;
    @Autowired
    BookStoreCartRepository bookStoreCartRepository;

//
//    @Override
//    public List<Cart> getCartDetails() {
//        List<Cart> getCartDetails=bookStoreCartRepository.findAll();
//        if (getCartDetails.isEmpty()){
//            throw new BookStoreException(" Not found Any Cart details ");
//        }
//        else {
//            return getCartDetails;
//        }
//    }

    @Override
    public ResponseDTO getCartDetails() {
        List<Cart> getCartDetails=bookStoreCartRepository.findAll();
        ResponseDTO dto= new ResponseDTO();
        if (getCartDetails.isEmpty()){
            String   message=" the cart is empty!!!";
            dto.setMessage(message);
            dto.setData(0);
            return dto;

        }
        else {
            dto.setMessage("the list of cart items is sucussfully retrived");
            dto.setData(getCartDetails);
            return dto;
        }
    }
    @Override
    public Optional<Cart> getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData=bookStoreCartRepository.findById(cartId);
        if (getCartData.isPresent()){
            return getCartData;
        }
        else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public Optional<Cart> deleteCartItemById(Integer cartId) {
        Optional<Cart> deleteData=bookStoreCartRepository.findById(cartId);
        if (deleteData.isPresent()){
            bookStoreCartRepository.deleteById(cartId);
            return deleteData;
        }
        else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }

    }//

    @Override
    public Cart updateRecordById(Integer cartId, CartDTO cartDTO) {
        Optional<Cart> cart = bookStoreCartRepository.findById(cartId);
        Optional<Book>  book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRegistrationRepository.findById(cartDTO.getUserId());
        if(cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        }
        else {
            if(book.isPresent() && user.isPresent()) {
                Cart newCart = new Cart(cartId,cartDTO.getQuantity(),book.get(),user.get());
                bookStoreCartRepository.save(newCart);
                return newCart;
            }
            else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        }
    }

    @Override
    public Cart insertItems(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            bookStoreCartRepository.save(newCart);
            return newCart;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    @Override
    public Cart updateQuantity(Integer id, Integer quantity) {
        Optional<Cart> cart = bookStoreCartRepository.findById(id);
        Optional<Book>  book = bookStoreRepository.findById(cart.get().getBook().getBookId());
        if(cart.isEmpty()) {
            throw new BookStoreException("Cart Record doesn't exists");
        }
        else {
            if(quantity < book.get().getQuantity()) {
                cart.get().setQuantity(quantity);
                bookStoreCartRepository.save(cart.get());
                book.get().setQuantity(book.get().getQuantity() - (quantity - cart.get().getQuantity()));
                bookStoreRepository.save(book.get());
                return cart.get();
            }
            else {
                throw new BookStoreException("Requested quantity is not available");
            }
        }
    }

}
