package com.example.Bookstore.Application.controller;

import com.example.Bookstore.Application.dto.ResponseDTO;
import com.example.Bookstore.Application.dto.UserDTO;
import com.example.Bookstore.Application.dto.UserLoginDTO;
import com.example.Bookstore.Application.model.UserRegistration;
import com.example.Bookstore.Application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserRegistrationController {

    @Autowired
    IUserService userRegistrationService;

    //hello
    @GetMapping("/")
    public String hello() {
        return "welcome to user registration page";
    }

    // Returns  the used if the mail id is given in the parameter
    @GetMapping("/getuserid")
    public Integer getUserIdByEMail(@RequestParam String email) {
        return userRegistrationService.loginUserId(email);
    }

    //Add User
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUserInBookStore(@Valid @RequestBody UserDTO userDTO){
        UserRegistration newUser= new UserRegistration(userRegistrationService.addUser(userDTO));
        ResponseDTO responseDTO=new ResponseDTO("User Registered Successfully",newUser);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }


    //Get All Users
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllUser()
    {
        List<UserRegistration> listOfUsers = userRegistrationService.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrieved successfully (:",listOfUsers);
        return new ResponseEntity(dto,HttpStatus.OK);
    }


    //Get All Users by id
    @GetMapping(value = "/getuserById/{id}")
    public ResponseEntity<String> getAllUserById(@PathVariable int id)
    {
        UserRegistration user = userRegistrationService.getUserByID(id);
        ResponseDTO dto = new ResponseDTO("User retrieved successfully ",user);
        return new ResponseEntity(dto,HttpStatus.OK);//
    }
    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer id,@RequestBody UserDTO userDTO){
        UserRegistration entity = userRegistrationService.updateRecordById(id,userDTO);
        ResponseDTO dto = new ResponseDTO("User Record updated successfully",entity);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token)
    {
        List<UserRegistration> listOfUser = userRegistrationService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfUser);
        return new ResponseEntity(dto,HttpStatus.OK);
    }
    //Get user by user token
    @GetMapping("/getIdByToken/{token}")
    public ResponseEntity<ResponseDTO> getUserById(@PathVariable String token) {
        return new ResponseEntity<>(new
                ResponseDTO("Get User Data By Id",
                userRegistrationService.getUserById(token)), HttpStatus.OK);
    }
//    @PutMapping("/updatebyToken/{token}")
//    public ResponseEntity<String> updateRecordById(@PathVariable String token,@Valid @RequestBody UserDTO userDTO){
//        UserRegistration entity = userRegistrationService.updateRecordByToken(token,userDTO);
//        ResponseDTO dto = new ResponseDTO("User Record updated successfully",entity);
//        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
//    }
    @GetMapping("/getToken/{email}")
    public ResponseEntity<ResponseDTO> getToken(@PathVariable String email){
        String generatedToken=userRegistrationService.getToken(email);
        ResponseDTO responseDTO=new ResponseDTO("Token for mail id sent on mail successfully",generatedToken);
        return new ResponseEntity(responseDTO,HttpStatus.OK);
    }
    //___________________________________
    //Login
    @GetMapping("/login")
    public String userLogin(@RequestParam String email,@RequestParam String password) {
        UserLoginDTO userLoginDTO=new UserLoginDTO(email, password);
        String response = userRegistrationService.loginUser(userLoginDTO.getEmail(),userLoginDTO.getPassword());
        return response;
    }
    //Forget password by email
    @PostMapping("/forgotpassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String password) {
        String resp = userRegistrationService.forgotPassword(email,password);
        return new ResponseEntity(resp, HttpStatus.OK);
    }
    //Get by email id
    @GetMapping("/getByEmailId/{emailId}")
    public ResponseEntity<ResponseDTO> getUserByEmailId(@PathVariable("emailId") String emailId) {
        return new ResponseEntity<ResponseDTO>( new
                ResponseDTO("Get User Data by Email",
                userRegistrationService.getUserByEmailId(emailId)), HttpStatus.OK);
    }
    //Update user by email
    @PutMapping("/updateUserByEmail/{email}")
    public ResponseEntity<ResponseDTO> updateUserById(@PathVariable String email,@Valid @RequestBody UserDTO userDTO){
        UserRegistration updateUser= userRegistrationService.updateUser(email,userDTO);
        ResponseDTO dto = new ResponseDTO(" User Record updated successfully",updateUser);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

}
//getAll
//getIdByToken
//getToken