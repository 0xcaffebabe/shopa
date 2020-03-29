package wang.ismy.shopa.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import wang.ismy.shopa.api.MemberService;
import wang.ismy.shopa.common.BaseApiService;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.common.Constants;
import wang.ismy.shopa.entity.AppEntity;
import wang.ismy.shopa.entity.UserEntity;
import wang.ismy.shopa.entity.UserTokenDo;
import wang.ismy.shopa.entity.dto.UserLoginInpDTO;
import wang.ismy.shopa.service.client.WxCodeServiceClient;
import wang.ismy.shopa.service.client.WxServiceClient;
import wang.ismy.shopa.service.mapper.UserMapper;
import wang.ismy.shopa.service.mapper.UserTokenMapper;
import wang.ismy.shopa.service.utils.GenerateToken;
import wang.ismy.shopa.service.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private GenerateToken generateToken;

    @Value("${server.port}")
    private String port;

    @Override
    public BaseResponse<AppEntity> member() {
        return wxServiceClient.run();
    }

    @Override
    public BaseResponse<UserEntity> register(UserEntity user, String registerCode) {
        if (true) {
            throw new RuntimeException("test exception");
        }
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
        if (!wxCodeServiceClient.verify(user.getEmail(), registerCode).getData()) {
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
        return userEntity == null ? setResultSuccess(false) : setResultSuccess(true);
    }

    @Override
    public BaseResponse<JSONObject> login(UserLoginInpDTO userLoginInpDTO) {
        String mobile = userLoginInpDTO.getMobile();
        if (StringUtils.isEmpty(mobile)) {
            return setResultError("手机号码不能为空!");
        }

        String password = userLoginInpDTO.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空!");
        }
        String loginType = userLoginInpDTO.getLoginType();
        if (StringUtils.isEmpty(loginType)) {
            return setResultError("登陆类型不能为空!");
        }
        if (!(loginType.equals(Constants.MEMBER_LOGIN_TYPE_ANDROID) || loginType.equals(Constants.MEMBER_LOGIN_TYPE_IOS)
                || loginType.equals(Constants.MEMBER_LOGIN_TYPE_PC))) {
            return setResultError("登陆类型出现错误!");
        }

        // 设备信息
        String deviceInfor = userLoginInpDTO.getDeviceInfor();
        if (StringUtils.isEmpty(deviceInfor)) {
            return setResultError("设备信息不能为空!");
        }
        String newPassWord = MD5Util.MD5(password);
        // 2.用户名称与密码登陆
        UserEntity userDo = userMapper.login(mobile, newPassWord);
        if (userDo == null) {
            return setResultError("用户名称与密码错误!");
        }
        // 3.查询之前是否有过登陆
        Long userId = userDo.getUser_id();
        UserTokenDo userTokenDo = userTokenMapper.selectByUserIdAndLoginType(userId, loginType);
        if (userTokenDo != null) {
            // 4.清除之前的token
            String token = userTokenDo.getToken();
            Boolean removeToken = generateToken.removeToken(token);
            if (removeToken) {
                userTokenMapper.updateTokenAvailability(userId, loginType);
            }
        }
        // 5. 生成新的token
        String token = generateToken.createToken(Constants.MEMBER_TOKEN_KEYPREFIX, userId + "",
                Constants.MEMBRE_LOGIN_TOKEN_TIME);
        JSONObject tokenData = new JSONObject();
        tokenData.put("token", token);
        // 6.存入在数据库中
        UserTokenDo userToken = new UserTokenDo();
        userToken.setUserId(userId);
        userToken.setLoginType(userLoginInpDTO.getLoginType());
        userToken.setToken(token);
        userToken.setDeviceInfor(deviceInfor);
        userTokenMapper.insertUserToken(userToken);
        return setResultSuccess(tokenData);
    }

    @Override
    public BaseResponse<UserEntity> getInfo(String token) {
        // 1.参数验证
        if (StringUtils.isEmpty(token)) {
            return setResultError("token不能为空!");
        }
        // 2.使用token向redis中查询userId
        String redisValue = generateToken.getToken(token);
        if (StringUtils.isEmpty(redisValue)) {
            return setResultError("token已经失效或者不正确");
        }
        Long userId = Long.parseLong(redisValue);
        // 3.根据userId查询用户信息
        UserEntity userDo = userMapper.findByUserId(userId);
        if (userDo == null) {
            return setResultError("用户信息不存在!");
        }
        return setResultSuccess(userDo);
    }

    @Override
    public String session(HttpServletRequest request, String username) {
        if (StringUtils.isEmpty(username)) {
            return port + ":" + request.getSession().getAttribute("username").toString();
        } else {
            request.getSession().setAttribute("username", username);
            return port + ":" + "write success";
        }
    }
}

