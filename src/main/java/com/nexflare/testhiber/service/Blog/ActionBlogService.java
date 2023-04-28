package com.nexflare.testhiber.service.Blog;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.enums.BlogStatus;
import com.nexflare.testhiber.enums.UserType;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Notification;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.Blog.ActionBlogRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
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
