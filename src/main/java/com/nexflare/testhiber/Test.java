package com.nexflare.testhiber;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.dal.BlogDAL;
import com.nexflare.testhiber.pojo.Blog;

import java.util.UUID;

public class Test {

    public static void main(String[] args) {
        AbstractDAL<Blog, UUID> blog = new BlogDAL();
        System.out.println(blog.getAll());


    }
}
