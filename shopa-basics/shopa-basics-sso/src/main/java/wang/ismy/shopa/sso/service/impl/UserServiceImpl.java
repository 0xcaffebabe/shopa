package wang.ismy.shopa.sso.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.UserEntity;
import wang.ismy.shopa.sso.client.MemberServiceClient;
import wang.ismy.shopa.sso.core.model.UserInfo;
import wang.ismy.shopa.sso.core.result.ReturnT;
import wang.ismy.shopa.sso.service.UserService;

import java.util.ArrayList;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MemberServiceClient memberServiceClient;

    @Override
    public ReturnT<UserInfo> findUser(String username, String password) {

        if (username==null || username.trim().length()==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "Please input username.");
        }
        if (password==null || password.trim().length()==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "Please input password.");
        }

        BaseResponse<UserEntity> result = memberServiceClient.getUserInfo(username,password);
        if (result.getData() == null){
            return new ReturnT<>(ReturnT.FAIL_CODE, "username or password is invalid.");
        }else {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            return new ReturnT<>(userInfo);
        }

    }


}
