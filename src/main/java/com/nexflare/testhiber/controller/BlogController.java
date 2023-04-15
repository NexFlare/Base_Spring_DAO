package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.BlogDAO;
import com.nexflare.testhiber.dao.UserDAO;
import com.nexflare.testhiber.enums.BlogType;
import com.nexflare.testhiber.mapper.Blog.BlogDOToResponseBlogItemMapper;
import com.nexflare.testhiber.mapper.Blog.CreateBlogRequestToBlogMapper;
import com.nexflare.testhiber.requestModel.Blog.ActionBlogRequestObject;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.requestModel.Blog.GetBlogRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Blog.ActionBlogService;
import com.nexflare.testhiber.service.Blog.CreateBlogService;
import com.nexflare.testhiber.service.Blog.GetBlogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
                                  BlogDAO blogDao, BlogDOToResponseBlogItemMapper responseMapper) {
        GetBlogRequestObject requestObject = GetBlogRequestObject.builder().type(type).blogId(blogId).location(location).build();
        BaseHandler<GetBlogRequestObject> getBlogService = new GetBlogService(userDao,request,blogDao, responseMapper);
        return getBlogService.handle(requestObject);
    }


    @PutMapping("/updatestatus")
    public Response updateBlogStatus(@RequestBody ActionBlogRequestObject obj,UserDAO userDao,
                                     HttpServletRequest request, BlogDAO blogDao) {
        BaseHandler<ActionBlogRequestObject> handler = new ActionBlogService(userDao,request,blogDao);
        return handler.handle(obj);
    }
}
