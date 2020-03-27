package wang.ismy.shopa.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import wang.ismy.shopa.common.BaseResponse;
import wang.ismy.shopa.entity.AppEntity;

/**
 * @author MY
 * @date 2020/3/26 14:58
 */
@Api(tags = "会员服务接口")
public interface MemberService {

    @GetMapping("/")
    @ApiOperation(value = "会员服务调用微信服务")
    BaseResponse<AppEntity> member();
}
