package com.nexflare.blog.service.Likes;

import com.nexflare.blog.dal.AbstractDAL;
import com.nexflare.blog.exceptions.AbstractException;
import com.nexflare.blog.helper.ObjectToMap;
import com.nexflare.blog.mapper.IRequestToDOMapper;
import com.nexflare.blog.pojo.Likes;
import com.nexflare.blog.pojo.User;
import com.nexflare.blog.requestModel.AddLikeRequestObject;
import com.nexflare.blog.responseModel.BaseResponseModel;
import com.nexflare.blog.responseModel.Response;
import com.nexflare.blog.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AddLikeService extends AuthenticatedBaseHandler<AddLikeRequestObject> {

    AbstractDAL<Likes, UUID> likesDAO;
    IRequestToDOMapper<AddLikeRequestObject, Likes> mapper;


    public AddLikeService(AbstractDAL<User, UUID> userDao, HttpServletRequest request, AbstractDAL<Likes, UUID> likesDAO, IRequestToDOMapper<AddLikeRequestObject, Likes> mapper) {
        super(userDao, request);
        this.likesDAO = likesDAO;
        this.mapper = mapper;
    }

    @Override
    protected Response handleRequest(AddLikeRequestObject object) throws AbstractException {
        Likes likeObj = this.mapper.map(object);
        Map<String, Object> map = new ObjectToMap<Likes>().getMap(likeObj);
        Likes prevLikeObj = null;
        try {
            prevLikeObj = this.likesDAO.getUniqueElementByQuery(map);
        } catch(Exception ignore) {

        }
        if(prevLikeObj == null) {
            this.likesDAO.add(likeObj);
            return BaseResponseModel.<Likes>builder().response(likeObj).code(200).build();
        }

        return BaseResponseModel.<String>builder().errorMessage("User has already liked the post").code(400).build();

    }
}
