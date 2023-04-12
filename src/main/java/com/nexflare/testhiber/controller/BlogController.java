package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.BlogDAO;
import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.enums.BlogType;
import com.nexflare.testhiber.mapper.Blog.CreateBlogRequestToBlogMapper;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.requestModel.Blog.GetBlogRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Blog.CreateBlogService;
import com.nexflare.testhiber.service.Blog.GetBlogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @PostMapping("/")
    public Response addBlog(@RequestBody CreateBlogRequestObject blog, UserDAO userDao, HttpServletRequest request, BlogDAO blogDao, CreateBlogRequestToBlogMapper mapper) {
        BaseHandler<CreateBlogRequestObject> createBlogService = new CreateBlogService(userDao,request,blogDao,mapper);
        return createBlogService.handle(blog);
    }

    @GetMapping("/")
    public Response getBlogDetail(@RequestParam(required = false) UUID blogId,
                                  @RequestParam(required = false) BlogType type,
                                  @RequestParam(required = false) String location,
                                  UserDAO userDao, HttpServletRequest request,
                                  BlogDAO blogDao) {
        GetBlogRequestObject requestObject = GetBlogRequestObject.builder().type(type).blogId(blogId).location(location).build();
        BaseHandler<GetBlogRequestObject> getBlogService = new GetBlogService(userDao,request,blogDao);
        return getBlogService.handle(requestObject);
    }
}
