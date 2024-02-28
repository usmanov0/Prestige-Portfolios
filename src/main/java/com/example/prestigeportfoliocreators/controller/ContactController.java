package com.example.prestigeportfoliocreators.controller;


import com.example.prestigeportfoliocreators.dto.PaginationForm;
import com.example.prestigeportfoliocreators.models.Message;
import com.example.prestigeportfoliocreators.service.ContactService;
import com.example.prestigeportfoliocreators.util.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(path = "/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(path = "/send")
    public ResponseEntity<HashMap<String,Object>> sendMessage(@RequestBody Message message){
        contactService.sendMessage(message);
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<HashMap<String,Object>> getMessage(@Valid PaginationForm paginationForm){
        Page<Message> page = contactService.getMessages(paginationForm);
        String[] keys = {"message","totalPages","hasNext"};
        Object[] values = {page.getContent(), page.getTotalPages(), page.hasNext()};
        return new ResponseEntity<>(Response.createBody(keys,values), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HashMap<String,Object>> deleteMessage(@PathVariable ("id")Long id){
        String errMessage = contactService.deleteMessage(id);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }
}
