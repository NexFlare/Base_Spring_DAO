package com.nexflare.blog.service.Blog;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.enums.BlogStatus;
import com.nexflare.blog.enums.UserType;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.pojo.Blog;
import com.nexflare.blog.pojo.Notification;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.Blog.ActionBlogRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public class ActionBlogService extends AuthenticatedBaseHandler<ActionBlogRequestObject> {

    private final AbstractDAL<Blog, UUID> blogDao;
    private final AbstractDAL<Notification, UUID> notificationDAL;
    public ActionBlogService(AbstractDAL<User, UUID> userDao,
                             HttpServletRequest request, AbstractDAL<Blog, UUID> blogDao,
                             AbstractDAL<Notification, UUID> notificationDAL) {
        super(userDao, request);
        this.blogDao = blogDao;
        this.notificationDAL = notificationDAL;
    }

    @Override
    protected Response handleRequest(ActionBlogRequestObject object) throws AbstractException {
        User user = this.getUserFromSession();
        if(user.getUserType() != UserType.ADMIN) {
            return BaseResponseModel.builder().code(403).errorMessage("User not authorized").build();
        }
        Blog blog = this.blogDao.get(object.getBlogId());
        blog.setBlogStatus(object.isApproved() ? BlogStatus.APPROVED : BlogStatus.REJECTED);
        this.blogDao.update(blog);
        StringBuilder notificationText = new StringBuilder("Blog ");
        notificationText.append(object.isApproved() ? "approved by ": "rejected by ");
        notificationText.append(user.getFirstName() + " " + user.getLastName());
        this.notificationDAL.add(Notification.builder().seen(false).user(blog.getUser()).text(notificationText.toString()).build());

        return BaseResponseModel.<String>builder().response(object.isApproved() ? "approved" : "rejected").code(200).build();
    }
}
