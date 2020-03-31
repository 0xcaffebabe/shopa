package wang.ismy.shopa.sso.service;


import wang.ismy.shopa.sso.core.model.UserInfo;
import wang.ismy.shopa.sso.core.result.ReturnT;

public interface UserService {

    ReturnT<UserInfo> findUser(String username, String password);

}
