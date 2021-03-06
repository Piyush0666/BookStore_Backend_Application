package com.example.Bookstore.Application.service;

import com.example.Bookstore.Application.dto.UserDTO;
import com.example.Bookstore.Application.exception.BookStoreException;
import com.example.Bookstore.Application.model.UserRegistration;
import com.example.Bookstore.Application.repository.UserRegistrationRepository;
import com.example.Bookstore.Application.util.EmailSenderService;
import com.example.Bookstore.Application.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    EmailSenderService mailService;
    @Autowired
    TokenUtility util;
    @Override
    public UserRegistration addUser(UserDTO userDTO) {
        UserRegistration newUser= new UserRegistration(userDTO);
        userRepository.save(newUser);
        String token = util.createToken(newUser.getUserId());
        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registered SuccessFully, hi: "
                +newUser.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8086/user/getBy/"+token);
        return newUser;
    }


    @Override
    public List<UserRegistration> getAllUsers() {
        List<UserRegistration> getUsers= userRepository.findAll();
        return getUsers;
    }
   //  Get id By token
    @Override
    public Object getUserById(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistration> getUser=userRepository.findById(id);
        if(getUser.isEmpty()){
            throw new BookStoreException("Record for provided userId is not found");
        }
        else {
            mailService.sendEmail("piyushvermaa.08@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +getUser.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8086/user/getBy/"+token);
            return getUser;
        }

    }

    // Return the user id when ever the user email is present.
    @Override
    public Integer loginUserId(String email_id) {
        UserRegistration login = userRepository.findByEmailid(email_id).get();
        return login.getUserId();
    }

    public UserRegistration getUserByID(int id) {
        Optional<UserRegistration> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            return null;
        }
        return userOptional.get();
    }


    @Override
    public UserRegistration updateRecordById(Integer id, @Valid UserDTO userDTO) {
        Optional<UserRegistration> addressBook = userRepository.findById(id);
        if(addressBook.isEmpty()) {
            throw new BookStoreException("User Details for id not found");
        }
        UserRegistration newBook = new UserRegistration(id,userDTO);
        userRepository.save(newBook);
        return newBook;
    }

    //need to working on this method


    @Override
    public String getToken(String email) {
        Optional<UserRegistration> userRegistration=userRepository.findByEmailid(email);
        String token=util.createToken(userRegistration.get().getUserId());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(token);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        mailService.sendEmail(userRegistration.get().getEmail(),"Welcome"+userRegistration.get().getFirstName(),"Token for changing password is :"+token);
        return token;
    }

    @Override
    public List<UserRegistration> getAllUserDataByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistration> isContactPresent=userRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserRegistration> listOfUsers=userRepository.findAll();
            mailService.sendEmail("piyushvermaa.08@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8086/user/getAll/"+token);
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }
    @Override
    public UserRegistration updateUser(String email_id, UserDTO userDTO) {
        Optional<UserRegistration> updateUser = userRepository.findByEmailid(email_id);
        if(updateUser.isEmpty()) {
            throw new BookStoreException("Book Details for email not found");
        }
        UserRegistration newBook = new UserRegistration(updateUser.get().getUserId(),userDTO);
        userRepository.save(newBook);
        String token = util.createToken(newBook.getUserId());
        mailService.sendEmail(newBook.getEmail(),"Welcome "+newBook.getFirstName(),"Click here \n http://localhost:8086/user/getBy/"+token);
        return newBook;
    }
    @Override
    public String loginUser(String email_id, String password) {
        Optional<UserRegistration> login = userRepository.findByEmailid(email_id);
        if(login.isPresent()){
            String pass = login.get().getPassword();
            System.out.println(pass);
            System.out.println(password);
            if(login.get().getPassword().equals(password)){
                return "User Login successfully";
            }

            else {
                return "Wrong Password";
            }
        }
        return "User not found";
    }
    @Override
    public String forgotPassword(String email, String password) {
        Optional<UserRegistration> isUserPresent = userRepository.findByEmailid(email);

        if(!isUserPresent.isPresent()) {
            throw new BookStoreException("Book record does not found");
        }
        else {
            UserRegistration user = isUserPresent.get();
            user.setPassword(password);
            userRepository.save(user);
            return "Password updated successfully";
        }

    }
    @Override
    public Object getUserByEmailId(String emailId) {

        return userRepository.findByEmailid(emailId);
    }

}
//updateUser
//updateRecordByToken
//getToken
//getAllUserDataByToken
