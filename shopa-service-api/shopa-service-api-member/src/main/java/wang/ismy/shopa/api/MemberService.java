package wang.ismy.shopa.api;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.AppEntity;
import wang.ismy.shopa.entity.UserEntity;
import wang.ismy.shopa.entity.dto.UserLoginInpDTO;

/**
 * @author MY
 * @date 2020/3/26 14:58
 */
@Api(tags = "会员服务接口")
public interface MemberService {

    @GetMapping("/")
    @ApiOperation(value = "会员服务调用微信服务")
    BaseResponse<AppEntity> member();

    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    BaseResponse<UserEntity> register(@RequestBody UserEntity userEntity,
                                      @RequestParam("registerCode") String registerCode);

    @ApiOperation(value = "根据邮箱查询用户是否已经存在")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "email", dataType = "String", required = true, value = "用户邮箱"), })
    @PostMapping("/existEmail")
    BaseResponse<Boolean> existEmail(@RequestParam("email") String email);

    @PostMapping("/login")
    @ApiOperation(value = "会员用户登陆信息接口")
    BaseResponse<JSONObject> login(@RequestBody UserLoginInpDTO userLoginInpDTO);

    @GetMapping("/getUserInfo")
    @ApiOperation(value = "/getUserInfo")
    BaseResponse<UserEntity> getInfo(@RequestParam("token") String token);
}
