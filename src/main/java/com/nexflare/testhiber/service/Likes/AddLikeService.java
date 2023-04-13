package com.nexflare.testhiber.service.Likes;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.helper.ObjectToMap;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class AddLikeService extends AuthenticatedBaseHandler<AddLikeRequestObject> {

    AbstractDAO<Likes, UUID> likesDAO;
    IRequestToDOMapper<AddLikeRequestObject, Likes> mapper;


    public AddLikeService(AbstractDAO<User, UUID> userDao, HttpServletRequest request, AbstractDAO<Likes, UUID> likesDAO, IRequestToDOMapper<AddLikeRequestObject, Likes> mapper) {
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
