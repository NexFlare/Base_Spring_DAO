package com.nexflare.testhiber.service.Likes;

import com.nexflare.testhiber.dao.AbstractDAO;
import com.nexflare.testhiber.exceptions.AbstractException;
import com.nexflare.testhiber.mapper.IRequestToDOMapper;
import com.nexflare.testhiber.pojo.Likes;
import com.nexflare.testhiber.pojo.User;
import com.nexflare.testhiber.requestModel.AddLikeRequestObject;
import com.nexflare.testhiber.responseModel.BaseResponseModel;
import com.nexflare.testhiber.responseModel.Response;
import com.nexflare.testhiber.service.AuthenticatedBaseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class DeleteLikeService extends AuthenticatedBaseHandler<AddLikeRequestObject> {
    AbstractDAO<Likes, UUID> likesDAO;
    IRequestToDOMapper<AddLikeRequestObject, Likes> mapper;


    public DeleteLikeService(AbstractDAO<User, UUID> userDao, HttpServletRequest request,
                             AbstractDAO<Likes, UUID> likesDAO, IRequestToDOMapper<AddLikeRequestObject, Likes> mapper) {
        super(userDao, request);
        this.likesDAO = likesDAO;
        this.mapper = mapper;
    }

    @Override
    protected Response handleRequest(AddLikeRequestObject object) throws AbstractException {
        Likes likeObj = this.mapper.map(object);
        Map<String, Object> map = new HashMap<>();
        map.put("blog", likeObj.getBlog());
        Likes like = this.likesDAO.getUniqueElementByQuery(map);
        this.likesDAO.delete(like);
        return BaseResponseModel.builder().code(200).response("Success").build();
    }
}
