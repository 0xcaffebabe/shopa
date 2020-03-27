package wang.ismy.shopa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.ismy.shopa.common.BaseResponse;

/**
 * @author MY
 * @date 2020/3/27 20:42
 */
@Api(tags = "微信注册码验证码接口")
public interface WxCodeService {
    @ApiOperation(value = "根据邮箱码和验证码验证是否正确")
    @GetMapping("/verify")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "email", dataType = "String", required = true, value = "邮箱"),
            @ApiImplicitParam(paramType = "query", name = "code", dataType = "String", required = true, value = "微信注册码") })
    BaseResponse<Boolean> verify(@RequestParam String email,@RequestParam String code);
}
