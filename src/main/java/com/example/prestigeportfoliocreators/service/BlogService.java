package com.example.prestigeportfoliocreators.service;

import com.example.prestigeportfoliocreators.dto.BlogSearchForm;
import com.example.prestigeportfoliocreators.dto.NewBlog;
import com.example.prestigeportfoliocreators.dto.PaginationForm;
import com.example.prestigeportfoliocreators.errors.CustomErrors;
import com.example.prestigeportfoliocreators.models.Blog;
import com.example.prestigeportfoliocreators.models.User;
import com.example.prestigeportfoliocreators.repository.BlogRepository;
import com.example.prestigeportfoliocreators.util.DateFormat;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepo;
    @Autowired
    private UserService userService;


    public void newBlog(NewBlog newBlog){
        Blog blog = Blog.builder()
                .title(newBlog.getTitle())
                .description(newBlog.getDescription())
                .body(newBlog.getBody())
                .date(DateFormat.MMddyyyy())
                .unixTime(DateFormat.getUnixTime())
                .likeCount(0)
                .viewCount(0)
                .build();
        blogRepo.save(blog);
    }

    public Page<Blog> getBlogs(HttpServletRequest request, BlogSearchForm blogSearchForm, PaginationForm paginationForm){
        Page<Blog> page = this.findAllWithPagination(request, blogSearchForm, paginationForm);
        List<Blog> blogs = page.getContent();
        if (blogSearchForm.getLiked()){
            for (Blog blog: blogs){
                blog.setIsLikedByUser(true);
            }
        }else {
            User dummyUser = userService.getDummyUser(request);
            for (Blog blog : blogs){
                blog.checksIsLikedByUser(dummyUser);
            }
        }
        return page;
    }

    public HashMap<String, Blog> getBlog(HttpServletRequest request, Long id, BlogSearchForm blogSearchForm){
        Optional<Blog> optional = blogRepo.findById(id);
        if (!optional.isPresent()){
            return null;
        }
        Blog blog = optional.get();
        HashMap<String, Blog> res = getPrevAndNext(request, blogSearchForm, blog);
        if (res == null) {
            return null;
        }
        blog.view();
        User dummyUser = userService.getDummyUser(request);
        blog.checksIsLikedByUser(dummyUser);
        res.put("blog", blog);
        blogRepo.save(blog);
        return res;
    }

    public String like(HttpServletRequest request, Long id){
        Optional<Blog> optional = blogRepo.findById(id);
        if (!optional.isPresent()){
            return CustomErrors.BLOG_ID_ERROR;
        }
        User user = userService.getUser(request);
        Blog blog = optional.get();
        blog.like(user);
        blogRepo.save(blog);
        return null;
    }

    public String unlike(HttpServletRequest request, Long id){
        Optional<Blog> optional = blogRepo.findById(id);
        if (!optional.isPresent()){
            return CustomErrors.BLOG_ID_ERROR;
        }
        User user = userService.getUser(request);
        Blog blog = optional.get();
        blog.dislike(user);
        blogRepo.save(blog);
        return null;
    }

    public String deleteBlog(Long id){
        Optional<Blog> optional = blogRepo.findById(id);
        if (!optional.isPresent()){
            return CustomErrors.BLOG_ID_ERROR;
        }
        blogRepo.delete(optional.get());
        return null;
    }


    private HashMap<String, Blog> getPrevAndNext(HttpServletRequest request, BlogSearchForm blogSearchForm, Blog blog) {
        PaginationForm paginationForm = new PaginationForm();
        paginationForm.setPageNum(0);
        paginationForm.setPageSize(Integer.MAX_VALUE);
        List<Blog> blogs = this.findAllWithPagination(request, blogSearchForm, paginationForm).getContent();
        int lower = search(blogs, blog, blogSearchForm.getAscending(), blogSearchForm.getOrderBy(), true);
        if (lower == -1) {
            return null;
        }
        int higher = search(blogs, blog, blogSearchForm.getAscending(), blogSearchForm.getOrderBy(), false);
        int index;
        for (index = lower; index <= higher; index++) {
            if (blogs.get(index).equals(blog)) {
                break;
            }
        }
        Blog prev = null, next = null;
        if (index > 0) {
            prev = blogs.get(index-1);
        }
        if (index < blogs.size()-1) {
            next = blogs.get(index+1);
        }
        HashMap<String, Blog> res = new HashMap<>();
        res.put("prev", prev);
        res.put("next", next);
        return res;
    }


    private int search(List<Blog> blogs, Blog rhs, boolean ascending, String orderBy, boolean lowerBound) {
        int ans = -1, asc = (ascending) ? -1 : 1;
        int low = 0, mid, high = blogs.size()-1;
        while (low <= high) {
            mid = (int)((0L+low+high))>>1;
            int compare;
            Blog lhs = blogs.get(mid);
            switch (orderBy) {
                case "title":
                    compare = asc*lhs.getTitle().compareToIgnoreCase(rhs.getTitle());
                    break;
                case "like_count":
                    compare = asc*lhs.getLikeCount().compareTo(rhs.getLikeCount());
                    break;
                case "view_count":
                    compare = asc*lhs.getViewCount().compareTo(rhs.getViewCount());
                    break;
                case "unix_time":
                default:
                    compare = asc*lhs.getUnixTime().compareTo(rhs.getUnixTime());
            }
            if (compare == 0) {
                ans = mid;
                if (lowerBound) {
                    high = mid-1;
                } else {
                    low = mid+1;
                }
            } else if (compare > 0) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        return ans;
    }

    private Page<Blog> findAllWithPagination(HttpServletRequest request, BlogSearchForm blogSearchForm, PaginationForm paginationForm) {
        Direction direction = (blogSearchForm.getAscending()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, blogSearchForm.getOrderBy());
        Pageable pageable = PageRequest.of(paginationForm.getPageNum(), paginationForm.getPageSize(), sort);
        return (blogSearchForm.getLiked()) ?
                blogRepo.findAllLikedWithPagination(pageable, UserService.getUserAddress(request), blogSearchForm.getSearch()) :
                blogRepo.findAllWithPagination(pageable, blogSearchForm.getSearch());
    }
}
