package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.UserDTO;
import com.example.Bookstore.Application.model.UserRegistration;

import javax.validation.Valid;
import java.util.List;

public interface IUserService {

    UserRegistration addUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    //  Get id By token
    Object getUserById(String token);

    Integer loginUserId(String email_id);

    UserRegistration getUserByID(int id);

    UserRegistration updateRecordById(Integer id, @Valid UserDTO userDTO);

    String getToken(String email);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateUser(String email_id, UserDTO userDTO);

    String loginUser(String email_id, String password);

    String forgotPassword(String email, String password);

    Object getUserByEmailId(String emailId);
}