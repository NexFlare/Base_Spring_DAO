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

    private AbstractDAL<Likes, UUID> likesDAL;

    public DeleteUserService(AbstractDAL<User, UUID> userDao,
                             HttpServletRequest request,
                             AbstractDAL<Blog,UUID> blogDAL,
                             AbstractDAL<Comments, UUID> commentsDAL,
                             AbstractDAL<Likes, UUID> likesDAL) {
        super(userDao, request);
        this.commentsDAL = commentsDAL;
        this.blogDAL = blogDAL;
        this.likesDAL = likesDAL;
    }

    @Override
    protected Response handleRequest(GetByIdRequestObject object) throws AbstractException {
        User user = this.getUserFromSession();
        if(!user.getId().equals(object.getId())) {
            return BaseResponseModel.builder().code(403).errorMessage("Unauthorized request").build();
        }
        Comments commentsObj = Comments.builder().user(user).build();
        Map<String, Object> map =new  ObjectToMap<Comments>().getMap(commentsObj);
        List<Comments> commentsList = commentsDAL.getElementsByQuery(map);
        commentsDAL.bulkDelete(commentsList);
        List<Blog> blogList = blogDAL.getElementsByQuery(map);
        blogDAL.bulkDelete(blogList);
        Likes likeObj = Likes.builder().user(user).build();
        map = new ObjectToMap<Likes>().getMap(likeObj);
        List<Likes> likesList = likesDAL.getElementsByQuery(map);
        likesDAL.bulkDelete(likesList);
        // to log out the user
        this.getRequest().setAttribute("USER_OBJECT", null);
        User userObj = User.builder().id(object.getId()).build();
        this.userDao.delete(userObj);


        return BaseResponseModel.builder().code(200).response("User deleted successfully").build();
    }
}
