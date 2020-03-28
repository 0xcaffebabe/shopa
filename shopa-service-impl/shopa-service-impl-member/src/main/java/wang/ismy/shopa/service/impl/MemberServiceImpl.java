package wang.ismy.shopa.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.MemberService;
import wang.ismy.shopa.common.BaseApiService;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.AppEntity;
import wang.ismy.shopa.entity.UserEntity;
import wang.ismy.shopa.service.client.WxCodeServiceClient;
import wang.ismy.shopa.service.client.WxServiceClient;
import wang.ismy.shopa.service.mapper.UserMapper;
import wang.ismy.shopa.service.utils.MD5Util;

/**
 * @author MY
 * @date 2020/3/26 15:01
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {

    @Autowired
    private WxServiceClient wxServiceClient;

    @Autowired
    private WxCodeServiceClient wxCodeServiceClient;

    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseResponse<AppEntity> member() {
        return wxServiceClient.run();
    }

    @Override
    public BaseResponse<UserEntity> register(UserEntity user, String registerCode) {
        String userName = user.getUserName();
        if (StringUtils.isEmpty(userName)) {
            return setResultError("用户名称不能为空!");
        }
        String mobile = user.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }
        String password = user.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        if (!wxCodeServiceClient.verify(user.getEmail(),registerCode).getData()){
            return setResultError("注册失败:验证码错误");
        }
        password = MD5Util.MD5(password);
        user.setPassword(password);
        int registerResult = userMapper.register(user);
        return registerResult > 0 ? setResultSuccess("注册成功") : setResultError("注册失败");
    }

    @Override
    public BaseResponse<Boolean> existEmail(String email) {
        UserEntity userEntity = userMapper.existEmail(email);
        return userEntity == null?setResultSuccess(false):setResultSuccess(true);
    }
}
