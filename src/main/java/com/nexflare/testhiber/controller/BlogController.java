package com.nexflare.testhiber.controller;


import com.nexflare.testhiber.dao.BlogDAO;
import com.nexflare.testhiber.enums.BlogType;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.requestModel.Blog.CreateBlogRequestObject;
import com.nexflare.testhiber.requestModel.Blog.GetBlogRequestObject;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.BaseHandler;
import com.nexflare.testhiber.service.Blog.CreateBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

//    private AbstractDAO<Blog, UUID> blogDao;

    private BaseHandler<CreateBlogRequestObject> createBlogService;
    private BaseHandler<GetByIdRequestObject> getBlogByIDService;

    private BaseHandler<GetBlogRequestObject> getBlogService;


    @Autowired
    public BlogController(BaseHandler<CreateBlogRequestObject> createBlogService,
                          @Qualifier("GetBlogService") BaseHandler<GetByIdRequestObject> getBlogByIDService,
                          BaseHandler<GetBlogRequestObject> getBlogService) {
        this.createBlogService = createBlogService;
        this.getBlogService = getBlogService;
    }

    @PostMapping("/")
    public Response addBlog(@RequestBody CreateBlogRequestObject blog) {
        return createBlogService.handle(blog);
    }

    @GetMapping("/")
    public Response getBlogDetail(@RequestParam(required = false) UUID blogId, @RequestParam(required = false)BlogType type, @RequestParam(required = false) String location) {
        GetBlogRequestObject requestObject = GetBlogRequestObject.builder().type(type).blogId(blogId).location(location).build();
        return this.getBlogService.handle(requestObject);
    }
}
