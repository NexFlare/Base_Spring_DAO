package com.nexflare.testhiber;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.dao.BlogDAO;
import com.nexflare.testhiber.pojo.Blog;

import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        AbstractDAO<Blog, UUID> blog = new BlogDAO();
        System.out.println(blog.getAll());


    }
}
