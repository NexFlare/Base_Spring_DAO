package com.nexflare.blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.dal.BlogDAL;
import com.nexflare.blog.pojo.Blog;

import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        AbstractDAL<Blog, UUID> blog = new BlogDAL();
        System.out.println(blog.getAll());


    }
}
