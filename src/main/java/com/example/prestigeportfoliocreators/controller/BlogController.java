package com.example.prestigeportfoliocreators.controller;

import com.example.prestigeportfoliocreators.dto.BlogSearchForm;
import com.example.prestigeportfoliocreators.dto.NewBlog;
import com.example.prestigeportfoliocreators.dto.PaginationForm;
import com.example.prestigeportfoliocreators.models.Blog;
import com.example.prestigeportfoliocreators.service.BlogService;
import com.example.prestigeportfoliocreators.util.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping(path = "/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping(path = "/new")
    public ResponseEntity<HashMap<String, Object>> newBlog(@RequestBody NewBlog newBlog){
        blogService.newBlog(newBlog);
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @GetMapping(path = "/get/page")
    public ResponseEntity<HashMap<String ,Object>> getBlog(HttpServletRequest request,
                                                           @Valid BlogSearchForm blogSearchForm,
                                                           @Valid PaginationForm paginationForm){
        Page<Blog> page = blogService.getBlogs(request, blogSearchForm, paginationForm);
        String[] keys = {"blogs", "totalPages", "hasNext"};
        Object[] values = {page.getContent(), page.getTotalPages(), page.hasNext()};
        return new ResponseEntity<>(Response.createBody(keys,values), HttpStatus.OK);
    }

    @PostMapping(path = "/like/{id}")
    public ResponseEntity<HashMap<String,Object>> likeBlog(HttpServletRequest request, @PathVariable("id") Long id){
        String errorMessage = blogService.like(request,id);
        if (errorMessage != null){
            return Response.errorMessage(errorMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }

    @PostMapping(path = "/unlike/{id}")
    public ResponseEntity<HashMap<String,Object>> unlikeBlog(HttpServletRequest request, @PathVariable("id") Long id){
        String errorMessage = blogService.unlike(request,id);
        if (errorMessage != null){
            return Response.errorMessage(errorMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(),HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HashMap<String,Object>> deleteBlog(@PathVariable("id") Long id){
        String errMessage = blogService.deleteBlog(id);
        if (errMessage != null){
            return Response.errorMessage(errMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Response.createBody(), HttpStatus.OK);
    }
}
