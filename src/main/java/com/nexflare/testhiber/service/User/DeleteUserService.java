package com.nexflare.testhiber.service.User;

import com.nexflare.testhiber.dal.AbstractDAL;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.pojo.Blog;
import com.nexflare.testhiber.pojo.Comments;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.GetByIdRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DeleteUserService extends AuthenticatedBaseHandler<GetByIdRequestObject> {

    private AbstractDAL<Comments, UUID> commentsDAL;
    private AbstractDAL<Blog,UUID> blogDAL;

    public DeleteUserService(AbstractDAL<User, UUID> userDao,
                             HttpServletRequest request,
                             AbstractDAL<Blog,UUID> blogDAL,
                             AbstractDAL<Comments, UUID> commentsDAL) {
        super(userDao, request);
        this.commentsDAL = commentsDAL;
        this.blogDAL = blogDAL;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        User user = this.getUserFromSession();
        if(!user.getId().equals(object.getId())) {
            return BaseResponseModel.builder().code(403).errorMessage("Unauthorized request").build();
        }
        Comments commentsObj = Comments.builder().user(user).build();
        Map<String, Object> commentMap =new  ObjectToMap<Comments>().getMap(commentsObj);
        List<Comments> commentsList = commentsDAL.getElementsByQuery(commentMap);
        commentsDAL.bulkDelete(commentsList);
        List<Blog> blogList = blogDAL.getElementsByQuery(commentMap);
        blogDAL.bulkDelete(blogList);
        // to log out the user
        this.getRequest().setAttribute("USER_OBJECT", null);
        User userObj = User.builder().id(object.getId()).build();
        this.userDao.delete(userObj);


        return BaseResponseModel.builder().code(200).response("User deleted successfully").build();
    }
}
